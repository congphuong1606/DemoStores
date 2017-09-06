package com.example.mypc.stores.ui.main.fragment.detailstorefragment;

import com.example.mypc.stores.data.model.Account;
import com.example.mypc.stores.data.model.Location;
import com.example.mypc.stores.data.model.Post;

import java.util.ArrayList;

/**
 * Created by congp on 8/23/2017.
 */

public interface StoreDetailView {

    void onLoadPostsSuccess(ArrayList<Post> posts);

    void onRequestFailure(String s);

    void onLoadDetailSuccess(Account account);
    void onLoadLocationSuccess(Location location);
}
