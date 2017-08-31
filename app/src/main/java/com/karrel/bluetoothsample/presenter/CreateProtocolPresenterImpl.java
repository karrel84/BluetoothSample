package com.karrel.bluetoothsample.presenter;

import com.karrel.bluetoothsample.model.Protocol;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

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
    public void saveProtocol(String name, String hexCode, boolean isChecksum) {
        RLog.e(hexCode);
        // 유효성체크
        if (name.isEmpty()) {
            view.showMessage("input protocol name");
            return;
        }

        saveProtocolToDB(name, hexCode, isChecksum);

        view.finish();
    }

    @Override
    public void setData(Protocol protocol) {
        RLog.d("protocol : " + protocol);
        if (protocol == null) {
            RealmResults<Protocol> protocols = realm.where(Protocol.class).findAll();
            if (protocols.size() > 0) {
                this.protocol = protocols.get(protocols.size() - 1);
                view.setName(this.protocol.name);
            } else {
                return;
            }
        } else {
            this.protocol = protocol;
            view.setName(protocol.name);
            view.enableDeleteButton();
            view.enableModifyButton();
            view.disableSaveButton();
        }
        // set saved hex data
        setHexDataSaved();
        view.setChecksum(this.protocol.isChecksum);
    }

    @Override
    public void deleteProtocol() {
        RealmResults<Protocol> protocols = realm.where(Protocol.class).equalTo("uuid", protocol.uuid).findAll();
        // 사용하기전에 아래의 메소드를 호출해야한다
        realm.beginTransaction();
        protocols.deleteAllFromRealm();
        // 커밋
        realm.commitTransaction();
        view.finish();
    }

    @Override
    public void modifyProtocol(String name, String hexCode, boolean isChecksum) {
        RealmResults<Protocol> protocols = realm.where(Protocol.class).equalTo("uuid", protocol.uuid).findAll();
        for (Protocol protocol : protocols) {
            realm.beginTransaction();
            protocol.name = name;
            protocol.hex = hexCode;
            protocol.isChecksum = isChecksum;
            realm.commitTransaction();
        }

        view.finish();
    }

    /**
     * 저장된 hex 데이터를 뷰에 뿌린다
     */
    private void setHexDataSaved() {
        String hex = protocol.hex;

        List<List<String>> strings2 = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings2.add(strings);
        for (int i = 2; i <= hex.length(); i += 2) {
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

    private void saveProtocolToDB(String name, String hexCode, boolean isChecksum) {
        realm.beginTransaction();
        Protocol protocol = realm.createObject(Protocol.class);
        protocol.hex = hexCode;
        protocol.name = name;
        protocol.uuid = String.valueOf(System.currentTimeMillis());
        protocol.isChecksum = isChecksum;
        realm.commitTransaction();
    }
}
