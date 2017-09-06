package com.example.mypc.stores.ui.main.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by congp on 8/30/2017.
 */

public class KeyBoardUtils {
    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public static void showKeyboard(Activity activity,EditText editText, boolean isShow) {
        editText.requestFocus();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(isShow){
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }else  inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }
}
