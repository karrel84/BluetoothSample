package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;

import com.karrel.bluetoothsample.model.ButtonWriteDataItem;
import com.karrel.bluetoothsample.model.ReadDataItem;

import java.util.List;

/**
 * Created by Rell on 2017. 8. 24..
 */

public interface MainPresenter {
    void connectBt(BluetoothDevice device);

    void sendMessage(String message);

    void startBt();

    void stopBt();

    void onCheckedChangeToggle(boolean b);

    void clearData();

    void clickSendButton();

    void loadWriteButtonData();

    interface View {

        void connectedDevice(String mConnectedDeviceName);

        void setSatus(String s);

        void readMessage(ReadDataItem readMessage);

        void scrollToTop();

        void clearData();

        void showWriteLayout();

        void setButtonWriteData(List<ButtonWriteDataItem> writeDataItems);

        void startWriteItemActivity();
    }
}
