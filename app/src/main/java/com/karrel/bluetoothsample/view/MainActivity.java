package com.karrel.bluetoothsample.view;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityMainBinding;
import com.karrel.bluetoothsample.etc.RxEvent;
import com.karrel.bluetoothsample.presenter.MainPresenter;
import com.karrel.bluetoothsample.presenter.MainPresenterImpl;
import com.karrel.mylibrary.RLog;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private ActivityMainBinding binding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // create presenter;
        presenter = new MainPresenterImpl(this);

        setupBluetoothList();

        setupRxEvent();
    }

    private void setupBluetoothList() {
        binding.bluetoothList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DeviceListActivity.class));
            }
        });
    }

    private void setupRxEvent() {
        RxEvent.getInstance().getObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof BluetoothDevice) {
                    BluetoothDevice device = (BluetoothDevice) o;
                    RLog.e(String.format("name : %s, address : %s", device.getName(), device.getAddress()));
                    presenter.connectBt(device);
                }
            }
        });
    }


}
