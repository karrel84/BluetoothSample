package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemByteLayoutBinding;
import com.karrel.bluetoothsample.model.ReadDataItem;
import com.karrel.mylibrary.RLog;

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
//        RLog.d(readDataItem.toString());
        this.readDataItem = readDataItem;

        setupLayout();
    }

    private void setupLayout() {
        int count = 0;
        this.layout.removeAllViews();

        LinearLayout linearLayout = new LinearLayout(layout.getContext());
        this.layout.addView(linearLayout);
        for (int i = 0; i < readDataItem.list.size(); i++) {
            ItemByteLayoutBinding binding = createItemBinding();
            binding.number.setText(i + "");
            binding.value.setText(readDataItem.list.get(i));
            linearLayout.addView(binding.getRoot());
        }
    }

    private ItemByteLayoutBinding createItemBinding() {
        return DataBindingUtil.inflate(
                LayoutInflater.from(layout.getContext())
                , R.layout.item_byte_layout
                , layout
                , false);
    }

}
