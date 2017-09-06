package com.example.mypc.stores.ui.main.fragment.editpost;

import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.network.ApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congp on 8/27/2017.
 */

public class EditPostPresenter {
    private CompositeDisposable mDisposable;
    private ApiService mApiService;
    private EditPostview mEditPostview;

    @Inject
    public EditPostPresenter(CompositeDisposable mDisposable, ApiService mApiService, EditPostview mEditPostview) {
        this.mDisposable = mDisposable;
        this.mApiService = mApiService;
        this.mEditPostview = mEditPostview;
    }
    public void updatePost(Post post) {
        mDisposable.add(mApiService.updatePost(post).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError));
    }

    private void onError(Throwable throwable) {
        mEditPostview.onRequestFailure(String.valueOf(throwable));
    }

    private void onSuccess(Post post) {
        mEditPostview.onUpdatePostSuccess(post);
    }
}
