package com.example.mypc.stores.ui.login;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mypc.stores.data.model.Account;
import com.example.mypc.stores.network.ApiService;
import com.example.mypc.stores.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MyPC on 04/08/2017.
 */

public class LoginPresenter {
    private String mAccName;
    private String mAccPass;
    private CompositeDisposable mDisposable;
    private ApiService mApiService;
    private LoginView mView;
    boolean isCheckAccount = true;
    int i = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;

    @Inject
    public LoginPresenter(FirebaseAuth mAuth, DatabaseReference reference, CompositeDisposable mDisposable,
                          ApiService apiService,
                          LoginView mView) {
        this.mAuth = mAuth;
        this.reference = reference;
        this.mDisposable = mDisposable;
        this.mApiService = apiService;
        this.mView = mView;
    }

    public void onLogin(String accName, String accPass) {
        mAccName = accName;
        mAccPass = accPass;
        mApiService.getAccounts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFail);

    }

    private void onFail(Throwable throwable) {
        mView.onFail(String.valueOf(throwable));
    }

    private void onSuccess(ArrayList<Account> accounts) {
        int cout = accounts.size();
        i = 0;
        for (Account account : accounts) {
            i++;
            if ((account.getAccName()).equals(mAccName) &&
                    (account.getAccPass()).equals(mAccPass)) {
                mView.onLoginSuccess(account);
                isCheckAccount = true;
                break;
            } else isCheckAccount = false;
        }
        if (i == cout && !isCheckAccount) {
            mView.onFail("tài khỏan hoặc mật khẩu không chính xác !");
        }
    }

    public void onDestroy() {
        mDisposable.dispose();
    }

    public void onloginFirebase() {
        mAuth.signInAnonymously();

    }



    public void isConnect() {
        mDisposable.add(mApiService.isConnect().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::isConnectSuccess, this::isConnectFail));
    }

    private void isConnectFail(Throwable throwable) {
        mView.onIsConnect(false);
    }

    private void isConnectSuccess(Integer integer) {
        if (integer == 1) {
            mView.onIsConnect(true);
        }
    }
}
