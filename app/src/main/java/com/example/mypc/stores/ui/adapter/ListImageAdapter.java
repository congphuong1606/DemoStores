package com.example.mypc.stores.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.events.ListImageAdapterListener;
import com.example.mypc.stores.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by congp on 9/3/2017.
 */

public class ListImageAdapter extends RecyclerView.Adapter {


    private ArrayList<Post> posts;
    private Context mContext;
    private ListImageAdapterListener mListener;

    public void setmListener(ListImageAdapterListener mListener) {
        this.mListener = mListener;
    }

    enum ItemType {
        FIRST,
        OTHERS
    }

    public ListImageAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ItemType.FIRST.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_post, parent, false);
            return new ViewHolder0(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_image_post_store, parent, false);
            mContext = view.getContext();
            return new ImageViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ItemType.FIRST.ordinal()) {
            ((ViewHolder0) holder).btnAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   mListener.onClickBtnAllPostStore();
                }
            });
        } else {
            Post post = posts.get(position);
            Glide.with(mContext).load(post.getPostImage())
                    .error(R.drawable.ic_no_image).into(((ImageViewHolder) holder).imvPostStore);
            ((ImageViewHolder) holder).imvPostStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClickImvPostStore(post,position);
                }
            });
        }


    }
    @Override
    public int getItemViewType(int position) {
        if (position == posts.size()) {
            return ItemType.FIRST.ordinal();
        }
        return ItemType.OTHERS.ordinal();
    }


    @Override
    public int getItemCount() {
        return posts.size()+1;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_post_store)
        CircleImageView imvPostStore;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ViewHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_all)
        Button btnAll;
        public ViewHolder0(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
