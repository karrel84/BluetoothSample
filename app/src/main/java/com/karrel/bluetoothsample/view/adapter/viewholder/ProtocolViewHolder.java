package com.karrel.bluetoothsample.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.karrel.bluetoothsample.model.Protocol;

/**
 * Created by jylee on 2017. 8. 29..
 */
public class ProtocolViewHolder extends RecyclerView.ViewHolder {


    protected Protocol data;

    public ProtocolViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(Protocol data) {
        this.data = data;
    }

    public Protocol getData() {
        return data;
    }
}
