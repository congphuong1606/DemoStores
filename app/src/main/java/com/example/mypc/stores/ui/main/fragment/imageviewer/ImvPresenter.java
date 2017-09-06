package com.example.mypc.stores.ui.main.fragment.imageviewer;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mypc.stores.data.model.IsLike;
import com.example.mypc.stores.network.ApiService;
import com.example.mypc.stores.utils.Constants;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congp on 8/30/2017.
 */

public class ImvPresenter {
    ApiService mApiService;
    ImvView mImvView;
    SharedPreferences mSharedPreferences;

    @Inject
    public ImvPresenter(ApiService mApiService, ImvView mImvView, SharedPreferences mSharedPreferences) {
        this.mApiService = mApiService;
        this.mImvView = mImvView;
        this.mSharedPreferences = mSharedPreferences;
    }

    public void addLikePost(long postId) {
        long accId = mSharedPreferences.getLong(Constants.PREF_ACC_ID, 0);
        long islikeId = Long.valueOf(String.valueOf(accId)
                .concat(String.valueOf(postId)));
        IsLike isLike = new IsLike(islikeId, accId, postId);
        mApiService.uploadIsLike(isLike).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::uploadIsLikeSuccess, this::fail);
    }

    private void uploadIsLikeSuccess(Integer integer) {
        mImvView.onUpdateIslikeSuccess(integer);
    }


    private void fail(Throwable throwable) {
        mImvView.onRequestFailure(String.valueOf(throwable));
        Log.e("ddddDDDDDDDDDD", String.valueOf(throwable));
    }


    public void updateCountLike(long postId, int i) {
        mApiService.updatePostLove(postId, i).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateCountLikeSuccess, this::fail);
    }

    private void updateCountLikeSuccess(Integer countLove) {
        mImvView.onUpdateCountLikeSuccess(countLove);
    }

    public void isLike(long postId) {
        long accId = mSharedPreferences.getLong(Constants.PREF_ACC_ID, 0);
        long islikeId = Long.valueOf(String.valueOf(accId)
                .concat(String.valueOf(postId)));
        mApiService.isLike(islikeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::isLikeSuccess, this::fail);
    }

    private void isLikeSuccess(Integer integer) {
        mImvView.onIsLikeSuccess(integer);
    }

    public void deleteLikePost(long postId) {
        long accId = mSharedPreferences.getLong(Constants.PREF_ACC_ID, 0);
        long islikeId = Long.valueOf(String.valueOf(accId)
                .concat(String.valueOf(postId)));
        mApiService.deleteIsLikePost(islikeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::deleteIsLikePostSuccess, this::fail);
    }

    private void deleteIsLikePostSuccess(Integer integer) {
        mImvView.onUpdateIslikeSuccess(integer);
    }
}
