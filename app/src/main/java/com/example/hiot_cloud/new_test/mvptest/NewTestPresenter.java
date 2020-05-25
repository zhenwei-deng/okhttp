package com.example.hiot_cloud.new_test.mvptest;

import com.example.hiot_cloud.new_test.mvptest.model.Users;

public class NewTestPresenter {
    private NewTestView view;
    public NewTestPresenter(NewTestView view) {
        this.view = view;
    }
    public void login(Users users) {
        if ("guoxiling".equals(users.getName()) && "20171774".equals(users.getNumber())) {
            view.showMessage("登录成功");
        }else {
            view.showMessage("登录失败");
        }
    }
}
