package com.example.hiot_cloud.test.mvptest.dagger2test;

import dagger.Module;
import dagger.Provides;

/**
 * dagger2测试module类
 */

//注解
@Module
public class TestModule {

    @Provides
    //做一个方法返回类型ThirdObj
    public ThirdObj getThirdObj(){ return new ThirdObj(); }
}
