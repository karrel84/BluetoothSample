package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.view.View;

import com.karrel.bluetoothsample.databinding.ItemWriteViewFooterBinding;
import com.karrel.bluetoothsample.event.RxWriteButtonEvent;
import com.karrel.bluetoothsample.model.RxAddWriteItem;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class WriteViewFooterHolder extends WriteViewHolder {

    private ItemWriteViewFooterBinding binding;

    public WriteViewFooterHolder(ItemWriteViewFooterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        setupEvent();
    }

    private void setupEvent() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 보내는전문을 하나 추가해야해
                RxWriteButtonEvent.getInstance().sendEvent(null);
            }
        });

    }
}
