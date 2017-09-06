package com.example.mypc.stores.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Post extends RealmObject implements Serializable,Parcelable {
    @PrimaryKey
    @SerializedName("postId")
    @Expose
    private long postId;
    @SerializedName("postContent")
    @Expose
    private String postContent;
    @SerializedName("postTime")
    @Expose
    private String postTime;
    @SerializedName("postCountComment")
    @Expose
    private Integer postCountComment;
    @SerializedName("postCountLike")
    @Expose
    private Integer postCountLike;
    @SerializedName("postStoreId")
    @Expose
    private long postStoreId;
    @SerializedName("postStoreName")
    @Expose
    private String postStoreName;
    @SerializedName("postStoreAvatar")
    @Expose
    private String postStoreAvatar;
    @SerializedName("postImage")
    @Expose
    private String postImage;
    @SerializedName("isLike")
    @Expose
    private Integer isLike;

    /**
     * No args constructor for use in serialization
     */
    public Post() {
    }

    public Post(long postId, String postContent, String postTime,
                Integer postCountComment, Integer postCountLike, long postStoreId,
                String postStoreName, String postStoreAvatar, String postImage, Integer isLike) {
        this.postId = postId;
        this.postContent = postContent;
        this.postTime = postTime;
        this.postCountComment = postCountComment;
        this.postCountLike = postCountLike;
        this.postStoreId = postStoreId;
        this.postStoreName = postStoreName;
        this.postStoreAvatar = postStoreAvatar;
        this.postImage = postImage;
        this.isLike = isLike;
    }

    public Post(long postId, String postContent, String postTime, Integer postCountComment, Integer postCountLike,
                long postStoreId, String postStoreName, String postStoreAvatar, String postImage) {
        this.postId = postId;
        this.postContent = postContent;
        this.postTime = postTime;
        this.postCountComment = postCountComment;
        this.postCountLike = postCountLike;
        this.postStoreId = postStoreId;
        this.postStoreName = postStoreName;
        this.postStoreAvatar = postStoreAvatar;
        this.postImage = postImage;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getPostCountComment() {
        return postCountComment;
    }

    public void setPostCoutComment(Integer postCountComment) {
        this.postCountComment = postCountComment;
    }

    public Integer getPostCountLike() {
        return postCountLike;
    }

    public void setPostCountLike(Integer postCountLike) {
        this.postCountLike = postCountLike;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getPostStoreId() {
        return postStoreId;
    }

    public void setPostStoreId(long postStoreId) {
        this.postStoreId = postStoreId;
    }

    public String getPostStoreName() {
        return postStoreName;
    }

    public void setPostStoreName(String postStoreName) {
        this.postStoreName = postStoreName;
    }

    public String getPostStoreAvatar() {
        return postStoreAvatar;
    }

    public void setPostStoreAvatar(String postStoreAvatar) {
        this.postStoreAvatar = postStoreAvatar;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public Post(Parcel in) {
        this.postId = in.readLong();
        this.postContent = in.readString();
        this.postTime = in.readString();
        this.postCountComment = in.readInt();
        this.postCountLike =  in.readInt();
        this.postStoreId = in.readLong();
        this.postStoreName = in.readString();
        this.postStoreAvatar = in.readString();
        this.postImage = in.readString();
        this.isLike = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.postId);
        parcel.writeString(this.postContent);
        parcel.writeString(this.postTime);
        parcel.writeInt(this.postCountComment);
        parcel.writeInt(this.postCountLike);
        parcel.writeLong(this.postStoreId);
        parcel.writeString(this.postStoreName);
        parcel.writeString(this.postStoreAvatar);
        parcel.writeString(this.postImage);
        parcel.writeInt(this.isLike);

    }
    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}