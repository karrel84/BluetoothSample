package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.karrel.bluetoothsample.model.BluetoothItem;

/**
 * Created by Rell on 2017. 8. 28..
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    protected BluetoothItem item;

    public ViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(BluetoothItem item) {
        this.item = item;
    }
}
