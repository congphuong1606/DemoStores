package com.example.mypc.stores.ui.register;

import com.example.mypc.stores.data.model.Account;
import com.example.mypc.stores.network.ApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MyPC on 05/08/2017.
 */

public class RegisPresenter {
    private ApiService mApiService;
    private CompositeDisposable mDisposable;
    private RegisView mRegisView;

    @Inject
    public RegisPresenter(ApiService mApiService,
                          CompositeDisposable mDisposable,
                          RegisView mRegisView) {
        this.mApiService = mApiService;
        this.mDisposable = mDisposable;
        this.mRegisView = mRegisView;
    }

    public void onSigup(String accName, String accNumber, String pass, String accType) {
        long accId =Long.valueOf(accNumber);
        String accFullname = accName;
        String accAvatar = "";
        Account account = new Account(accId, accType, accNumber, accName, accFullname, pass, accAvatar);
        mDisposable.add(mApiService.saveNewAccount(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSaveSuccess, this::onSavaFail));

    }

    private void onSaveSuccess(Account account) {
        mRegisView.onSigupSuccess();
    }

    private void onSavaFail(Throwable throwable) {
        mRegisView.onRequestFailure("đăng ký không thành công");
    }
}
