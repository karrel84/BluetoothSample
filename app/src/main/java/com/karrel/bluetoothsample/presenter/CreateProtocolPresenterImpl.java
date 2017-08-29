package com.karrel.bluetoothsample.presenter;

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
}
