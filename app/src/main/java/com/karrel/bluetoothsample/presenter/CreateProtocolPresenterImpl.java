package com.karrel.bluetoothsample.presenter;

import com.karrel.mylibrary.RLog;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class CreateProtocolPresenterImpl implements CreateProtocolPresenter {
    private CreateProtocolPresenter.View view;
    private int hexIndex = 0;

    public CreateProtocolPresenterImpl(CreateProtocolPresenter.View view) {
        this.view = view;
    }

    @Override
    public void addHexLayout() {
        view.addHexLayout(hexIndex);
        hexIndex += 10;
    }

    @Override
    public void saveProtocol(String name, String hexCode) {
        // todo 유효성체크
        if (name.isEmpty()) {
            view.showMessage("input protocol name");
            return;
        }
        RLog.e(hexCode);
    }
}
