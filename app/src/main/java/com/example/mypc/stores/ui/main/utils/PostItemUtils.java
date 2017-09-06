package com.example.mypc.stores.ui.main.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.View;

import com.example.mypc.stores.R;
import com.plattysoft.leonids.ParticleSystem;

import java.util.List;

/**
 * Created by congp on 8/31/2017.
 */

public class PostItemUtils {
    public static void showAnimationHeart(Activity activity, View view){
        new ParticleSystem(activity, 100, R.drawable.ic_heart_pink, 800)
                .setSpeedRange(0.1f, 0.25f).oneShot(view,100);
    }
    public static void sendImageToFriendFaceBook(Activity activity,String url){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.setType("text/plain");

        List<ResolveInfo> matches = activity.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().contains("facebook")) {
                intent.setPackage(info.activityInfo.packageName);
            }
        }
        activity.startActivity(intent);
    }
}
