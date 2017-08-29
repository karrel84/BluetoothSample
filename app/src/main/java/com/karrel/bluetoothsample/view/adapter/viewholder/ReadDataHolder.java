package com.karrel.bluetoothsample.view.adapter.viewholder;

import com.karrel.bluetoothsample.databinding.ItemGroupByteLayoutBinding;
import com.karrel.bluetoothsample.model.ReadDataItem;

/**
 * Created by Rell on 2017. 8. 29..
 */

public class ReadDataHolder extends ViewHolder {
    private final ItemGroupByteLayoutBinding binding;
    private ReadDataItem readDataItem;

    public ReadDataHolder(ItemGroupByteLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setData(ReadDataItem readDataItem) {
        this.readDataItem = readDataItem;

        binding.byteBoxView.setData(readDataItem);

        binding.title.setText(String.format("수신 데이터(%s)", readDataItem.date));
    }
}
