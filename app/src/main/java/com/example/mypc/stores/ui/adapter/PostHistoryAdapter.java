package com.example.mypc.stores.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.events.PostHistoryAdapterOnClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by congp on 9/2/2017.
 */

public class PostHistoryAdapter extends RecyclerView.Adapter<PostHistoryAdapter.PostHistoryHolder> {

    private Context mContext;
    private ArrayList<Post> posts;
    private PostHistoryAdapterOnClickListener mListener;

    public void setListener(PostHistoryAdapterOnClickListener mListener) {
        this.mListener = mListener;
    }

    public PostHistoryAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public PostHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_history_layout, parent, false);
        mContext = view.getContext();
        return new PostHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(PostHistoryHolder holder, int position) {
        Post post = posts.get(position);
        Glide.with(mContext).load(post.getPostImage()).into(holder.imvPostHistory);
        holder.tvPostHistoryContent.setText(post.getPostContent());
        holder.tvPostHistoryStoreName.setText(post.getPostStoreName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostHistoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_post_history)
        SimpleDraweeView imvPostHistory;
        @BindView(R.id.tv_post_history_store_name)
        TextView tvPostHistoryStoreName;
        @BindView(R.id.tv_post_history_content)
        TextView tvPostHistoryContent;

        public PostHistoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
