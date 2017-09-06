package com.example.mypc.stores.ui.login;

import com.example.mypc.stores.data.model.Account;

/**
 * Created by MyPC on 04/08/2017.
 */

public interface LoginView {
    void onLoginSuccess(Account account);
    void onFail(String msg);
    void onIsConnect(boolean b);
}
