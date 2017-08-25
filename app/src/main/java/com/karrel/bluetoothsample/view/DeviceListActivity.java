package com.karrel.bluetoothsample.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityDeviceListBinding;
import com.karrel.bluetoothsample.etc.RxConnectEvent;
import com.karrel.bluetoothsample.etc.RxParingEvent;
import com.karrel.bluetoothsample.presenter.DeviceListPresenter;
import com.karrel.bluetoothsample.presenter.DeviceListPresenterImpl;
import com.karrel.bluetoothsample.view.adapter.PairedAdapter;
import com.karrel.bluetoothsample.view.adapter.ScanAdapter;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.Set;

import rx.functions.Action1;

public class DeviceListActivity extends AppCompatActivity implements DeviceListPresenter.View {

    private static final int REQUEST_ENABLE_BT = 1;
    private DeviceListPresenter presenter;

    private ActivityDeviceListBinding binding;
    private PairedAdapter pairedAdapter;
    private ScanAdapter scanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RLog.d();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device_list);

        // setupRecyclerView
        setupPairedRecyclerView();

        // setup scan recyclerView
        setupScanRecyclerView();

        // create presenter
        createPresenter();

        // setup rx event
        setupConnectEvent();

        // setup rx connext event
        setupPairingEvent();
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

    private void setupScanRecyclerView() {
        scanAdapter = new ScanAdapter();
        binding.scanedList.setLayoutManager(new LinearLayoutManager(this));
        binding.scanedList.setAdapter(scanAdapter);
    }

    private void setupPairedRecyclerView() {
        pairedAdapter = new PairedAdapter();
        binding.pairedList.setLayoutManager(new LinearLayoutManager(this));
        binding.pairedList.setAdapter(pairedAdapter);
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
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    @Override
    public void setPairedList(Set<BluetoothDevice> pairedDevices) {
        // 페어링된 기기들의 정보를 넣자.
        pairedAdapter.setData(pairedDevices);
    }

    @Override
    public void addScanedDevice(BluetoothDevice device) {
        scanAdapter.addData(device);
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
     * 스캔된 블루투스 목록에서 클릭하여 전달되는 데이터들을 받아온다.
     */
    private void setupPairingEvent() {
        RxParingEvent.getInstance().getObservable().subscribe(new Action1<BluetoothDevice>() {
            @Override
            public void call(BluetoothDevice device) {
                RLog.e("device : " + device.getName());
                presenter.pairingDevice(DeviceListActivity.this, device);
            }
        });
    }

    /**
     * 블루투스 연결을 위한 이벤트가 들어오면 선택 액티비티를 종료한다.
     */
    private void setupConnectEvent() {
        RxConnectEvent.getInstance().getObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof BluetoothDevice) {
                    finish();
                }
            }
        });
    }
}
