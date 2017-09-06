package com.example.mypc.stores;

import android.app.Application;

import com.example.mypc.stores.di.component.AppComponent;

import com.example.mypc.stores.di.component.DaggerAppComponent;
import com.example.mypc.stores.di.module.AppModule;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by MyPC on 02/08/2017.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("postRealm.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);




        myApplication = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();


        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("ChatBase")
                .setMaxCacheSize(100 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(10 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(3 * ByteConstants.MB)
                .setVersion(1)
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setResizeAndRotateEnabledForNetwork(false)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setDownsampleEnabled(true)
                .build();


        Fresco.initialize(this, config);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();


    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static synchronized MyApplication get() {
        return myApplication;
    }
}
