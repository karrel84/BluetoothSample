package com.karrel.bluetoothsample.presenter;

/**
 * Created by jylee on 2017. 8. 29..
 */

public interface CreateProtocolPresenter {
    void addHexLayout();

    void saveProtocol(String name, String hexCode);

    interface View {

        void addHexLayout(int startIndex);

        void showMessage(String s);
    }
}
