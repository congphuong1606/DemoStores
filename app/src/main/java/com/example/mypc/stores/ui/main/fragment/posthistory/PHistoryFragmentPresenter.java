package com.example.mypc.stores.ui.main.fragment.posthistory;

import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.network.ApiService;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congp on 9/2/2017.
 */

public class PHistoryFragmentPresenter {
    private PHistoryFragmentView mPHistoryFragmentView;
    private ApiService mApiService;

    @Inject
    public PHistoryFragmentPresenter(PHistoryFragmentView mPHistoryFragmentView, ApiService mApiService) {
        this.mPHistoryFragmentView = mPHistoryFragmentView;
        this.mApiService = mApiService;
    }

    public void getPostHistorys(long accMyId) {
        mApiService.getPostHistorys(accMyId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getPostHistorysSuccess, this::fail);
    }

    private void getPostHistorysSuccess(ArrayList<Post> posts) {
        mPHistoryFragmentView.onLoadPostsSuccess(posts);

    }

    private void fail(Throwable throwable) {
        mPHistoryFragmentView.onRequestFailure(String.valueOf(throwable));
    }


}
