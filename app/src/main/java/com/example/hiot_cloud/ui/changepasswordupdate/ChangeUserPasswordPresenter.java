package com.example.hiot_cloud.ui.changepasswordupdate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.ui.base.BasePresenter;


import javax.inject.Inject;

/**
 * 修改密码presenter类
 */
public class ChangeUserPasswordPresenter extends BasePresenter< ChangeUserPasswordView > {
    private static final String TAG ="ChangeUserPasswordPresenter";
    /**
     * 网络层封装类  作用：调用service方法来实现登录
     */
    @Inject
    DataManager dataManager;

    @Inject
    public ChangeUserPasswordPresenter(){

    }


    /**
     * 修改密码
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     */
    public void GPassword( String oldpassword, String newpassword, String confirmpassword) {
        subscribe( dataManager.getGPassword( oldpassword, newpassword, confirmpassword ), new RequestCallback< ResultBase< String > >() {
            @SuppressLint("LongLogTag")
            @Override
            public void onNext(ResultBase< String > resultBase) {
                if (resultBase != null && !TextUtils.isEmpty(resultBase.getData())){
                    String confirmpassword = resultBase.getData();
                    getView().showMessage("修改成功，新密码：" + confirmpassword);
                    Log.d(TAG, confirmpassword + resultBase.getData());
                }
                if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
                    getView().showMessage(resultBase.getMsg());
                    Log.d(TAG, confirmpassword + resultBase.getMsg());
                }

            }
        } );

    }



}
