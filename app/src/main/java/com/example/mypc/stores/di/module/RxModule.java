package com.example.mypc.stores.di.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by MyPC on 04/08/2017.
 */
@Module
public class RxModule {
    @Provides
    public CompositeDisposable getCompositeDisposable(){
        CompositeDisposable mDisposable=new CompositeDisposable();
        return mDisposable;
    }
}
