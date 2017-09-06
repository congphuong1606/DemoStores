package com.example.mypc.stores.ui.base;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.mypc.stores.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MyPC on 02/08/2017.
 */

public abstract class BaseFragment extends Fragment {
    private View view;
    private Unbinder unbinder;

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract int getLayoutID();

    protected abstract void onDestroyComposi();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(), container, false);
        injectDependence(view);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    protected abstract void injectDependence(View view);

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        onDestroyComposi();
    }





}
