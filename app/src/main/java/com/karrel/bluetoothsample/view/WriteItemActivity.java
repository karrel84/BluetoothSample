package com.karrel.bluetoothsample.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityWriteItemBinding;
import com.karrel.bluetoothsample.presenter.WriteItemPresenter;
import com.karrel.bluetoothsample.presenter.WriteItemPresenterImpl;

public class WriteItemActivity extends AppCompatActivity implements WriteItemPresenter.View {

    private ActivityWriteItemBinding binding;

    private WriteItemPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_item);

        presenter = new WriteItemPresenterImpl(this);
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
