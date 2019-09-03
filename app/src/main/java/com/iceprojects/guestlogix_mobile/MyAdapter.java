package com.iceprojects.guestlogix_mobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<EpisodeModel> mItems;

    public MyAdapter(Context context, ArrayList<EpisodeModel> items) {

        mContext = context;
        if (items != null)
            mItems = items;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        MyItem myItem = new MyItem(view);
        return myItem;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String s = mContext.getString(R.string.list_tv_head_prefix) + mItems.get(position).getEpisode();
        ((MyItem)holder).tv_head.setText(s);
        ((MyItem)holder).tv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EpisodesActivity.class);

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MyItem extends RecyclerView.ViewHolder{

        TextView tv_head;
        LinearLayout itemLayout;

        public MyItem(@NonNull View itemView) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.item_layout);
            tv_head = itemView.findViewById(R.id.item_tv_head);

        }
    }

}
