package com.demo.leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class LeakCanaryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            //此过程专用于LeakCanary进行堆分析。在此过程中不应初始化应用程序。
            return;
        }
        LeakCanary.install(this);
    }
}
