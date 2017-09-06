package com.example.mypc.stores.events;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mypc.stores.data.model.Post;

/**
 * Created by MyPC on 03/08/2017.
 */

public interface PostAdapterClickListener {
    void onClickImvAvatarPostStore(long postStoreId);
    void onClickBtnCmt(Post post, int adapterPosition);
    void onClickBtnLike(long postId, int position, Button viewheartPost);

    void onclickBtnMenu(Post post, int position);
    void onClickImvPost(Post post, int position);
    void onClickBtnShare(String postImage);
   /* boolean isCheckIsLikePost();

    void checkLike(long islikeId);*/
}
