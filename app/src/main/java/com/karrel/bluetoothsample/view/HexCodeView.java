package com.karrel.bluetoothsample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemHexCodeBinding;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class HexCodeView extends LinearLayout {
    private ItemHexCodeBinding binding;
    private int hexIndex;

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

        addView(binding.getRoot());
    }

    public void setHexIndex(int hexIndex) {
        this.hexIndex = hexIndex;

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
}
