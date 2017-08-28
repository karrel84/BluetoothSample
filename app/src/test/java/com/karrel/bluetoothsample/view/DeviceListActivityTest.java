package com.karrel.bluetoothsample.view;

import android.bluetooth.BluetoothDevice;

import com.karrel.bluetoothsample.model.BluetoothItem;
import com.karrel.bluetoothsample.presenter.DeviceListPresenter;
import com.karrel.bluetoothsample.presenter.DeviceListPresenterImpl;
import com.karrel.mylibrary.RLog;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Rell on 2017. 8. 24..
 */
public class DeviceListActivityTest implements DeviceListPresenter.View {
    private DeviceListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new DeviceListPresenterImpl(this);
        RLog.setSystemMode(true);
    }

    @Test
    public void onCreate() throws Exception {
        presenter.startBluetooth();
    }


    @Override
    public void showToast(String s) {
        RLog.d(s);
    }

    @Override
    public void finish() {
        RLog.d();
    }

    @Override
    public void checkPermission(String rationaleMessage, String... permissions) {

    }

    @Override
    public void startActionRequestEnable() {

    }

    @Override
    public void addBluetoothDevice(BluetoothItem item) {

    }
}