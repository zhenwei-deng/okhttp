package com.example.hiot_cloud.ui.register;

import com.example.hiot_cloud.ui.base.BaseView;

/**
 * 注册模块
 */
interface RegisterView extends BaseView {
    /**
     *
     * 注册成功后的处理
     * @param userName
     * @param password
     */
    void registerSucc(String userName, String password);

    /**
     * 自动登录后的处理
     */
    void loginSucc();
}
