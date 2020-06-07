package com.example.hiot_cloud.data;

import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.test.networktest.UserBean;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */
public interface NetworkService {
    public static final String BASE_URL = "http://114.67.88.191:8080/";

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     * @return
     */
    @POST("/auth/login")
    Observable<ResultBase<LoginResultDTO>> login(@Query("username") String userName,
                                                 @Query("password") String password,
                                                 @Query("loginCode") String loginCode);

    /**
     * 注销
     * @param authorization
     * @return
     */
    @POST("/auth/logout")
    Observable<ResultBase>logout(@Header("Authorization") String authorization);
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

    /**
     * 注册
     * @param userBean
     * @return
     */
    @POST("/user/register")
    Observable<ResultBase<UserBean>> register(@Body UserBean userBean);

    /**
     * 修改密码
     * @param authorization
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     * @return
     */
    @PUT("/user/password")
    Observable<ResultBase<String>> getGPassword(@Header("Authorization") String authorization,
                                                @Query("oldpassword") String oldpassword,
                                                @Query("newpassword") String newpassword,
                                                @Query("confirmpassword") String confirmpassword);

    @POST("/user/img")
    @Multipart
    Observable<ResultBase<String>>uploadImage(@Part MultipartBody.Part file,
                                              @Header("Authorization") String authorization);



}
