package com.karrel.bluetoothsample.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.databinding.ItemWriteViewBinding;
import com.karrel.bluetoothsample.databinding.ItemWriteViewFooterBinding;
import com.karrel.bluetoothsample.model.ButtonWriteDataItem;
import com.karrel.bluetoothsample.view.adapter.viewholder.ProtocolViewContentHolder;
import com.karrel.bluetoothsample.view.adapter.viewholder.ProtocolViewFooterHolder;
import com.karrel.bluetoothsample.view.adapter.viewholder.ProtocolViewHolder;

import java.util.List;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class ProtocolAdapter extends RecyclerView.Adapter<ProtocolViewHolder> {
    private List<ButtonWriteDataItem> data;

    private final int TYPE_CONTENT = 0;
    private final int TYPE_FOOTER = 1;

    @Override
    public ProtocolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CONTENT) {
            ItemWriteViewBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                            , R.layout.item_write_view
                            , parent
                            , false
                    );
            return new ProtocolViewContentHolder(binding);
        } else {
            ItemWriteViewFooterBinding binding =
                    DataBindingUtil
                            .inflate(LayoutInflater.from(parent.getContext())
                                    , R.layout.item_write_view_footer
                                    , parent
                                    , false
                            );
            return new ProtocolViewFooterHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(ProtocolViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public void setData(List<ButtonWriteDataItem> data) {
        this.data = data;
        notifyItemRangeInserted(0, data.size() + 1);
    }
}
