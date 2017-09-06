package com.example.mypc.stores.ui.main.fragment.listpost;

import com.example.mypc.stores.data.model.IsLike;
import com.example.mypc.stores.data.model.Post;

import java.util.ArrayList;

/**
 * Created by congp on 8/28/2017.
 */

public interface ListPostView {
    void onLoadPostsSuccess(ArrayList<Post> posts);
    void onRequestFailure(String msg);
    void onUpdatePostLoveSuccess(Integer postLove);
    void islikeSuccess(Integer integer);
    void onUpdateIsLikeSuccess(Integer integer);
    void onLoadPostsStoreSuccess(ArrayList<Post> posts);
}
