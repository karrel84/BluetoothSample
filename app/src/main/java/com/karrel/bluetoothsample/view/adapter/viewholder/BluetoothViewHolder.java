package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.view.View;

import com.karrel.bluetoothsample.databinding.ItemPairedHolderBinding;
import com.karrel.bluetoothsample.etc.RxBluetoothConnectEvent;
import com.karrel.bluetoothsample.model.BluetoothItem;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class BluetoothViewHolder extends ViewHolder {

    private ItemPairedHolderBinding binding;

    public BluetoothViewHolder(ItemPairedHolderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        clickRoot();
    }


    @Override
    public void setData(BluetoothItem item) {
        super.setData(item);

        final String none = "(없음)";
        try {
            String name = none;
            if (item.device.getName() != null) {
                name = item.device.getName();
            }

            name = name.isEmpty() ? none : name;
            // name
            binding.deviceName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
            binding.deviceName.setText(none);
        }

        // address
        binding.address.setText(item.device.getAddress());
    }

    protected void clickRoot() {
        this.binding.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBluetoothConnectEvent.getInstance().sendEvent(item.device);
            }
        });
    }
}
