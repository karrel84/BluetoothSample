package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.view.View;

import com.karrel.bluetoothsample.databinding.ItemProtocolViewBinding;
import com.karrel.bluetoothsample.event.RxProtocolEvent;
import com.karrel.bluetoothsample.model.Protocol;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class ProtocolViewContentHolder extends ProtocolViewHolder {
    private ItemProtocolViewBinding binding;

    public ProtocolViewContentHolder(ItemProtocolViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        setupEvent();
    }

    private void setupEvent() {
        binding.settting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxProtocolEvent.getInstance().sendEvent(data);
            }
        });
    }

    @Override
    public void setData(Protocol data) {
        super.setData(data);

        binding.name.setText(data.name);
    }
}
