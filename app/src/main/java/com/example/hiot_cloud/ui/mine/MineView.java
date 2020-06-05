package com.example.hiot_cloud.ui.mine;

import com.example.hiot_cloud.test.networktest.UserBean;
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
     * 修改密码的处理
     *
     */
    void changeUserPassword( );

    /**
     * 修改邮箱的处理的操作
     */

    void changeUserEmail();
}
