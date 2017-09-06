package com.example.mypc.stores.events;

import com.example.mypc.stores.data.model.Post;

/**
 * Created by MyPC on 02/08/2017.
 */

public interface OnEventclickListener {
    void onClickDelete(long postId );
    void onClickEdit(Post post);
    void onClickSavePostHistory(long postId);
    void onClickRePortPost(Post post);

}
