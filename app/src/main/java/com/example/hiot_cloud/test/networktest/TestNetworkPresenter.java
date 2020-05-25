package com.example.hiot_cloud.test.networktest;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

public class TestNetworkPresenter extends BasePresenter<TestNetworkPackView> {

    @Inject
    DataManager dataManager;

    @Inject
    public TestNetworkPresenter() {
    }

    /**
     * 登录
     *
     * @param userNmae
     * @param password
     */
    public void login(String userNmae, String password) {
        subscribe( dataManager.login( userNmae, password ), new RequestCallback< ResultBase< LoginResultDTO > >() {
            @Override
            public void onNext(ResultBase< LoginResultDTO > resultBase) {
                if (resultBase != null && resultBase.getData() != null) {
                    getView().showTolen( resultBase.data.getTokenValue() );
                } else if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }
            }
        } );
    }

    /**
     * 获取用户信息
     *
     * @param authorization
     */
    public void getUserInfo(String authorization) {
        subscribe( dataManager.getUserInfo( authorization ), new RequestCallback< ResultBase< UserBean > >() {
            @Override
            public void onNext(ResultBase< UserBean > resultBase) {
                if (resultBase != null && resultBase.getData() != null) {
                    UserBean userBean = resultBase.getData();
                    String str = String.format( "用户：%s，email：%s",
                            userBean.getUsername(), userBean.getEmail() );
                    getView().showMessage( str );
                } else if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }
            }
        } );

    }

    /**
     * 修改邮箱
     *
     * @param authorization
     * @param email
     */
    public void updateEmail(String authorization, String email) {
        subscribe( dataManager.updateEmail( authorization, email ), new RequestCallback< ResultBase< String > >() {
            @Override
            public void onNext(ResultBase< String > resultBase) {
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getData() )) {
                    String newEmail = resultBase.getData();
                    getView().showMessage( "修改成功，新邮箱：" + newEmail );
                }
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }
            }
        } );
    }

    /**
     * 注册
     *
     * @param userNmae
     * @param password
     * @param email
     */
    public void register(final String userNmae, String password, String email) {
        subscribe( dataManager.register( userNmae, password, email ), new RequestCallback< ResultBase< UserBean > >() {
            @Override
            public void onNext(ResultBase< UserBean > resultBase) {
                if (resultBase != null && resultBase.getData() != null) {
                    UserBean newUserBean = resultBase.getData();
                    String userStr = String.format( "userName:%s,email:%s", newUserBean.getUsername(), newUserBean.getEmail() );
                    getView().showMessage( userStr );
                }
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError( e );
                getView().showMessage( e.getMessage() );
            }
        } );
    }

    public void GPassword(String Authorization, String oldpassword, String newpassword, String confirmpassword) {
        subscribe( dataManager.getGPassword( Authorization, oldpassword, newpassword, confirmpassword ), new RequestCallback< ResultBase< String > >() {
            @Override
            public void onNext(ResultBase< String > resultBase) {
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getData() )) {
                    String confirmpassword = resultBase.getData();
                    getView().showMessage( "修改成功，新密码：" + confirmpassword );
                }
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }

            }
        } );
    }
}

