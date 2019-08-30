package com.teaproject.teacollectorapp.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teaproject.teacollectorapp.R;
import com.teaproject.teacollectorapp.dto.HolderResponse;

import java.util.List;

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ViewHolder> {

    private HolderResponse mHolderResponse;
    private List<HolderResponse> holderResponseList;
    private Activity activity;

    public HolderAdapter(List<HolderResponse> holderResponseList, Activity activity) {
        this.holderResponseList = holderResponseList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HolderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_holder,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapter.ViewHolder viewHolder, int i) {

        HolderResponse holderResponse = holderResponseList.get(i);
        viewHolder.mHolderName.setText(holderResponse.getUsername());
        viewHolder.mAddress.setText(holderResponse.getMobile());
    }

    @Override
    public int getItemCount() {
        return holderResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mHolderName;
        private TextView mAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHolderName = itemView.findViewById(R.id.tv_holder);
            mAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}
