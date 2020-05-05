package com.example.hiot_cloud.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
//用于区分ApplicationContext  中的
//@ApplicationContext
//    Context context();
//
//    Application application();
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
