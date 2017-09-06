package com.example.mypc.stores.ui.main.fragment.newpost;

import android.support.annotation.NonNull;

import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.network.ApiService;
import com.example.mypc.stores.utils.Constants;
import com.example.mypc.stores.utils.TimeControler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MyPC on 04/08/2017.
 */

public class NewPostPresenter {
    TimeControler timeControler = new TimeControler();
    private ApiService mApiService;
    private NewPostView mPostView;
    private CompositeDisposable mDisposable;
    private StorageReference mStorageReference;

    @Inject
    public NewPostPresenter(StorageReference mStorageReference,ApiService mApiService, NewPostView mPostView, CompositeDisposable compositeDisposable) {
        this.mStorageReference=mStorageReference;
        this.mPostView = mPostView;
        this.mApiService = mApiService;
        this.mDisposable = compositeDisposable;
    }
    public void onUploadPost(long accId, String accAvatar, String accFullName, String postContent,String postImage) {
        long postId = accId + timeControler.getLongCurentTime();
        String postTime = timeControler.getCurentTime() + "";
        Post post = new Post(postId, postContent, postTime, 0, 0, accId, accFullName, accAvatar,postImage);
        mDisposable.add(mApiService.saveNewPost(post)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFail));
    }

    private void onSuccess(Post post) {
        mPostView.onLoadPostSuccess(post);
    }

    private void onFail(Throwable throwable) {
        mPostView.onRequestFailure(String.valueOf(throwable));
    }

    public void uploadPic(byte[] picByte) {
        String picName=String.valueOf(System.currentTimeMillis());
        StorageReference mSto = mStorageReference.child(Constants.IMAGE_PIC_PATH).child(picName + ".jpg");
        UploadTask uploadTask = mSto.putBytes(picByte);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String picUrl = String.valueOf(taskSnapshot.getDownloadUrl());
                mPostView.onUploadPicSuccess(picUrl);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                mPostView.onRequestFailure(String.valueOf(exception));
            }
        });
    }
}
