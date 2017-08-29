package com.karrel.bluetoothsample.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityCreateProtocolBinding;
import com.karrel.bluetoothsample.presenter.CreateProtocolPresenter;
import com.karrel.bluetoothsample.presenter.CreateProtocolPresenterImpl;

public class CreateProtocolActivity extends AppCompatActivity implements CreateProtocolPresenter.View {

    private ActivityCreateProtocolBinding binding;

    private CreateProtocolPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_protocol);

        presenter = new CreateProtocolPresenterImpl(this);
        presenter.addHexLayout();

        setupAddEvent();
    }

    private void setupAddEvent() {
        binding.addLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addHexLayout();
            }
        });
    }

    @Override
    public void addHexLayout(int hexIndex) {
        HexCodeView hexCodeView = new HexCodeView(this);
        hexCodeView.setHexIndex(hexIndex);
        binding.hexCodeLayout.addView(hexCodeView);
    }
}
