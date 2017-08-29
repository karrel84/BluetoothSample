package com.karrel.bluetoothsample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.karrel.bluetoothsample.model.ReadDataItem;
import com.karrel.bluetoothsample.view.adapter.viewholder.ReadDataHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rell on 2017. 8. 29..
 */

public class ReadDataAdapter extends RecyclerView.Adapter<ReadDataHolder> {

    private List<ReadDataItem> list;

    public ReadDataAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ReadDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 바이트 레이아웃을 만든다.
        LinearLayout layout = new LinearLayout(parent.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        return new ReadDataHolder(layout);
    }

    @Override
    public void onBindViewHolder(ReadDataHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(ReadDataItem item) {
        list.add(0, item);
        notifyItemRangeInserted(0, 1);
    }
}
