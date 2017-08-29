package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemByteLayoutBinding;
import com.karrel.bluetoothsample.model.ReadDataItem;

/**
 * Created by Rell on 2017. 8. 29..
 */

public class ReadDataHolder extends ViewHolder {
    private LinearLayout layout;
    private ReadDataItem readDataItem;

    public ReadDataHolder(LinearLayout layout) {
        super(layout);
        this.layout = layout;
    }

    public void setData(ReadDataItem readDataItem) {
        this.readDataItem = readDataItem;

        setupLayout();
    }

    private void setupLayout() {
        int count = 0;
        LinearLayout layout = new LinearLayout(this.layout.getContext());
        while (readDataItem.queue.isEmpty()) {
            ItemByteLayoutBinding binding = createItemBinding();
            binding.number.setText(count + "");
            binding.value.setText(readDataItem.queue.poll());
            layout.addView(binding.getRoot());

            count++;
        }

        this.layout.addView(layout);
    }

    private ItemByteLayoutBinding createItemBinding() {
        return DataBindingUtil.inflate(
                LayoutInflater.from(layout.getContext())
                , R.layout.item_byte_layout
                , layout
                , false);
    }

}
