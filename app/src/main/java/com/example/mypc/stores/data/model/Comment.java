package com.example.mypc.stores.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("cmtId")
    @Expose
    private long cmtId;
    @SerializedName("cmtAccId")
    @Expose
    private long cmtAccId;
    @SerializedName("cmtAccAvatar")
    @Expose
    private String cmtAccAvatar;
    @SerializedName("cmtPostId")
    @Expose
    private long cmtPostId;
    @SerializedName("cmtContent")
    @Expose
    private String cmtContent;
    @SerializedName("cmtTime")
    @Expose
    private String cmtTime;
    @SerializedName("cmtAccName")
    @Expose
    private String cmtAccName;

    public Comment() {
    }

    public Comment(long cmtId, long cmtAccId, String cmtAccAvatar,
                   long cmtPostId, String cmtContent, String cmtTime,String cmtAccName) {
        this.cmtId = cmtId;
        this.cmtAccId = cmtAccId;
        this.cmtAccAvatar = cmtAccAvatar;
        this.cmtPostId = cmtPostId;
        this.cmtContent = cmtContent;
        this.cmtTime = cmtTime;
        this.cmtAccName=cmtAccName;
    }

    public String getCmtAccName() {
        return cmtAccName;
    }

    public void setCmtAccName(String cmtAccName) {
        this.cmtAccName = cmtAccName;
    }

    public long getCmtId() {
        return cmtId;
    }

    public void setCmtId(long cmtId) {
        this.cmtId = cmtId;
    }

    public long getCmtAccId() {
        return cmtAccId;
    }

    public void setCmtAccId(long cmtAccId) {
        this.cmtAccId = cmtAccId;
    }

    public String getCmtAccAvatar() {
        return cmtAccAvatar;
    }

    public void setCmtAccAvatar(String cmtAccAvatar) {
        this.cmtAccAvatar = cmtAccAvatar;
    }

    public long getCmtPostId() {
        return cmtPostId;
    }

    public void setCmtPostId(long cmtPostId) {
        this.cmtPostId = cmtPostId;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public String getCmtTime() {
        return cmtTime;
    }

    public void setCmtTime(String cmtTime) {
        this.cmtTime = cmtTime;
    }
}
