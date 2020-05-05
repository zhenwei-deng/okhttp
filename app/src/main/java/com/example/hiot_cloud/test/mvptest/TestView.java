package com.example.hiot_cloud.test.mvptest;

import com.example.hiot_cloud.ui.base.BaseView;

//让TestView使用模板类
public interface TestView extends BaseView {
    void showMessage(String msg);
}
