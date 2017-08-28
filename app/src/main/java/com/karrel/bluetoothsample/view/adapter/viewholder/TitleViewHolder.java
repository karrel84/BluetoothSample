package com.karrel.bluetoothsample.view.adapter.viewholder;

import com.karrel.bluetoothsample.databinding.ItemBluetoothTitleBinding;
import com.karrel.bluetoothsample.model.BluetoothItem;
import com.karrel.bluetoothsample.model.TitleItem;

/**
 * Created by Rell on 2017. 8. 28..
 */

public class TitleViewHolder extends ViewHolder {
    private ItemBluetoothTitleBinding binding;

    public TitleViewHolder(ItemBluetoothTitleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    public void setData(BluetoothItem item) {
        super.setData(item);
        TitleItem item1 = (TitleItem) item;
        binding.title.setText(item1.title);
    }
}
