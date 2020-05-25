package com.example.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hiot_cloud.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okHttp框架测试类
 */
public class TestOkHttpActivity extends AppCompatActivity {
//    private static final String baseUrl = "http://www.baidu.com";
    private static final String baseUrl = "http://114.67.88.191:8080";
    private static final String TAG = "TestOkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_http);

        //execute方法测试
        Button btnExecute = findViewById(R.id.btn_okhttp_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testExecute();

            }
        });
        //enqueue方法测试
        Button btnEnqueue = findViewById(R.id.btn_okhttp_enqueue);
        btnEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testEnqueue();
            }
        });
        //登录测试
        Button btnLogin = findViewById(R.id.btn_okhttp_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("deng123","abc123","app");
            }
        });

        //获取用户信息
        Button btnUserinfo = findViewById(R.id.btn_okhttp_userinfo);
        btnUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfo("e907ffd13e4441a7bd31cf51f6e494f3_55908e522f8242ec940bfea7da697fa2_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_okhttp_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail("e907ffd13e4441a7bd31cf51f6e494f3_55908e522f8242ec940bfea7da697fa2_use","guoxiling@qq.com");
            }
        });
    }


    /**
     * 测试execute方式
     */
    private void testExecute() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(baseUrl).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "run: "+response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "testExecute: "+e.getMessage(),e );
                }
            }
        }.start();
    }

    /**
     * 测试enqueue方式
     */
    private void testEnqueue() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl).build();
        callRequeue(okHttpClient, request);
    }

    private void callRequeue(OkHttpClient okHttpClient, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = baseUrl + "/user/email?email=" + email;
        Request request = new Request.Builder().put(body).url(url).header("Authorization",authorization).build();
        callRequeue(okHttpClient, request);
    }
    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = baseUrl + "/user/one";
        Request request = new Request.Builder().get().url(url).header("Authorization",authorization).build();
        callRequeue(okHttpClient, request);
    }
    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = baseUrl + String.format("/auth/login?username=%s&password=%s&loginCode=%s",
                userName,password,loginCode);
        Request request = new Request.Builder().post(body).url(url).build();
        callRequeue(okHttpClient, request);
    }
}
