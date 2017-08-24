package com.karrel.bluetoothsample.view.adapter;

import android.bluetooth.BluetoothDevice;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemPairedHolderBinding;
import com.karrel.bluetoothsample.view.adapter.viewholder.PairedViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class PairedAdapter extends RecyclerView.Adapter<PairedViewHolder> {
    private List<BluetoothDevice> pairedDevices;

    @Override
    public PairedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPairedHolderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_paired_holder, parent, false);
        return new PairedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PairedViewHolder holder, int position) {
        BluetoothDevice device = pairedDevices.get(position);
        holder.setData(device);
    }

    @Override
    public int getItemCount() {
        if (pairedDevices == null) return 0;
        return pairedDevices.size();
    }

    public void setData(Set<BluetoothDevice> pairedDevices) {

        this.pairedDevices = new ArrayList<>(pairedDevices);
    }
}
