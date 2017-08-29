package com.karrel.bluetoothsample.presenter;

/**
 * Created by jylee on 2017. 8. 29..
 */

public interface WriteItemPresenter {
    void addHexLayout();

    interface View {

        void addHexLayout(int startIndex);
    }
}
