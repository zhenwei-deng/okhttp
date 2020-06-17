package com.example.hiot_cloud.ui.mine;

import com.example.hiot_cloud.data.bean.UserBean;
import com.example.hiot_cloud.ui.base.BaseView;

/**
 * 用户中心层接口
 */
interface MineView extends BaseView {
    /**
     * 刷新用户信息
     * @param userBean
     */
    void refreshUserInfo(UserBean userBean);

    /**
     * 刷新用户头像
     * @param url
     */
    void refreshUserHead(String url);

    /**
     * 重新登录后的处理
     */
    void tokenOut();

    /**
     * 点击修改密码的处理
     *
     */
    void changeUserPassword( );

    /**
     * 点击修改邮箱的处理的操作
     */

    void changeUserEmail();

    /**
     * 点击意见反馈的处理的操作
     */
    void openopinion();

    /**
     * 点击关于我们的处理的操作
     */
    void aboutWe();
}
