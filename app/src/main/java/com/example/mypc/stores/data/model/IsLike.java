package com.example.mypc.stores.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsLike {

    @SerializedName("islikeId")
    @Expose
    private long islikeId;
    @SerializedName("islikePost")
    @Expose
    private long islikePost;
    @SerializedName("islikeAcc")
    @Expose
    private long islikeAcc;

    /**
     * No args constructor for use in serialization
     */
    public IsLike() {
    }

    /**
     * @param islikeAcc
     * @param islikePost
     * @param islikeId
     */
    public IsLike(long islikeId, long islikeAcc, long islikePost) {
        this.islikeId = islikeId;
        this.islikePost = islikePost;
        this.islikeAcc = islikeAcc;
    }

    public long getIslikeId() {
        return islikeId;
    }

    public void setIslikeId(long islikeId) {
        this.islikeId = islikeId;
    }

    public long getIslikePost() {
        return islikePost;
    }

    public void setIslikePost(long islikePost) {
        this.islikePost = islikePost;
    }

    public long getIslikeAcc() {
        return islikeAcc;
    }

    public void setIslikeAcc(long islikeAcc) {
        this.islikeAcc = islikeAcc;
    }
}
