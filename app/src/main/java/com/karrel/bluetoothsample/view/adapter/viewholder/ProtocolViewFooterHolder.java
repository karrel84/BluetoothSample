package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.view.View;

import com.karrel.bluetoothsample.databinding.ItemProtocolViewFooterBinding;
import com.karrel.bluetoothsample.event.RxProtocolEvent;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class ProtocolViewFooterHolder extends ProtocolViewHolder {

    private ItemProtocolViewFooterBinding binding;

    public ProtocolViewFooterHolder(ItemProtocolViewFooterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        setupEvent();
    }

    private void setupEvent() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 보내는전문을 하나 추가해야해
                RxProtocolEvent.getInstance().sendEvent(null);
            }
        });

    }
}
