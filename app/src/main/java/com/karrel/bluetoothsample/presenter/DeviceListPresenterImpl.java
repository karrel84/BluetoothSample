package com.karrel.bluetoothsample.presenter;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;

import com.karrel.bluetoothsample.bluetooth.BluetoothPairUtil;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class DeviceListPresenterImpl implements DeviceListPresenter {

    private DeviceListPresenter.View view;
    private BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    private boolean mScanning;

    private BluetoothLeScanner bleScanner;

    private ScanCallback mScanCallback;

    private PublishSubject<BluetoothDevice> devicePublishSubject;

    public DeviceListPresenterImpl(DeviceListPresenter.View view) {
        this.view = view;
    }

    @Override
    public void startBluetooth() {
        RLog.e();
        // create subject
        createSubject();

        createScanCallback();

        createBluetoothLeAdapter();
        // checkpermission
        checkPermission();

    }

    private void createSubject() {
        devicePublishSubject = PublishSubject.create();
        devicePublishSubject
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new Action1<BluetoothDevice>() {
                    @Override
                    public void call(BluetoothDevice device) {
                        view.addScanedDevice(device);
                    }
                });
    }

    @Override
    public void onPermissionGranted() {
        // 권한이 승인되면 블루투스 사용가능여부를 체크한다.
        checkBluetoothEnable();
    }

    @Override
    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        view.showToast("퍼미션을 승인받지 못해서 앱을 종료합니다.");
        view.finish();
    }

    @Override
    public void onResultEnableBt(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            checkBluetoothEnable();
        } else {
            view.showToast("블루투스를 이용할 수 없습니다.");
            view.finish();
        }
    }

    /**
     * 퍼미션체크부터 시작
     */
    private void checkPermission() {
        // check permission
        RLog.e("퍼미션 체크가 필요한가?");
        view.checkPermission("블루투스를 이용하기위한 권한이 필요합니다."
                , Manifest.permission.BLUETOOTH
                , Manifest.permission.BLUETOOTH_ADMIN
                , Manifest.permission.ACCESS_COARSE_LOCATION
        );
    }

    private void checkBluetoothEnable() {
        RLog.e();
        // 블루투스 이용가능한지 체크
        RLog.d("블루투스 이용가능한지 체크");
        boolean isBluetoothEnable = isEnabledBluetooth();
        if (isBluetoothEnable) {
            RLog.d("블루투스 이용가능하면은 페어링과 페어링된 목록을 가져온다.");
            // 블루투스 이용가능하면은 페어링과 페어링된 목록을 가져온다.
            getPairedList();
            startScan();
        } else {
            RLog.d("블루투스 이용가능하도록 처리");
            // 블루투스 이용가능하도록 처리
            requestBluetoothEnable();
        }
    }

    private boolean isEnabledBluetooth() {
        return btAdapter.isEnabled();
    }

    public void startScan() {
        if (mScanning) return;
        mScanning = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bleScanner.startScan(mScanCallback);
        } else {
            btAdapter.startLeScan(mLeScanCallback);
        }
    }

    public void stopScan() {
        if (!mScanning) return;
        RLog.d("stopScan");

        mScanning = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bleScanner.stopScan(mScanCallback);
        } else {
            btAdapter.stopLeScan(mLeScanCallback);
        }
    }

    @Override
    public void pairingDevice(Context context, BluetoothDevice device) {
        stopScan();
        BluetoothPairUtil bluetoothPairUtil = new BluetoothPairUtil(context);
        bluetoothPairUtil.pairDevice(device);
    }

    /**
     * 블루투스 이용가능 요청
     */
    private void requestBluetoothEnable() {
        RLog.e();
        // 블루투스이용할 수 있도록 실행
        view.startActionRequestEnable();
    }

    private void getPairedList() {
        RLog.e("");

        // 페어링된 리스트를 가져온다.
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            RLog.d(String.format("pairedDevice > %s : %s", device.getName(), device.getAddress()));
        }
        // 페어링된 리스트를 보여준다.
        view.setPairedList(pairedDevices);
    }

    private boolean createBluetoothLeAdapter() {
        RLog.d("createBluetoothLeAdapter");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bleScanner = btAdapter.getBluetoothLeScanner();
            if (bleScanner == null) {
                view.showToast("BLE Scanner 를 찾을 수 없습니다.");
                return false;
            }
        }

        return true;
    }

    private void createScanCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mScanCallback = new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    processResult(result);
//                    RLog.d(".onScanResult");
                }

                @Override
                public void onBatchScanResults(List<ScanResult> results) {
//                    RLog.d(".onBatchScanResults");
                    for (ScanResult result : results) {
                        processResult(result);
                    }
                }

                @Override
                public void onScanFailed(int errorCode) {
                    RLog.d(".onScanFailed");
                }

                private void processResult(final ScanResult result) {
//                    RLog.d("processResult");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        devicePublishSubject.onNext(result.getDevice());
//                        RLog.d(String.format("device : %s", result.getDevice().getName()));
                    }
                }
            };
        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    devicePublishSubject.onNext(device);
                }
            };
}