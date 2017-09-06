package com.example.mypc.stores.ui.main;

import com.example.mypc.stores.data.model.Post;

import java.util.ArrayList;

/**
 * Created by MyPC on 02/08/2017.
 */

public interface MainView {
    void onDeletePostSuccess(Long postId);
    void onRequestFailure(String s);

}
