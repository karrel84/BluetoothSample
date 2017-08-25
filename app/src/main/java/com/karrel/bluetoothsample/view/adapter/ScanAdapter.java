package com.karrel.bluetoothsample.view.adapter;

import android.bluetooth.BluetoothDevice;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemPairedHolderBinding;
import com.karrel.bluetoothsample.view.adapter.viewholder.ScanViewHolder;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class ScanAdapter extends RecyclerView.Adapter<ScanViewHolder> {
    private List<BluetoothDevice> list;

    public ScanAdapter() {
        list = new ArrayList<>();

    }

    @Override
    public ScanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPairedHolderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_paired_holder, parent, false);
        return new ScanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ScanViewHolder holder, int position) {
        BluetoothDevice device = list.get(position);
        holder.setData(device);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(BluetoothDevice device) {
        RLog.e(String.format("device > %s, %s", device.getName(), device.getAddress()));
        list.add(device);
        notifyItemRangeInserted(list.size(), 1);
    }
}
