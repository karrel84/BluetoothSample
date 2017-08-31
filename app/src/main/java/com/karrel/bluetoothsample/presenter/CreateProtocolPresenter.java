package com.karrel.bluetoothsample.presenter;

import com.karrel.bluetoothsample.model.Protocol;

import java.util.List;

/**
 * Created by jylee on 2017. 8. 29..
 */

public interface CreateProtocolPresenter {
    void addHexLayout();

    void saveProtocol(String name, String hexCode, boolean isChecksum);

    void setData(Protocol protocol);

    void deleteProtocol();

    void modifyProtocol(String name, String hexCode, boolean isChecksum);

    interface View {

        void addHexLayout(int startIndex);

        void showMessage(String s);

        void finish();

        void setName(String name);

        void enableDeleteButton();

        void setHexData(List<String> strings);

        void enableModifyButton();

        void disableSaveButton();

        void setChecksum(boolean isChecksum);
    }
}
