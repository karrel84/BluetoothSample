package com.karrel.bluetoothsample.view.adapter.viewholder;

import com.karrel.bluetoothsample.databinding.ItemProtocolViewBinding;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class ProtocolViewContentHolder extends ProtocolViewHolder {
    private ItemProtocolViewBinding binding;

    public ProtocolViewContentHolder(ItemProtocolViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
