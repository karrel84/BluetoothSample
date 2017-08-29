package com.karrel.bluetoothsample.view;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityDeviceListBinding;
import com.karrel.bluetoothsample.event.RxBluetoothConnectEvent;
import com.karrel.bluetoothsample.model.BluetoothItem;
import com.karrel.bluetoothsample.presenter.DeviceListPresenter;
import com.karrel.bluetoothsample.presenter.DeviceListPresenterImpl;
import com.karrel.bluetoothsample.view.adapter.BluetoothAdapter;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;

import rx.functions.Action1;

public class DeviceListActivity extends AppCompatActivity implements DeviceListPresenter.View {

    private static final int REQUEST_ENABLE_BT = 1;
    private DeviceListPresenter presenter;

    private ActivityDeviceListBinding binding;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RLog.d();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device_list);

        // setup scan recyclerView
        setupScanRecyclerView();

        // create presenter
        createPresenter();

        // setup rx event
        setupConnectEvent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.startScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stopScan();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupScanRecyclerView() {
        bluetoothAdapter = new BluetoothAdapter();
        binding.bluetoothList.setLayoutManager(new LinearLayoutManager(this));
        binding.bluetoothList.setAdapter(bluetoothAdapter);
    }

    private void createPresenter() {
        presenter = new DeviceListPresenterImpl(this);
        presenter.startBluetooth();
    }

    @Override
    public void showToast(String s) {
        RLog.d(s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPermission(String rationaleMessage, String... permissions) {
        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        presenter.onPermissionGranted();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        presenter.onPermissionDenied(deniedPermissions);
                    }
                })
                .setRationaleMessage(rationaleMessage)
                .setPermissions(permissions)
                .check();
    }

    @Override
    public void startActionRequestEnable() {
        Intent enableBtIntent = new Intent(android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    @Override
    public void addBluetoothDevice(BluetoothItem item) {
        bluetoothAdapter.addData(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            RLog.d("resultCode > " + resultCode);
            presenter.onResultEnableBt(resultCode);
        }
    }

    /**
     * 블루투스 연결을 위한 이벤트가 들어오면 선택 액티비티를 종료한다.
     */
    private void setupConnectEvent() {
        RxBluetoothConnectEvent.getInstance().getObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof BluetoothDevice) {
                    finish();
                }
            }
        });
    }
}
