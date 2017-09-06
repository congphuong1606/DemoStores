package com.example.mypc.stores.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

import com.example.mypc.stores.utils.Constants;

import dagger.Module;
import dagger.Provides;


/**
 * Created by MyPC on 25/07/2017.
 */
@Module
public class StorageModule {

    @Provides
    public SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(Constants.SPF_NAME, Context.MODE_PRIVATE);
    }
    @Provides
    public SharedPreferences.Editor getEditor(Context context){
        return context.getSharedPreferences(Constants.SPF_NAME, Context.MODE_PRIVATE).edit();

    }
}
