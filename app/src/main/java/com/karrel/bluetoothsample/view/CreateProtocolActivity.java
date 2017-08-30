package com.karrel.bluetoothsample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ActivityCreateProtocolBinding;
import com.karrel.bluetoothsample.model.Protocol;
import com.karrel.bluetoothsample.presenter.CreateProtocolPresenter;
import com.karrel.bluetoothsample.presenter.CreateProtocolPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class CreateProtocolActivity extends AppCompatActivity implements CreateProtocolPresenter.View {

    private ActivityCreateProtocolBinding binding;

    private CreateProtocolPresenter presenter;

    private List<HexCodeView> hexCodeViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_protocol);

        presenter = new CreateProtocolPresenterImpl(this);
        presenter.addHexLayout();

        setupAddEvent();

        presenter.setData((Protocol) getIntent().getParcelableExtra("protocol"));

        setupNameTouchEvent();
    }

    private void setupNameTouchEvent() {
        binding.name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    binding.name.setSelectAllOnFocus(true);
                }
                return false;
            }
        });
    }

    private void setupAddEvent() {
        binding.addLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addHexLayout();
            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save protocol
                String name = binding.name.getText().toString();
                presenter.saveProtocol(name, getHexCode());
            }
        });
    }

    @Override
    public void addHexLayout(int hexIndex) {
        HexCodeView hexCodeView = new HexCodeView(this);
        hexCodeView.setHexIndex(hexIndex);
        hexCodeViewList.add(hexCodeView);
        binding.hexCodeLayout.addView(hexCodeView);
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setName(String name) {
        binding.name.setText(name);
    }

    @Override
    public void enableDeleteButton() {
        binding.delete.setVisibility(View.VISIBLE);
    }

    /**
     * 마지막 데이터를 입력해준다..
     *
     * @param strings
     */
    @Override
    public void setHexData(List<String> strings) {
        hexCodeViewList.get(hexCodeViewList.size() - 1).setHexData(strings);
    }

    public String getHexCode() {
        // getHexCode in HexCodeView
        String hexCodes = "";
        for (HexCodeView hexCodeView : hexCodeViewList) {
            hexCodes += hexCodeView.getHexCode();
        }
        return hexCodes;
    }
}
