package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;

import com.karrel.bluetoothsample.bluetooth.BluetoothChatService;
import com.karrel.bluetoothsample.etc.Constants;
import com.karrel.bluetoothsample.model.ReadDataItem;
import com.karrel.bluetoothsample.model.WriteDataItem;
import com.karrel.bluetoothsample.util.ByteConverter;
import com.karrel.mylibrary.RLog;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View view;

    private BluetoothChatService mChatService = null;

    private String mConnectedDeviceName = null;

    public MainPresenterImpl(MainPresenter.View view) {
        this.view = view;
    }

    @Override
    public void connectBt(BluetoothDevice device) {
        // create chatService
        if (mChatService == null) {
            createBtService();
        }

        connectDevice(device, true);
    }

    @Override
    public void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);
        }
    }

    private void createBtService() {
        mChatService = new BluetoothChatService(mHandler);
    }

    private void connectDevice(BluetoothDevice device, boolean secure) {
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }

    @Override
    public void startBt() {
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }

    @Override
    public void stopBt() {
        if (mChatService != null) {
            mChatService.stop();
        }
    }


    /**
     * The Handler that gets information byte_box from the BluetoothChatService
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            RLog.d("연결됨");
                            view.connectedDevice(mConnectedDeviceName);
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            RLog.d("연결중...");
                            view.setSatus("연결중...");
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            RLog.d("연결되지 않음");
                            view.setSatus("연결되지 않음");
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    RLog.d("writeMessage : " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    ReadDataItem readDataItem = new ReadDataItem(createByteList(readBuf));
                    view.readMessage(readDataItem);
//                    RLog.d("readMessage : " + readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    RLog.d(String.format("%s에 연결됨", mConnectedDeviceName));
                    break;
                case Constants.MESSAGE_TOAST:
                    RLog.d(msg.getData().getString(Constants.TOAST));
                    break;
            }
        }
    };

    private List<String> createByteList(byte[] readBuf) {
        // construct a string from the valid bytes in the buffer
        String readMessage = ByteConverter.byteArrayToHexString(readBuf);
        // 뒤에 0은 모두 지운다.
        readMessage = readMessage.replaceAll("0*$", "");

        byte[] bytes = ByteConverter.hexToByteArray(readMessage);

        List<String> list = new ArrayList<>();
        for (byte b : bytes) {
            list.add(ByteConverter.byteToHex(b));
        }

        return list;
    }
}
