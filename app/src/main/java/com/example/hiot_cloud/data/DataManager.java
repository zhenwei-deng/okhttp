package com.example.hiot_cloud.data;

import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.test.networktest.UserBean;
import com.example.hiot_cloud.utils.Constants;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

        return service.getGPassword(sharedPreferencesHelper.getUserToke(),oldpassword, newpassword, confirmpassword );
    }


    /**
     * 上传图片
     * @param filePath
     */
    public Observable<ResultBase<String>> uploadImage(String filePath) {
        //传参数的时候需要做参数转换\
        //即定义网络封装方法
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create( MediaType.parse( Constants.MULTIPART_FORM_DATA ),file );
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData( "file", file.getName(), requestBody );
        return service.uploadImage( multipartFile, sharedPreferencesHelper.getUserToke() );
    }

    /**
     * 注销
     */
    public Observable<ResultBase> logout() {
       return service.logout( sharedPreferencesHelper.getUserToke() )
               .doOnNext( new Consumer< ResultBase >() {
                   @Override
                   public void accept(ResultBase resultBase) throws Exception {
                       sharedPreferencesHelper.setUserToken("");

                   }
               } );
    }

    /**
     * 我的界面跳转后修改邮箱
     * @param email
     * @return
     */
    public Observable<ResultBase<String>> changeEmail(String email){
        return service.updateEmail(sharedPreferencesHelper.getUserToke(),email);
    }
    /**
     * 我的界面的修改密码
     */

    public Observable<ResultBase<String>> GPassword( String oldpassword,
                                                      String newpassword,
                                                      String confirmpassword){

        return service.getGPassword(sharedPreferencesHelper.getUserToke(),oldpassword, newpassword, confirmpassword )
                .doOnNext( new Consumer< ResultBase< String > >() {
                    @Override
                    public void accept(ResultBase< String > resultBase) throws Exception {

                    }
                } );
    }


    /**
     *设备绑定
     * @param deviceId
     * @return
     */
    public Observable<ResultBase> bindDevice(String deviceId){
        return service.bindDevice( deviceId,sharedPreferencesHelper.getUserToke() );
    }

}
