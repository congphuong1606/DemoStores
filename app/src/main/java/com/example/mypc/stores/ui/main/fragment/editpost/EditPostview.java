package com.example.mypc.stores.ui.main.fragment.editpost;

import com.example.mypc.stores.data.model.Post;

/**
 * Created by congp on 8/27/2017.
 */

public interface EditPostview {
    void onRequestFailure(String s);
    void onUpdatePostSuccess(Post post);
}
