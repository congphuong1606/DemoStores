package com.example.mypc.stores.ui.main.fragment.usermanager;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.mypc.stores.data.model.Account;
import com.example.mypc.stores.data.model.Location;
import com.example.mypc.stores.network.ApiService;
import com.example.mypc.stores.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congp on 8/28/2017.
 */

public class UserManagerPresenter {
    private UserManagerView userManagerView;
    private ApiService apiService;
    private StorageReference mStorageReference;
    long accId;

    @Inject
    public UserManagerPresenter(UserManagerView userManagerView,
                                ApiService apiService,
                                StorageReference mStorageReference
                              ) {
        this.userManagerView = userManagerView;
        this.apiService = apiService;
        this.mStorageReference = mStorageReference;


    }


    public void getLocationStore(Long accId) {
        apiService.getLocation(accId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadLocationSuccess, this::onloadFail);
    }

    private void onLoadLocationSuccess(Location location) {
        userManagerView.onLoadLocationSuccess(location.getLocationLat(), location.getLocationLng());
    }

    private void onloadFail(Throwable throwable) {
        userManagerView.onRequestFailure(String.valueOf(throwable));
    }

    public void uploadToFireBase(byte[] picByte) {
        String picName = String.valueOf(System.currentTimeMillis());
        StorageReference mSto = mStorageReference.child(Constants.IMAGE_PIC_PATH).child(picName + ".jpg");
        UploadTask uploadTask = mSto.putBytes(picByte);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String avatarUrl = String.valueOf(taskSnapshot.getDownloadUrl());
                userManagerView.onUploadPicSuccess(avatarUrl);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                userManagerView.onRequestFailure(String.valueOf(exception));
            }
        });
    }

    public void onUpdateAccInfo(Account acc) {
        apiService.updateAcc(acc).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdateAccSuccess, this::onloadFail);
    }

    private void onUpdateAccSuccess(Integer integer) {
        if(integer==1){
            userManagerView.onUpdateSuccess(integer);
        }
    }

    public void updateLocation(Location location) {
        apiService.updateLocation(location).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdateLocationSuccess, this::onloadFail);
    }

    private void onUpdateLocationSuccess(Location location) {
        userManagerView.onUploadLocationSuccess();
    }


}
