package com.karrel.bluetoothsample.view;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityMainBinding;
import com.karrel.bluetoothsample.etc.RxEvent;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
                }
            }
        });
    }
}
