package com.karrel.bluetoothsample.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemBluetoothTitleBinding;
import com.karrel.bluetoothsample.databinding.ItemPairedHolderBinding;
import com.karrel.bluetoothsample.model.BluetoothItem;
import com.karrel.bluetoothsample.model.PairedItem;
import com.karrel.bluetoothsample.model.ScanedItem;
import com.karrel.bluetoothsample.model.TitleItem;
import com.karrel.bluetoothsample.view.adapter.viewholder.BluetoothViewHolder;
import com.karrel.bluetoothsample.view.adapter.viewholder.TitleViewHolder;
import com.karrel.bluetoothsample.view.adapter.viewholder.ViewHolder;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class BluetoothAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<BluetoothItem> list;
    private List<BluetoothItem> pairedItems;
    private List<BluetoothItem> scanedItems;

    private final int TYPE_TITLE = 0;
    private final int TYPE_ITEM = 1;
    private BluetoothItem item;

    public BluetoothAdapter() {
        list = new ArrayList<>();
        pairedItems = new ArrayList<>();
        pairedItems.add(new TitleItem("paired devices"));
        scanedItems = new ArrayList<>();
        scanedItems.add(new TitleItem("devices seached from BLE"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
        if (viewType == TYPE_TITLE) {
            ItemBluetoothTitleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_bluetooth_title, parent, false);
            holder = new TitleViewHolder(binding);
        } else {
            ItemPairedHolderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_paired_holder, parent, false);
            holder = new BluetoothViewHolder(binding);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        item = list.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemViewType(int position) {
        BluetoothItem item = list.get(position);
        if (item instanceof TitleItem) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(BluetoothItem item) {
        int positionStart = 0;
        if (item instanceof PairedItem) {
            positionStart = pairedItems.size();
            pairedItems.add(item);
        } else if (item instanceof ScanedItem) {
            positionStart = pairedItems.size() + scanedItems.size();
            scanedItems.add(item);
        }
        RLog.e(String.format("device > %s, %s", item.device.getName(), item.device.getAddress()));
        list = new ArrayList<>();
        list.addAll(pairedItems);
        list.addAll(scanedItems);
        notifyItemRangeInserted(positionStart, 1);
    }
}
