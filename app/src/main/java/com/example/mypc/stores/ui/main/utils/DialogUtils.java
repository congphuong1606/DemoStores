package com.example.mypc.stores.ui.main.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.example.mypc.stores.R;
import com.example.mypc.stores.events.OnEventclickListener;
import com.example.mypc.stores.events.UserManagerclickListener;

/**
 * Created by congp on 9/4/2017.
 */

public class DialogUtils {
    ProgressDialog dialog;
    Activity activity;

    public DialogUtils(ProgressDialog dialog, Activity activity) {
        this.dialog = dialog;
        this.activity = activity;
    }

    public static void showDialogGetPhotoMenu(Context context, final UserManagerclickListener mListener) {
        CharSequence[] items = {"Chọn hình", "Chụp hình"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.replayavatar));
        builder.setIcon(R.drawable.ic_noavatar);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Chọn hình")) {
                    mListener.eventChoosePhoto();
                } else if (items[i].equals("Chụp hình")) {
                    mListener.eventTakePicture();
                }
            }
        });
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showLoading() {
        if (dialog != null) {
            if (dialog.isShowing()) dialog.dismiss();
            dialog.show();
            return;
        }
        dialog = ProgressDialog
                .show(activity, "", "Loading. Please wait...", true);
    }


    public void hideLoading() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public static void showErorr(Context mContext, String msg) {
        String error = "lỗi";
        if (msg.equals(mContext.getResources().getString(R.string.isempity))) {
            error = mContext.getResources().getString(R.string.nullPosition);
        } else if (msg.equals(mContext.getResources().getString(R.string.nointernet))) {
            error = mContext.getResources().getString(R.string.chedokhonginternet);
        } else if((msg.split(" /")[0]).equals(mContext.getResources().getString(R.string.loiketnoi) )){
            error=mContext.getResources().getString(R.string.connectfail);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("lỗi");
        builder.setMessage(error);
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
    public static void showDialogConfim(Context context, final UserManagerclickListener mListener, int typeUpdate) {
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_about)
                .setTitle("Xác nhận ")
                .setMessage("Thông tin cá nhân của bạn sẽ bị thay đổi ?")
                .setPositiveButton("đồng ý", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onConfim(typeUpdate);
                    }

                })
                .setNegativeButton("hủy", null)
                .show();
    }


}
