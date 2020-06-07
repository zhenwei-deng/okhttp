package com.example.hiot_cloud.ui.changepasswordupdate;

import com.example.hiot_cloud.ui.base.BaseView;

/**
 * 修改密码模块view接口
 */
public interface ChangeUserPasswordView extends BaseView {
    /**
     * 修改密码成功后返回登录界面成功的处理
     */
    void changeuserpasswordSucc();


    /**
     * 点击返回我的主界面成功的处理
     */
    void returnmine();
}
