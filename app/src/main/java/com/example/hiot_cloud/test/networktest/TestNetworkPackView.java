package com.example.hiot_cloud.test.networktest;

import com.example.hiot_cloud.ui.base.BaseView;

import io.reactivex.schedulers.Schedulers;

/**
 * 网络测试封装MVP架构view层
 */
public interface TestNetworkPackView extends BaseView {
    /**
     * 显示token
     * @param token
     */
    void showTolen(String token);

    /**
     * 显示信息
     * @param message
     */
    void showMessage(String message);
}
