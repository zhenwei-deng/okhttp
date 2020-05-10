package com.example.hiot_cloud.data;

import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.test.networktest.UserBean;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */
public interface NetService {
    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     * @return
     */
    @POST("/auth/login")
    //返回的都是被观察者Observable
    //最终要返回的对象补给它
    Observable< ResultBase< LoginResultDTO > > login(@Query("username") String userName, @Query("password") String password,
                                                     @Query("loginCode") String loginCode);

    /**
     * 获取用户信息
     * @param authorization
     * @return
     */
    @GET("/user/one")
    Observable<ResultBase<UserBean>> getUserInfo(@Header("Authorization") String authorization);

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     * @return
     */
    @PUT("/user/email")
    Observable<ResultBase<String>> updateEmail(@Header("Authorization") String authorization,
                                   @Query("email") String email);
    Observable< ResultBase< String>> updateEmail(String authorization);

    /**
     * 注册
     * @param userBean
     * @return
     */
    @POST("/user/register")
    Observable<ResultBase<UserBean>> register(@Body UserBean userBean);


}
