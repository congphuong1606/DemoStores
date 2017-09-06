package com.example.mypc.stores.ui.main.fragment.imageviewer;

/**
 * Created by congp on 8/30/2017.
 */

public interface ImvView {

    void onIsLikeSuccess(Integer integer);

    void onRequestFailure(String smg);

    void onUpdateCountLikeSuccess(Integer countLove);

    void onUpdateIslikeSuccess(Integer integer);

}
