package com.karrel.bluetoothsample.presenter;

import com.karrel.bluetoothsample.model.Protocol;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class CreateProtocolPresenterImpl implements CreateProtocolPresenter {
    private CreateProtocolPresenter.View view;
    private int hexIndex = 0;
    private Realm realm = Realm.getDefaultInstance();
    private Protocol protocol;

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
        RLog.e(hexCode);
        // 유효성체크
        if (name.isEmpty()) {
            view.showMessage("input protocol name");
            return;
        }

        saveProtocolToDB(name, hexCode);

        view.finish();
    }

    @Override
    public void setData(Protocol protocol) {
        if (protocol == null) return;
        RLog.d("protocol : " + protocol);
        this.protocol = protocol;
        view.setName(protocol.name);
        view.enableDeleteButton();
        // set saved hex data
        setHexDataSaved();
    }

    private void setHexDataSaved() {
        String hex = protocol.hex;

        List<List<String>> strings2 = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings2.add(strings);
        for (int i = 2; i < hex.length(); i += 2) {
            if (strings.size() == 10) {
                strings = new ArrayList<>();
                strings2.add(strings);
            }
            strings.add(hex.substring(i - 2, i));
        }

        for (int i = 0; i < strings2.size(); i++) {
            if (i != 0) {
                addHexLayout();
            }
            view.setHexData(strings2.get(i));
        }
    }

    private void saveProtocolToDB(String name, String hexCode) {
        realm.beginTransaction();
        Protocol protocol = realm.createObject(Protocol.class);
        protocol.hex = hexCode;
        protocol.name = name;
        realm.commitTransaction();
    }
}
