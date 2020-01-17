package com.example.myassignmentid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myassignmentid.R;
import com.example.myassignmentid.Utilities.NetworkUtil;
import com.example.myassignmentid.Utilities.Utilities;
import com.example.myassignmentid.model.RowData;

import java.util.List;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.MyViewHolder> {

    List<RowData> mRowData;
    Context context;

    public ListDataAdapter(List<RowData> rowData, Context mContext) {
        this.mRowData = rowData;
        this.context = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDescription;
        public ImageView mImageView;
        public LinearLayout mParentPanel;

        public MyViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.mTextViewTitle);
            mDescription = (TextView) view.findViewById(R.id.mTextViewDescription);
            mImageView = (ImageView) view.findViewById(R.id.mImageView);
            mParentPanel = (LinearLayout) view.findViewById(R.id.parentPanel);
        }
    }

    @NonNull
    @Override
    public ListDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View mView = mLayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RowData mRowItemData = mRowData.get(position);
        if (mRowItemData != null && mRowItemData.getTitle() != null) {
            holder.mTitle.setText(mRowItemData.getTitle());
        } else {
            holder.mTitle.setVisibility(View.GONE);
        }
        if (mRowItemData != null && mRowItemData.getDescription() != null) {
            holder.mDescription.setText(mRowItemData.getDescription());
        } else {
            holder.mDescription.setVisibility(View.GONE);
        }
        if (mRowItemData != null && mRowItemData.getImageHref() != null) {
            if (NetworkUtil.isConnected(context)) {
                Glide.with(this.context)
                        .load(mRowItemData.getImageHref())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.mImageView);
            } else {
                Utilities.showAlertDialog(context, context.getString(R.string.netork_error), context.getString(R.string.netork_error_message));
            }
        } else {
            holder.mImageView.setVisibility(View.GONE);
        }

    }

    // returns an size of the list
    @Override
    public int getItemCount() {
        return mRowData.size();
    }
}
