package com.example.mypc.stores.ui.main.fragment.cmt;

import com.example.mypc.stores.data.model.Comment;

import java.util.ArrayList;

/**
 * Created by MyPC on 05/08/2017.
 */

public interface CmtFragmentView {
    void onLoadCmtSuccess(ArrayList<Comment> comments);

    void onRequestFailure(String s);

    void onUploadNewCmtSuccess(Comment cmt);

    void onUpdateCountPostCmtSuccess(Integer countPostCmt);
}
