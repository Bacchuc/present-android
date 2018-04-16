package com.larry.present.common.basetemplate;

import android.app.Application;


/**
 *
 * 基础application
 *
 */
public abstract class BaseApplication extends Application {

    public abstract void initConfigs();

    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();
    }

}
