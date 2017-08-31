package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;

import com.karrel.bluetoothsample.model.ButtonWriteDataItem;
import com.karrel.bluetoothsample.model.Protocol;
import com.karrel.bluetoothsample.model.ReadDataItem;
import com.karrel.bluetoothsample.model.WriteDataItem;

import java.util.List;

/**
 * Created by Rell on 2017. 8. 24..
 */

public interface MainPresenter {
    void connectBt(BluetoothDevice device);

    void sendMessage(String hex, boolean isChecksum);

    void startBt();

    void stopBt();

    void clearData();

    void clickSendButton();

    void loadProtol();

    void onCheckedChangeToggle();

    interface View {

        void connectedDevice(String mConnectedDeviceName);

        void setSatus(String s);

        void readMessage(ReadDataItem readMessage);

        void scrollToTop();

        void clearData();

        void showProtocolLayout();

        void startCreateProtocolActivity();

        void setProtocolData(List<Protocol> protocols);

        void startCreateProtocolActivity(Protocol protocol);

        void showMessage(String s);

        void writeMessage(WriteDataItem writeDataItem);

        void setFixedToggleMenu(boolean fixedToggle);

        void hideProtocolLayout();
    }
}
