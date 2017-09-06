package com.example.mypc.stores.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {

    @SerializedName("accId")
    @Expose
    private long accId;
    @SerializedName("accType")
    @Expose
    private String accType;
    @SerializedName("accNumber")
    @Expose
    private String accNumber;
    @SerializedName("accName")
    @Expose
    private String accName;
    @SerializedName("accFullName")
    @Expose
    private String accFullName;
    @SerializedName("accPass")
    @Expose
    private String accPass;
    @SerializedName("accAvatar")
    @Expose
    private String accAvatar;


    /**
     * No args constructor for use in serialization
     */
    public Account() {
    }

    /**
     * @param accId
     * @param accType
     * @param accNumber
     * @param accName
     * @param accFullName
     * @param accPass
     * @param accAvatar
     */
    public Account(long accId, String accType, String accNumber, String accName, String accFullName, String accPass, String accAvatar) {
        super();
        this.accId = accId;
        this.accType = accType;
        this.accNumber = accNumber;
        this.accName = accName;
        this.accFullName = accFullName;
        this.accPass = accPass;
        this.accAvatar = accAvatar;


    }



    public long getAccId() {
        return accId;
    }

    public void setAccId(long accId) {
        this.accId = accId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccFullName() {
        return accFullName;
    }

    public void setAccFullName(String accFullName) {
        this.accFullName = accFullName;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public String getAccAvatar() {
        return accAvatar;
    }

    public void setAccAvatar(String accAvatar) {
        this.accAvatar = accAvatar;
    }


}
