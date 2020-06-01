package com.example.hiot_cloud.ui.mine;

import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.test.networktest.UserBean;
import com.example.hiot_cloud.ui.base.BasePresenter;
import com.example.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 用户中心MinePresenter类
 */
 public class MinePresenter extends BasePresenter<MineView> {

     @Inject
     DataManager dataManager;
    @Inject
    public MinePresenter() {
    }

    /**
     * 加载用户信息
     */
    public void loadUserInfo() {
        subscribe( dataManager.getUserInfo(), new RequestCallback< ResultBase< UserBean > >() {
            @Override
            public void onNext(ResultBase< UserBean > resultBase) {
                if (resultBase == null){
                    getView().showMessage( "服务器开小差了，请稍后再试" );
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS){
                    getView().showMessage( resultBase.getMsg() );
                    return;

                }
                if (resultBase.getData() == null){
                    getView().showMessage( "服务器开小差了，请稍后再试" );
                    return;
                }
               UserBean userBean =  resultBase.getData();
                getView().refreshUserInfo(userBean);
            }
        } );

    }
}
