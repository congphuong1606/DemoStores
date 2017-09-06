package com.example.mypc.stores.ui.main.fragment.listpost;

import com.example.mypc.stores.data.model.IsLike;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.network.ApiService;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congp on 8/28/2017.
 */

public class ListPostPresenter {
    private ApiService mApiService;
    private CompositeDisposable mDisposable;
    private ListPostView mListPostView;

    @Inject
    public ListPostPresenter(ListPostView view, ApiService apiService,
                             CompositeDisposable mDisposable) {
        this.mApiService = apiService;
        this.mListPostView = view;
        this.mDisposable = mDisposable;
    }


    public void getPosts(long accid,int curentPage) {
        mApiService.getListPost(accid,curentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {
        mListPostView.onRequestFailure(String.valueOf(throwable));
    }

    private void onSuccess(ArrayList<Post> posts) {
        mListPostView.onLoadPostsSuccess(posts);
    }


    public void onDestroy() {
        mDisposable.dispose();
    }


    public void updateCountPostLove(long postId, int i) {
        mApiService.updatePostLove(postId, i).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updatePostLoveSuccsess, this::onError);
    }

    private void updatePostLoveSuccsess(Integer countPostLove) {
        mListPostView.onUpdatePostLoveSuccess(countPostLove);
    }



    public void isLike(long islikeId) {
        mApiService.isLike(islikeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::isLikeSuccsess, this::onError);
    }

    private void isLikeSuccsess(Integer integer) {
        mListPostView.islikeSuccess(integer);
    }



    private void updateIsLikeSuccess(Integer integer) {
        mListPostView.onUpdateIsLikeSuccess(integer);
    }


    public void addLikePost(Long islikeId, long accid, long mPostId) {
        IsLike isLike = new IsLike(islikeId, accid, mPostId);
        mApiService.uploadIsLike(isLike).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateIsLikeSuccess, this::onError);
    }

    public void deleteLikePost(Long islikeId) {
        mApiService.deleteIsLikePost(islikeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateIsLikeSuccess, this::onError);
    }

    public void getStorePosts(long accId, long storeId, int i) {
        mApiService.getListPostStore(accId,storeId,i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetStorePostsSuccess, this::onError);
    }

    private void onGetStorePostsSuccess(ArrayList<Post> posts) {
        mListPostView.onLoadPostsStoreSuccess(posts);
    }
}
