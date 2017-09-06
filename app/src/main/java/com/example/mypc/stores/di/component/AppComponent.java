package com.example.mypc.stores.di.component;

import com.example.mypc.stores.di.module.AppModule;
import com.example.mypc.stores.di.module.NetworkModule;
import com.example.mypc.stores.di.module.RxModule;
import com.example.mypc.stores.di.module.ViewModule;

import dagger.Component;

/**
 * Created by MyPC on 02/08/2017.
 */
@Component (modules = {
        AppModule.class,
        RxModule.class,
        NetworkModule.class})
public interface AppComponent {
    SubComponent plus(ViewModule viewModule);
}
