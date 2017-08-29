package com.karrel.bluetoothsample.view.adapter.viewholder;

import com.karrel.bluetoothsample.databinding.ItemWriteViewBinding;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class ProtocolViewContentHolder extends ProtocolViewHolder {
    private ItemWriteViewBinding binding;

    public ProtocolViewContentHolder(ItemWriteViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
