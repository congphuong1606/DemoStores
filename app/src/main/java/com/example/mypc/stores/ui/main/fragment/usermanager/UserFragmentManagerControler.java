package com.example.mypc.stores.ui.main.fragment.usermanager;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mypc.stores.R;
import com.example.mypc.stores.ui.main.utils.KeyBoardUtils;

import butterknife.BindView;

/**
 * Created by congp on 9/4/2017.
 */

public class UserFragmentManagerControler {

    Button btnEditAccName;
    Button btnEditAccNumber;
    Button btnEditAccLocation;
    Button btnSaveNumber;
    Button btnSaveAccName;
    Button btnSaveAccLocation;
    Activity activity;
    EditText edtEditAccName;
    EditText edtEditAccNumber;
    EditText edtLat;


    public void setViewControler(Button btnEditAccName, Button btnEditAccNumber,
                                 Button btnEditAccLocation, Button btnSaveNumber, Button btnSaveAccName,
                                 Button btnSaveAccLocation,
                                 EditText edtEditAccName, EditText edtEditAccNumber, EditText edtLat) {
        this.btnEditAccName = btnEditAccName;
        this.btnEditAccNumber = btnEditAccNumber;
        this.btnEditAccLocation = btnEditAccLocation;
        this.btnSaveNumber = btnSaveNumber;
        this.btnSaveAccName = btnSaveAccName;
        this.btnSaveAccLocation = btnSaveAccLocation;
        this.edtEditAccName = edtEditAccName;
        this.edtEditAccNumber = edtEditAccNumber;
        this.edtLat = edtLat;
    }

    public UserFragmentManagerControler(Activity activity) {
        this.activity = activity;
    }

    public void btnEditAccNameClick() {
        KeyBoardUtils.hideKeyboard(activity);
        KeyBoardUtils.showKeyboard(activity, edtEditAccName, true);
        btnEditAccName.setVisibility(View.GONE);
        btnEditAccNumber.setVisibility(View.VISIBLE);
        btnEditAccLocation.setVisibility(View.VISIBLE);
        btnSaveAccName.setVisibility(View.VISIBLE);
        btnSaveAccLocation.setVisibility(View.GONE);
        btnSaveNumber.setVisibility(View.GONE);
    }

    public void btnEditAccNumberClick() {
        KeyBoardUtils.hideKeyboard(activity);
        KeyBoardUtils.showKeyboard(activity, edtEditAccNumber, true);
        btnEditAccNumber.setVisibility(View.GONE);
        btnSaveNumber.setVisibility(View.VISIBLE);
        btnEditAccName.setVisibility(View.VISIBLE);
        btnEditAccLocation.setVisibility(View.VISIBLE);
        btnSaveAccName.setVisibility(View.GONE);
        btnSaveAccLocation.setVisibility(View.GONE);

    }

    public void btnEditAccLocationClick() {
        KeyBoardUtils.hideKeyboard(activity);
        KeyBoardUtils.showKeyboard(activity, edtLat, true);
        btnEditAccLocation.setVisibility(View.GONE);
        btnSaveAccLocation.setVisibility(View.VISIBLE);
        btnEditAccName.setVisibility(View.VISIBLE);
        btnEditAccNumber.setVisibility(View.VISIBLE);
        btnSaveAccName.setVisibility(View.GONE);
        btnSaveNumber.setVisibility(View.GONE);

    }

    public void btnSaveNumberClick() {
        btnEditAccNumber.setVisibility(View.VISIBLE);
        KeyBoardUtils.hideKeyboard(activity);
        btnSaveNumber.setVisibility(View.GONE);
        btnEditAccName.setVisibility(View.VISIBLE);
        btnEditAccLocation.setVisibility(View.VISIBLE);
        btnSaveAccName.setVisibility(View.GONE);
        btnSaveAccLocation.setVisibility(View.GONE);


    }

    public void btnSaveAccNameClick() {
        btnEditAccName.setVisibility(View.VISIBLE);
        btnSaveAccName.setVisibility(View.GONE);
        KeyBoardUtils.hideKeyboard(activity);
        btnEditAccNumber.setVisibility(View.VISIBLE);
        btnEditAccLocation.setVisibility(View.VISIBLE);
        btnSaveAccLocation.setVisibility(View.GONE);
        btnSaveNumber.setVisibility(View.GONE);
    }

    public void btnSaveAccLocationClick() {
        btnEditAccName.setVisibility(View.VISIBLE);
        btnSaveAccName.setVisibility(View.GONE);
        KeyBoardUtils.hideKeyboard(activity);
        btnEditAccNumber.setVisibility(View.VISIBLE);
        btnEditAccLocation.setVisibility(View.VISIBLE);
        btnSaveAccLocation.setVisibility(View.GONE);
        btnSaveNumber.setVisibility(View.GONE);
    }

}
