package com.example.mypc.stores.ui.main.fragment.posthistory;

import com.example.mypc.stores.data.model.Post;

import java.util.ArrayList;

/**
 * Created by congp on 9/2/2017.
 */

public interface PHistoryFragmentView {
    void onRequestFailure(String s);
    void onLoadPostsSuccess(ArrayList<Post> posts);
}
