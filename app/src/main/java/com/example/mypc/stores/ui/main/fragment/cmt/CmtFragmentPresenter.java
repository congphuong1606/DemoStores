package com.example.mypc.stores.ui.main.fragment.cmt;

import com.example.mypc.stores.data.model.Comment;
import com.example.mypc.stores.network.ApiService;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MyPC on 05/08/2017.
 */

public class CmtFragmentPresenter {
    private ApiService mApiService;
    private CompositeDisposable mDisposable;
    private CmtFragmentView mView;

    @Inject
    public CmtFragmentPresenter(ApiService mApiService,
                                CompositeDisposable mDisposable,
                                CmtFragmentView mView) {
        this.mApiService = mApiService;
        this.mDisposable = mDisposable;
        this.mView = mView;
    }

    public void onLoadPostCmts(long cmtPostId) {
        Observable<ArrayList<Comment>> postCmts = mApiService.getPostCmts(cmtPostId);
        mDisposable.add(postCmts.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError));
    }

    private void onError(Throwable throwable) {
        mView.onRequestFailure(String.valueOf(throwable));
    }

    private void onSuccess(ArrayList<Comment> comments) {
        mView.onLoadCmtSuccess(comments);
    }

    public void onDestroy() {
        mDisposable.dispose();
    }

    public void onUploadNewCmt(Comment cmt) {
        mDisposable.add(mApiService.saveNewCmt(cmt).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpCmtSuccess, this::onError));

    }

    private void onUpCmtSuccess(Comment comment) {
        mView.onUploadNewCmtSuccess(comment);
    }


    public void onUpdatePost(long postId) {
        mDisposable.add(mApiService.updatePostCmt(postId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updatePostCmtSuccsess, this::onError));
    }

    private void updatePostCmtSuccsess(Integer countPostCmt) {
        mView.onUpdateCountPostCmtSuccess(countPostCmt);
    }


}
