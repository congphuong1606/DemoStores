package com.example.mypc.stores.events;

import com.example.mypc.stores.data.model.Post;

import java.util.ArrayList;

/**
 * Created by congp on 9/3/2017.
 */

public interface ListImageAdapterListener {
    void onClickBtnAllPostStore();
    void onClickImvPostStore(Post post, int position);
}
