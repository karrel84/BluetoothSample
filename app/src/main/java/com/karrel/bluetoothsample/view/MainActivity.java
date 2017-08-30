package com.karrel.bluetoothsample.view;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityMainBinding;
import com.karrel.bluetoothsample.event.RxBluetoothConnectEvent;
import com.karrel.bluetoothsample.model.Protocol;
import com.karrel.bluetoothsample.model.ReadDataItem;
import com.karrel.bluetoothsample.model.WriteDataItem;
import com.karrel.bluetoothsample.presenter.MainPresenter;
import com.karrel.bluetoothsample.presenter.MainPresenterImpl;
import com.karrel.bluetoothsample.util.ScreenUtil;
import com.karrel.bluetoothsample.view.adapter.ProtocolAdapter;
import com.karrel.bluetoothsample.view.adapter.ReadDataAdapter;
import com.karrel.mylibrary.RLog;

import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private ReadDataAdapter readDataAdapter;
    private ProtocolAdapter writeDataAdapter;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // create presenter;
        presenter = new MainPresenterImpl(this);

        setupRxEvent();
        setupReadList();
        setupSendButton();
        setupWriteView();

        // load protocol
        presenter.loadProtol();
    }

    private void setupWriteView() {
        writeDataAdapter = new ProtocolAdapter();
        binding.protocolRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.protocolRecyclerView.setAdapter(writeDataAdapter);

    }

    private void setupSendButton() {
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clickSendButton();
            }
        });
    }

    private void setupReadList() {
        readDataAdapter = new ReadDataAdapter();
        binding.readList.setLayoutManager(new LinearLayoutManager(this));
        binding.readList.setAdapter(readDataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.startBt();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopBt();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_select_device:
                startActivity(new Intent(MainActivity.this, DeviceListActivity.class));
                return true;
            case R.id.menu_clear_log:
                presenter.clearData();
                return true;
            case R.id.menu_fixed_scroll:
                presenter.onCheckedChangeToggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRxEvent() {
        RxBluetoothConnectEvent.getInstance().getObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof BluetoothDevice) {
                    BluetoothDevice device = (BluetoothDevice) o;
                    RLog.e(String.format("name : %s, address : %s", device.getName(), device.getAddress()));
                    setStutus(String.format("name : %s, address : %s", device.getName(), device.getAddress()));
                    presenter.connectBt(device);
                }
            }
        });
    }

    @Override
    public void connectedDevice(String mConnectedDeviceName) {
        RLog.e("mConnectedDeviceName : " + mConnectedDeviceName);
        setStutus("connected : " + mConnectedDeviceName);
    }

    @Override
    public void setSatus(String s) {
        RLog.e("setSatus : " + s);
        setStutus(s);
    }

    @Override
    public void readMessage(ReadDataItem item) {
        readDataAdapter.addItem(item);
        RLog.e("readMessage : " + item.list);
    }

    @Override
    public void scrollToTop() {
        binding.readList.scrollToPosition(0);
    }

    @Override
    public void clearData() {
        readDataAdapter.clearData();
    }

    @Override
    public void showProtocolLayout() {
        RLog.e("");
        binding.protocolLayout.setVisibility(View.VISIBLE);
        binding.protocolLayout.setY(binding.contentPanel.getHeight());
        final int y = binding.contentPanel.getHeight() / 2;
        binding.protocolLayout.animate().y(y).start();

        final int y2 = (int) (y - ScreenUtil.dpToPx(this, 16) - binding.sendButton.getHeight());
        binding.sendButton.animate().y(y2).start();
    }

    @Override
    public void hideProtocolLayout() {
        RLog.e("");
        final int y = binding.contentPanel.getHeight();
        binding.protocolLayout.animate().y(y).start();

        final int y2 = (int) (y - ScreenUtil.dpToPx(this, 16) - binding.sendButton.getHeight());
        binding.sendButton.animate().y(y2).start();
    }

    @Override
    public void startCreateProtocolActivity() {
        startActivity(new Intent(this, CreateProtocolActivity.class));
    }

    @Override
    public void setProtocolData(List<Protocol> protocols) {
        writeDataAdapter.setData(protocols);
    }

    @Override
    public void startCreateProtocolActivity(Protocol protocol) {
        Intent intent = new Intent(this, CreateProtocolActivity.class);
        intent.putExtra("protocol", protocol);
        startActivity(intent);
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void writeMessage(WriteDataItem writeDataItem) {
        readDataAdapter.addItem(writeDataItem);
        RLog.e("writeMessage : " + writeDataItem.list);
    }

    @Override
    public void setFixedToggleMenu(boolean fixedToggle) {
        int resMenuIcon = fixedToggle ? R.drawable.ic_vertical_align_top_white_24dp : R.drawable.ic_vertical_align_top_black_24dp;
        menu.getItem(1).setIcon(resMenuIcon);
    }

    private void setStutus(String message) {
        RLog.e("message : " + message);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(message);
        }
    }
}
