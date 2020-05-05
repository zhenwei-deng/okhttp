package com.example.hiot_cloud.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.hiot_cloud.injection.ApplicationContext;
import com.example.hiot_cloud.injection.module.ActivityModule;
import com.example.hiot_cloud.injection.module.ApplicationModule;
import com.example.hiot_cloud.App;
import com.example.hiot_cloud.injection.component.ActivityComponent;


import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
//modules = ApplicationModule.class指向ApplicationComponent
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //定义inject，做注入App application
    void inject(App application);

    @ApplicationContext
    Context context();

    Application application();


    @Component.Builder
    //ApplicationModuleBuilder是注入器生成的方法

    interface ApplicationModuleBuilder {
        ApplicationComponent build();

        //ApplicationModuleBuilder注入器生成方法需要用到ApplicationModule，带入参数
        ApplicationModuleBuilder applicationModule(ApplicationModule applicationModule);
    }
}
