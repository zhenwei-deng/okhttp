package com.example.hiot_cloud.data;

import com.example.hiot_cloud.data.bean.DeviceBean;
import com.example.hiot_cloud.data.bean.DeviceDetailBean;
import com.example.hiot_cloud.data.bean.UserBean;
import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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


    /**
     * 绑定设备
     * @param device_pk    设备id
     * @param authorization
     * @return
     */
    @POST("/holder/device/{device_pk}")
    Observable<ResultBase> bindDevice (@Path("device_pk")String device_pk,
                                        @Header("Authorization") String authorization);


    /**
     * 接口查询持有者绑定设备
     *
     * @param bonding
     * @param authorization
     * @return
     */
    @GET("/holder/user")
    Observable< ResultBase< List< DeviceBean > > > listBindedDevice(@Query("bonding") int bonding,
                                                                    @Header("Authorization") String authorization);



    @GET("/device/{id}")
    Observable<ResultBase< DeviceDetailBean >> getDeviceDetail(@Path("id") String deviceId,
                                                               @Header("Authorization") String authorization);

    /**
     * 控制通道开关
     *
     * @param dataStreamId
     * @param status
     * @param authorization
     * @return
     */
    @POST("/downdatastream/switch/{downdatastream_pk}")
    Observable<ResultBase> changeSwitch(@Path("downdatastream_pk") String dataStreamId,
                                        @Query("status") int status,
                                        @Header("Authorization") String authorization);
}
