package com.example.mypc.stores.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.mypc.stores.MyApplication;
import com.example.mypc.stores.R;
import com.example.mypc.stores.data.model.Post;
import com.example.mypc.stores.di.module.ViewModule;
import com.example.mypc.stores.events.OnEventclickListener;
import com.example.mypc.stores.utils.Constants;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MyPC on 02/08/2017.
 */

public abstract class BaseActivity extends AppCompatActivity  {
    private Unbinder mUnbinder;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutID());
        mUnbinder = ButterKnife.bind(this);
        injectDependence();
        initView();
        initData();
    }


    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
        onDestroyComposi();
    }

    protected abstract void onDestroyComposi();

    protected abstract int getContentLayoutID();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void injectDependence();




    public void onStartActivity(Class aClass) {
        startActivity(new Intent(this, aClass));
    }

    public void onShowBuiderPostPotion(final OnEventclickListener eventClick, Post post, int type) {
        CharSequence[] items = new CharSequence[0];
        if (type == Constants.ANOTHER) {
            items = new CharSequence[]{"lưu xem sau", "báo cáo"};
        } else if (type == Constants.ME) {
            items = new CharSequence[]{"xóa", "chỉnh sửa"};
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence[] finalItems = items;
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                if (finalItems[i].equals("xóa")) {
                    eventClick.onClickDelete(post.getPostId());
                } else if (finalItems[i].equals("chỉnh sửa")) {
                    eventClick.onClickEdit(post);
                } else if (finalItems[i].equals("lưu xem sau")) {
                    eventClick.onClickSavePostHistory(post.getPostId());
                } else if (finalItems[i].equals("báo cáo")) {
                    eventClick.onClickRePortPost(post);
                }
            }
        });
        AlertDialog dialog = builder.create();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(params);
        dialog.show();
    }


    public void onShowErorr(String msg) {
        if(msg.equals(getResources().getString(R.string.isempity))){

        }else {
            String error=msg;
            try{
                error = msg.split(" /")[0];
            }catch (Exception e){
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("lỗi");
            if(error.equals("java.net.ConnectException: Failed to connect to")){
                builder.setMessage(getResources().getString(R.string.connectfail));
            }else {
                builder.setMessage(error);
            }
            builder.setIcon(R.drawable.logo_app);
            builder.setCancelable(true);
            final AlertDialog dialog = builder.create();
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }







}



