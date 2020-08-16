package com.example.codingtestciti.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.codingtestciti.R;
import com.example.codingtestciti.model.AccessInfo;

import java.util.List;

/**
 * Item Adapter to populate item in the recycler view
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final List<AccessInfo.Item> mValues;

    public MyItemRecyclerViewAdapter(Context context, List<AccessInfo.Item> items) {
        this.context =context;
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide.with(context).load( mValues.get(position).getVolumeInfo().getImageLinks().getThumbnail()).into(holder.imageView);
        holder.mTitle.setText("Title : " + mValues.get(position).getVolumeInfo().getTitle());
        holder.mYear.setText("Year : " + mValues.get(position).getVolumeInfo().getPublishedDate());
        Double rating = mValues.get(position).getVolumeInfo().getAverageRating();
        if(rating != null){
            holder.mRating.setText("Rating : " + rating.toString());
        }else{
            holder.mRating.setText("Rating : " + "0");
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Hold Item Per List Row
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View contentView;
        public final ImageView imageView;
        public final TextView mTitle;
        public final TextView mYear;
        public final TextView mRating;
        public AccessInfo.Item mItem;

        public ViewHolder(View view) {
            super(view);
            contentView = view;
            imageView = view.findViewById(R.id.imageView_category);
            mTitle = view.findViewById(R.id.title);
            mYear = view.findViewById(R.id.year);
            mRating = view.findViewById(R.id.rating);
        }
    }
}
