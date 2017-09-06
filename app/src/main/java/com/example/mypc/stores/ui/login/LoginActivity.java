package com.example.mypc.stores.ui.login;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Account;
import com.example.mypc.stores.di.module.ViewModule;
import com.example.mypc.stores.ui.base.BaseActivity;
import com.example.mypc.stores.ui.main.MainActivity;
import com.example.mypc.stores.ui.register.RegisActivity;
import com.example.mypc.stores.utils.Constants;
import com.example.mypc.stores.utils.InputUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {
public static LoginActivity loginActivity;
    @BindView(R.id.edt_input_acc)
    EditText edtInputAcc;
    @BindView(R.id.tvInput)
    TextInputLayout tvInput;
    @BindView(R.id.edt_input_pass)
    EditText edtInputPass;
    @BindView(R.id.tvInput2)
    TextInputLayout tvInput2;
    @BindView(R.id.rempasswordcheckbox)
    CheckBox rempasswordcheckbox;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_quit)
    Button btnQuit;
    @BindView(R.id.btn_register)
    Button tvRegister;

    @Inject
    LoginPresenter mPresenter;
    @Inject
    SharedPreferences mPreferences;
    @Inject
    SharedPreferences.Editor editor;

    private String accName;
    private String accPass;
    private static boolean isConnect=false;


    @Override
    protected void onDestroyComposi() {

    }

    @Override
    protected void injectDependence() {
        MyApplication.get().getAppComponent()
                .plus(new ViewModule(this))
                .injectTo(this);
    }

    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mPresenter.onloginFirebase();
        loginActivity=this;
        mPresenter.isConnect();
        edtInputAcc.setText(mPreferences
                .getString(Constants.LOGIN_NAME, ""));
        edtInputPass.setText(mPreferences
                .getString(Constants.LOGIN_PASS, ""));

    }


    @Override
    protected void initView() {
        if (!mPreferences.getString(Constants.LOGIN_NAME, "").isEmpty()) {
            rempasswordcheckbox.setChecked(true);
        }

    }


    @Override
    public void onLoginSuccess(Account account) {

        editor.putLong(Constants.PREF_ACC_ID, account.getAccId())
                .putString(Constants.PREF_ACC_TYPE, account.getAccType())
                .putString(Constants.PREF_ACC_NUMBER, account.getAccNumber())
                .putString(Constants.PREF_ACC_NAME, account.getAccName())
                .putString(Constants.PREF_ACC_FULLNAME, account.getAccFullName())
                .putString(Constants.PREF_ACC_PASS, account.getAccPass())
                .putString(Constants.PREF_ACC_AVATAR, account.getAccAvatar())
                .putString(Constants.PREF_TOKEN, getResources().getString(R.string.token))
                .commit();

        onStartActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onFail(String msg) {
        mPresenter.onDestroy();
        onShowErorr(msg);
    }

    @Override
    public void onIsConnect(boolean b) {
        isConnect=b;
        if (!mPreferences.getString(Constants.PREF_TOKEN, "").isEmpty()) {
            onStartActivity(MainActivity.class);
        }
    }
    public static boolean isConnect() {
        return  isConnect;
    }

    @OnClick({R.id.btn_quit, R.id.rempasswordcheckbox, R.id.btn_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                actionLogin();
                break;
            case R.id.rempasswordcheckbox:
                if (rempasswordcheckbox.isChecked()) {
                    editor.putString(Constants.LOGIN_NAME, accName)
                            .putString(Constants.LOGIN_PASS, accPass)
                            .commit();
                } else {
                    editor.clear().commit();
                }
                break;
            case R.id.btn_register:
                onStartActivity(RegisActivity.class);
                break;
            case R.id.btn_quit:
                finish();
                break;

        }
    }

    private void actionLogin() {
        if (InputUtils.checkInPutLogin(edtInputAcc, edtInputPass, this)) {
            accName = edtInputAcc.getText().toString().trim();
            accPass = edtInputPass.getText().toString().trim();
            mPresenter.onLogin(accName,accPass);


        }
    }



}
