package com.example.hiot_cloud.test.networktest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * retrofit service接口
 */
public interface TestRetrofitService {
//        public static final String baseUrl = "http://www.baidu.com/";
    public static final String baseUrl = "http://114.67.88.191:8080/";

    @GET("/")
    Call<ResponseBody> test();
    @POST("/auth/login")
    Call<ResponseBody> login(@Query("username") String userName, @Query("password") String password, @Query("loginCode") String loginCode);
    @GET("/user/one")
    Call<ResponseBody> getUserInfo(@Header("Authorization") String authorization);

    @GET("/user/one")
    Call<ResultBase<UserBean>> getUserInfo2(@Header("Authorization") String authorization);

    @PUT("/user/email")
    Call<ResponseBody> updateEmail(@Header("Authorization") String authorization,
                                   @Query("email") String email);
    @POST("/auth/login")
    @FormUrlEncoded
    Call<ResponseBody> login2(@Field("username") String userName, @Field("password") String password, @Field("loginCode") String loginCode);
    @POST("/user/register")
    Call<ResponseBody> register(@Body UserBean userBean);
}
