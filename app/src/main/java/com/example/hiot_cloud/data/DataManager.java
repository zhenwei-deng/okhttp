package com.example.hiot_cloud.data;

import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.test.networktest.UserBean;
import com.example.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 网络请求封装类
 */
public class DataManager {

    private NetworkService service;

    @Inject
    public DataManager(NetworkService service) {
        this.service = service;
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Observable<ResultBase<LoginResultDTO>> login(String userName,String password){
        return service.login(userName,password, Constants.LOGIN_CODE_APP);
    }

    /**
     * 获取用户信息
     * @param authorization
     * @return
     */
    public Observable<ResultBase<UserBean>> getUserInfo(String authorization){
        return service.getUserInfo(authorization);
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     * @return
     */
    public Observable<ResultBase<String>> updateEmail(String authorization,String email){
        return service.updateEmail(authorization,email);
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
     * @param authorization
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     * @return
     */

    public Observable<ResultBase<String>> getGPassword( String authorization,
                                                        String oldpassword,
                                                        String newpassword,
                                                        String confirmpassword){
        return service.getGPassword(authorization,oldpassword,newpassword,confirmpassword);
    }
}
