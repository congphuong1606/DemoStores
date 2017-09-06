package com.example.mypc.stores.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.mypc.stores.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MyPC on 05/08/2017.
 */

public class InputUtils {
    public static  boolean isEmpty(EditText etText) {
        if (etText!=null && etText.getText().toString().trim().length() > 0) {
            return true;
        } else {
            etText.requestFocus();
            etText.setError("Vui lòng điền thông tin!");
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "[a-zA-Z0-9._-]+@[a-z]+(\\.+[a-z]+)+";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public static boolean checkInPutLogin(EditText edtAccName, EditText edtAccPass, Context context){
        if (isEmpty(edtAccName) && isEmpty(edtAccPass)) {
            String acc = edtAccName.getText().toString().trim();
            String pass = edtAccPass.getText().toString().trim();
            if (acc.length() < 6) {
                edtAccName.requestFocus();
                edtAccName.setError(context.getResources().getString(R.string.accc_error));
                return false;
            } else {
                if (pass.length() < 6) {
                    edtAccPass.requestFocus();
                    edtAccPass.setError(context.getResources().getString(R.string.pass_error));
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
