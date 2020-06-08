package com.example.hiot_cloud.utils;

/**
 * 常量类
 */
public class Constants {
    /**
     *
     */
    public static final int MAIN_FRAGMENT_COUNT = 4;
    /**
     *
     */
    public static final int MAIN_VIEWPAGER_INDEX_MESSAGE = 0;
    /**
     *
     */
    public static final int MAIN_VIEWPAGER_INDEX_EQUIPMENT = 1;
    /**
     *
     */
    public static final int MAIN_VIEWPAGER_INDEX_SCENE = 2;
    /**
     *
     */
    public static final int MAIN_VIEWPAGER_INDEX_MINE = 3;
    /**
     * APP登录的loginCode
     */
    public static final String LOGIN_CODE_APP = "app";
    /**
     * APP的注册类型
     */
    public static final String REGISTER_TYPE_APP_NORMAL = "1"; /**
     * 模拟用户名
     */
    public static final String LOGIN_USER_NAME = "1426938113@qq.com";

    /**
     * 模拟密码
     */
    public static final String LOGIN_PASSWORD = "asd123";
    /**
     * 图片详情需要的intent额外参数key
     */
    public static final String INTENT_EXTRAL_PHOTO_URL = "INTENT_EXTRAL_PHOTO_URL";
    /**
     * 服务端返回消息状态属性成功
     */
    public static final int MSG_STATUS_SUCCESS = 1;
    /**
     * 网络访问失败吐司
     */
    public static final String TOAST_MAS_NETWORK_FAIL ="当前网络无法访问，请稍后再试" ;

    /**
     * 网络登录成功吐司
     */
    public static final String DENGLU_SUCCESS = "登录成功";
    /**
     *form-data类型
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data" ;
    /**
     * token失效状态
     */
    public static final int MSG_STATUS_TOKEN_OUT = -100;
    /**
     * 设备已绑定状态
     */
    public static final int DEVICE_STATUS_BINDED = 1;

    /**
     * 设备未绑定状态
     */
    public static final int DEVICE_STATUS_UNBINDED = 0;
}
