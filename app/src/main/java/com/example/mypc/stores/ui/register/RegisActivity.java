package com.example.mypc.stores.ui.register;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.R;
import com.example.mypc.stores.di.module.ViewModule;
import com.example.mypc.stores.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisActivity extends BaseActivity implements RegisView {

    @BindView(R.id.edt_input_acc)
    EditText edtInputAcc;
    @BindView(R.id.edt_input_number)
    EditText edtInputNumber;
    @BindView(R.id.edt_input_pass)
    EditText edtInputPass;
    @BindView(R.id.ck_user)
    CheckBox ckUser;
    @BindView(R.id.ck_store)
    CheckBox ckStore;
    @BindView(R.id.btn_sigup)
    Button btnRegis;
    @BindView(R.id.btn_back_login)
    Button btnBackLogin;

    @Inject
    RegisPresenter regisPresenter;

    @Override
    protected void injectDependence() {
        MyApplication.get().getAppComponent().plus(new ViewModule(this)).injectTo(this);
    }

    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_regis;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.ck_user, R.id.ck_store,
            R.id.btn_sigup, R.id.btn_back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ck_user:
                ckUser.setChecked(true);
                ckStore.setChecked(false);
                break;
            case R.id.ck_store:
                ckUser.setChecked(false);
                ckStore.setChecked(true);
                break;
            case R.id.btn_sigup:
                String accType="user";
                if(ckStore.isChecked()){
                    accType="store";
                }
                String accName=edtInputAcc.getText().toString().trim();
                String accPass=edtInputPass.getText().toString().trim();
                String accNumber=edtInputNumber.getText().toString().trim();
                regisPresenter.onSigup(accName,accNumber,accPass,accType);
                break;
            case R.id.btn_back_login:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroyComposi() {

    }


    @Override
    public void onSigupSuccess() {
        Log.i("a:","được rồi");
        finish();
    }

    @Override
    public void onRequestFailure(String msg) {
        Log.i("a:","not được");
    }
}
