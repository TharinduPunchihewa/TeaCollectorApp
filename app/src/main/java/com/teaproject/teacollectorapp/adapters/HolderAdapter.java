package com.teaproject.teacollectorapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teaproject.teacollectorapp.R;
import com.teaproject.teacollectorapp.dto.HolderList;

import java.util.List;

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ViewHolder> {

    private List<HolderList> holderListList;
    private Activity activity;

    public HolderAdapter(List<HolderList> holderListList, Activity activity) {
        this.holderListList = holderListList;
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

        final HolderList holderList = holderListList.get(i);
        viewHolder.mHolderName.setText(holderList.getUsername());
        viewHolder.mAddress.setText(holderList.getMobile());
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+holderList.getMobile()));
                activity.startActivity(callIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return holderListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mHolderName;
        private TextView mAddress;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHolderName = itemView.findViewById(R.id.tv_holder);
            mAddress = itemView.findViewById(R.id.tv_address);
            mImageView = itemView.findViewById(R.id.iv_call);
        }
    }
}
