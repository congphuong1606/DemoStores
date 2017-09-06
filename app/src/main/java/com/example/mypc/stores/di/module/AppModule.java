package com.example.mypc.stores.di.module;

import android.content.Context;

import com.example.mypc.stores.network.ApiService;
import com.example.mypc.stores.network.ApiUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MyPC on 02/08/2017.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

}
