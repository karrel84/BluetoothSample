package com.karrel.bluetoothsample.presenter;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class WriteItemPresenterImpl implements WriteItemPresenter {
    private WriteItemPresenter.View view;
    private int hexIndex = 0;

    public WriteItemPresenterImpl(WriteItemPresenter.View view) {
        this.view = view;
    }

    @Override
    public void addHexLayout() {
        view.addHexLayout(hexIndex);
        hexIndex += 10;
    }
}
