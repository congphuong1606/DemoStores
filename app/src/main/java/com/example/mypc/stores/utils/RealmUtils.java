package com.example.mypc.stores.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.data.model.Post;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by congp on 9/1/2017.
 */

public class RealmUtils {
    private static RealmUtils instance;
    private final Realm realm;

    public RealmUtils(Application application) {
        realm = Realm.getDefaultInstance();
    }
    public static RealmUtils with(android.support.v4.app.Fragment fragment) {

        if (instance == null) {
            instance = new RealmUtils(fragment.getActivity().getApplication());
        }
        return instance;
    }
    public static RealmUtils with(Activity activity) {

        if (instance == null) {
            instance = new RealmUtils(activity.getApplication());
        }
        return instance;
    }

    public static RealmUtils with(Application application) {

        if (instance == null) {
            instance = new RealmUtils(application);
        }
        return instance;
    }

    public static RealmUtils getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }


    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Post
    public void deletePost() {

        realm.beginTransaction();
        realm.delete(Post.class);
        realm.commitTransaction();
    }
    public RealmResults<Post> getPosts() {

        return realm.where(Post.class).findAll();
    }
    public Post getPost(Long id) {
        return realm.where(Post.class).equalTo("postId", id).findFirst();
    }
    public boolean hasPost() {
        return !realm.isEmpty();
    }
}
