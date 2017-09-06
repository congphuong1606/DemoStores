package com.example.mypc.stores.ui.main.fragment.usermanager;

import com.example.mypc.stores.data.model.Location;

/**
 * Created by congp on 8/28/2017.
 */

public interface UserManagerView {
    void onRequestFailure(String msg);

    void onLoadLocationSuccess(double locationLat, double locationLng);

    void onUploadPicSuccess(String avatarUrl);

    void onUpdateSuccess(Integer integer);

    void onUploadLocationSuccess();
}
