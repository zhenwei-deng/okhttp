package com.example.hiot_cloud.ui.ChangeEmailupdate;

import android.text.TextUtils;

import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 修改邮箱presenter类
 */
public class ChangeUserEmailPresenter extends BasePresenter< ChangeUserEmailView > {

    /**
     * 网络层封装类  作用：调用service方法来实现登录
     */
    @Inject
    DataManager dataManager;

    @Inject
    public ChangeUserEmailPresenter(){

    }
    /**
     *修改邮箱

     * @param email
     */
    public void changeEmail( String email){
        subscribe(dataManager.changeEmail(  email), new RequestCallback< ResultBase<String> >() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                if (resultBase != null && !TextUtils.isEmpty(resultBase.getData())){
                    String newEmail = resultBase.getData();
                    getView().showMessage("修改成功，新邮箱：" + newEmail);
                }
                if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
                    getView().showMessage(resultBase.getMsg());
                }

            }
        });
    }
}
