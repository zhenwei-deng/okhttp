package com.example.hiot_cloud.test.mvptest.dagger2test;

import com.example.hiot_cloud.test.mvptest.TestMVPActivity;

import dagger.Component;

/**
 * 测试注入器接口
 */
@Component(modules = TestModule.class)
public interface PresenterComponent {
    /**
     * 注入方法testMVPActivity，由dagger2框架自行生成
     * testMVPActivity
     */

    void  inject(TestMVPActivity testMVPActivity);
}
