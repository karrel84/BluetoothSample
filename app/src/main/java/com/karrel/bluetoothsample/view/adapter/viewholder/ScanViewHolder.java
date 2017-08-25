package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.view.View;

import com.karrel.bluetoothsample.databinding.ItemPairedHolderBinding;
import com.karrel.bluetoothsample.etc.RxParingEvent;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class ScanViewHolder extends PairedViewHolder {

    public ScanViewHolder(ItemPairedHolderBinding binding) {
        super(binding);
    }

    @Override
    protected void clickRoot() {
        this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxParingEvent.getInstance().sendEvent(data);
            }
        });
    }
}
