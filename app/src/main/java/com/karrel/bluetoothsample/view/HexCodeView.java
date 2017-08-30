package com.karrel.bluetoothsample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemHexCodeBinding;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class HexCodeView extends LinearLayout {
    private ItemHexCodeBinding binding;

    public HexCodeView(Context context) {
        super(context);
        init();
    }

    public HexCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HexCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext())
                , R.layout.item_hex_code
                , this
                , false
        );

        setupFocus();
        addView(binding.getRoot());
        setupSelectAll();
    }

    private void setupSelectAll() {
        OnTouchListener listener = new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    EditText text = (EditText) view;
                    text.setSelectAllOnFocus(true);
                }
                return false;
            }
        };

        binding.value1.setOnTouchListener(listener);
        binding.value2.setOnTouchListener(listener);
        binding.value3.setOnTouchListener(listener);
        binding.value4.setOnTouchListener(listener);
        binding.value5.setOnTouchListener(listener);
        binding.value6.setOnTouchListener(listener);
        binding.value7.setOnTouchListener(listener);
        binding.value8.setOnTouchListener(listener);
        binding.value9.setOnTouchListener(listener);
        binding.value10.setOnTouchListener(listener);
    }

    private void setupFocus() {
        binding.value1.setNextFocusDownId(R.id.value2);
        binding.value2.setNextFocusDownId(R.id.value3);
        binding.value3.setNextFocusDownId(R.id.value4);
        binding.value4.setNextFocusDownId(R.id.value5);
        binding.value5.setNextFocusDownId(R.id.value6);
        binding.value6.setNextFocusDownId(R.id.value7);
        binding.value7.setNextFocusDownId(R.id.value8);
        binding.value8.setNextFocusDownId(R.id.value9);
        binding.value9.setNextFocusDownId(R.id.value10);
    }

    public void setHexIndex(int hexIndex) {
        binding.number1.setText(++hexIndex + "");
        binding.number2.setText(++hexIndex + "");
        binding.number3.setText(++hexIndex + "");
        binding.number4.setText(++hexIndex + "");
        binding.number5.setText(++hexIndex + "");
        binding.number6.setText(++hexIndex + "");
        binding.number7.setText(++hexIndex + "");
        binding.number8.setText(++hexIndex + "");
        binding.number9.setText(++hexIndex + "");
        binding.number10.setText(++hexIndex + "");
    }

    public String getHexCode() {
        String hexCodes = "";
        hexCodes += getHexCode(binding.value1);
        hexCodes += getHexCode(binding.value2);
        hexCodes += getHexCode(binding.value3);
        hexCodes += getHexCode(binding.value4);
        hexCodes += getHexCode(binding.value5);
        hexCodes += getHexCode(binding.value6);
        hexCodes += getHexCode(binding.value7);
        hexCodes += getHexCode(binding.value8);
        hexCodes += getHexCode(binding.value9);
        hexCodes += getHexCode(binding.value10);
        return hexCodes;
    }

    private String getHexCode(EditText value) {
        String hexCode = value.getText().toString();
        if (hexCode.isEmpty()) {
            return "00";
        }

        if (hexCode.length() == 1) {
            return "0" + hexCode;
        }

        return hexCode;
    }
}
