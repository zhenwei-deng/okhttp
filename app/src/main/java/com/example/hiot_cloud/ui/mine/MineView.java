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
}
