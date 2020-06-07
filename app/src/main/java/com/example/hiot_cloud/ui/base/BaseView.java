package com.example.hiot_cloud.ui.base;

/**
 * MVP架构视图层接口
 */
public interface BaseView {
    /**
     * 弹出登录成功
     * @param message
     */
    void showMessage(String message);

    /**
     * token失效的处理
     */
    void tokenOut();
}
