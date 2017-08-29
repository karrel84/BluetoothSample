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

        binding.item1.number.setText(++hexIndex + "");
        binding.item2.number.setText(++hexIndex + "");
        binding.item3.number.setText(++hexIndex + "");
        binding.item4.number.setText(++hexIndex + "");
        binding.item5.number.setText(++hexIndex + "");
        binding.item6.number.setText(++hexIndex + "");
        binding.item7.number.setText(++hexIndex + "");
        binding.item8.number.setText(++hexIndex + "");
        binding.item9.number.setText(++hexIndex + "");
        binding.item10.number.setText(++hexIndex + "");
    }
}
