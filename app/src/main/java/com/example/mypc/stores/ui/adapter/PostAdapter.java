package com.example.mypc.stores.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.events.PostAdapterClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MyPC on 02/08/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostviewHoder> {
    ArrayList<Post> posts;
    Context mContext;
    PostAdapterClickListener mListener;


    public void setPostAdapter(PostAdapterClickListener mListener) {
        this.mListener = mListener;

    }

    public PostAdapter(ArrayList<Post> posts) {
        this.posts = posts;


    }

    @Override
    public PostviewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_layout, parent, false);
        mContext = view.getContext();
        return new PostviewHoder(view);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }


    @Override
    public void onBindViewHolder(PostviewHoder holder, int position) {

        Post post = posts.get(position);
        setViewholer(holder,position,post);
        //set Color btn like
        setColorBtnLike(post.getIsLike(), holder.btnLikePost);

        setOnClick(holder, post, position);


    }

    private void setViewholer(PostviewHoder holder, int position, Post post) {
        holder.tvStoreName.setText(post.getPostStoreName() + "");
        holder.tvPostTime.setText(post.getPostTime() + "");
        if (post.getPostCountLike() == 0) {
            holder.tvPostCountLike.setText(" ");
        } else {
            holder.tvPostCountLike.setText(post.getPostCountLike() + " "
                    + mContext.getResources().getString(R.string.yeuthich));
        }
        if (post.getPostCountComment() == 0) {
            holder.tvPostCountCmt.setText("");
        } else {
            holder.tvPostCountCmt.setText(post.getPostCountComment() + " "
                    + mContext.getApplicationContext().getString(R.string.binhluan));
        }
        holder.tvPostContent.setText(post.getPostContent() + "");
        Glide.with(mContext).load(post.getPostStoreAvatar()).into(holder.imvAvatarPostStore);
        if (post.getPostImage() != null) {
//            holder.imvPostImage.setImageURI(Uri.parse(post.getPostImage()));
            Glide.with(mContext).load(post.getPostImage()).into(holder.imvPostImage);
        }
    }

    private void setOnClick(PostviewHoder holder, Post post, int position) {
        holder.imvAvatarPostStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickImvAvatarPostStore(post.getPostStoreId());
            }
        });
        holder.btnCommentPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickBtnCmt(post, position);
            }
        });
        holder.btnLikePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickBtnLike(post.getPostId(), position, holder.btnLikePost);
            }
        });
        holder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onclickBtnMenu(post, position);
            }
        });
        holder.tvPostContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tvPostContent.setMaxLines(200);
            }
        });
        holder.imvPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickImvPost(post, position);
            }
        });
        holder.imvPostImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.onClickBtnLike(post.getPostId(), position, holder.btnLikePost);
                return true;
            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickBtnShare(post.getPostImage());
            }
        });
    }

    private void setColorBtnLike(Integer isLike, Button btnLike) {
        if (isLike == 1) {
            btnLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_redxml, 0, 0, 0);
            btnLike.setTextColor(mContext.getResources().getColor(R.color.text_color_pink));

        } else {
            btnLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_whitexml, 0, 0, 0);
            btnLike.setTextColor(mContext.getResources().getColor(R.color.text_color_black));
        }
    }


    public class PostviewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_heart_post)
        View viewheartPost;
        @BindView(R.id.imv_avatar_post_store)
        CircleImageView imvAvatarPostStore;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.tv_post_time)
        TextView tvPostTime;
        @BindView(R.id.btn_menu)
        Button btnMenu;
        @BindView(R.id.tv_post_content)
        TextView tvPostContent;
        @BindView(R.id.imv_post_image)
        SimpleDraweeView imvPostImage;
        @BindView(R.id.tv_post_count_Like)
        TextView tvPostCountLike;
        @BindView(R.id.btn_like_post)
        Button btnLikePost;
        @BindView(R.id.btn_comment_post)
        Button btnCommentPost;
        @BindView(R.id.tv_post_count_cmt)
        TextView tvPostCountCmt;
        @BindView(R.id.btn_share)
        Button btnShare;

        public PostviewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

    }


}
