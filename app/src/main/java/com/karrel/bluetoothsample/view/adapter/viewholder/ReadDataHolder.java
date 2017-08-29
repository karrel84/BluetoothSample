package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.support.v4.content.ContextCompat;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemGroupByteLayoutBinding;
import com.karrel.bluetoothsample.model.ReadDataItem;
import com.karrel.bluetoothsample.model.WriteDataItem;

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

        if (readDataItem instanceof WriteDataItem) {
            binding.title.setText(String.format("송신 데이터(%s)", readDataItem.date));
            binding.byteBoxView.setCountColor(R.color.colorAccent);
            binding.title.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.colorAccent));
            binding.line.setBackgroundColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.colorAccent));
        } else {
            binding.title.setText(String.format("수신 데이터(%s)", readDataItem.date));
            binding.byteBoxView.setCountColor(R.color.colorPrimary);
            binding.title.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.colorPrimary));
            binding.line.setBackgroundColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.colorPrimary));
        }

        binding.byteBoxView.setData(readDataItem);
    }
}
