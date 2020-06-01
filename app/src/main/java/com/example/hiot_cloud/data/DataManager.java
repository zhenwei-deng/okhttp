package com.example.hiot_cloud.data;

import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.test.networktest.UserBean;
import com.example.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 网络请求封装类
 */
public class DataManager {

    private NetworkService service;

    SharedPreferencesHelper sharedPreferencesHelper;
    @Inject
    public DataManager(NetworkService service,SharedPreferencesHelper sharedPreferencesHelper) {
        this.service = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Observable<ResultBase<LoginResultDTO>> login(String userName,String password){
        return service.login(userName,password, Constants.LOGIN_CODE_APP)
                .doOnNext( new Consumer< ResultBase< LoginResultDTO > >() {
                    @Override
                    public void accept(ResultBase< LoginResultDTO > resultBase) throws Exception {
                        if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                            if (resultBase != null && resultBase.getData() != null) {
                                sharedPreferencesHelper.setUserToken( resultBase.getData().getTokenValue() );
                            }
                        }
                    }
                } );
    }

    /**
     * 获取用户信息
     * @return
     */
    public Observable<ResultBase<UserBean>> getUserInfo( ){
        return service.getUserInfo(sharedPreferencesHelper.getUserToke());
    }

    /**
     * 修改邮箱
     * @param email
     * @return
     */
    public Observable<ResultBase<String>> updateEmail(String email){
        return service.updateEmail(sharedPreferencesHelper.getUserToke(),email);
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @param email
     * @return
     */
    public Observable<ResultBase<UserBean>> register(String userName,String password,String email){
        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType( Constants.REGISTER_TYPE_APP_NORMAL);
        return service.register(userBean);
    }
    /**
     * 修改密码
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     * @return
     */

    public Observable<ResultBase<String>> getGPassword( String oldpassword,
                                                        String newpassword,
                                                        String confirmpassword){
        return service.getGPassword(sharedPreferencesHelper.getUserToke(),oldpassword,newpassword,confirmpassword);
    }


}
