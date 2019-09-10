package com.iceprojects.guestlogix_mobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<EpisodeModel> mItems;
    private ArrayList<CharacterModel> cItems;

    public MyAdapter(Context context, ArrayList<EpisodeModel> items) {

        mContext = context;
        if (items != null)
            mItems = items;
        cItems = null;

    }
    public MyAdapter(ArrayList<CharacterModel> items, Context context) {

        mContext = context;
        if (items != null)
            cItems = items;
        mItems = null;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (mItems != null){
            String s = mContext.getString(R.string.list_tv_head_prefix) + mItems.get(position).getEpisode() + ": " + mItems.get(position).getName();
            ((MyItem)holder).tv_head.setText(s);
            ((MyItem)holder).tv_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, EpisodesActivity.class);
                    intent.putExtra("episode", mItems.get(position));
                    mContext.startActivity(intent);
                }
            });
        }
        if (cItems != null){
            String s = cItems.get(position).getName();
            ((MyItem)holder).tv_head.setText(s);
            ((MyItem)holder).tv_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CharacterDetailsActivity.class);
                    intent.putExtra("character", cItems.get(position));
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mItems == null? cItems.size() : mItems.size();
    }

    public class MyItem extends RecyclerView.ViewHolder{

        TextView tv_head;

        public MyItem(@NonNull View itemView) {
            super(itemView);

            tv_head = itemView.findViewById(R.id.item_tv);

        }
    }

}
