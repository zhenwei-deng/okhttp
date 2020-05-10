package com.example.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiot_cloud.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "TestRetrofitActivity";
    private Retrofit retrofit;
    private TestRetrofitService service;
    private Gson gson = new Gson();
    private EditText etToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);


        //全局取到edit_token
        etToken = findViewById( R.id.et_token_retrofit );

        //创建retrofit和service对象
        createrRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_enqueue);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();

            }
        });

        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("ab123","abc123","app");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        //被观察者btnUserInfo
        //观察者OnClickListener
        //订阅setOnClickListener   ，将被观察者btnUserInfo和观察者OnClickListener联系起来
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            //点击事件onClick
            //在事件中我们就能做业务逻辑
            public void onClick(View v) {
                getUserInfo("28e50ffedbf84f2a8be44612e9a44fba_ac4af0e9fa6c4b28bd0474547ea0a6c8_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("28e50ffedbf84f2a8be44612e9a44fba_ac4af0e9fa6c4b28bd0474547ea0a6c8_use","apptest123456@qq.com");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });
    }

    /**
     * 注册用户
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("apptest123456");
        userBean.setEmail("apptest123456789@qq.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        Call< ResponseBody > call = service.register(userBean);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
//        Call<ResponseBody> call = service.getUserInfo(authorization);
//        callEnqueueLogin( call );

        Call< ResultBase< UserBean > > call = service.getUserInfo2( authorization );
        call.enqueue( new Callback< ResultBase< UserBean > >() {
            @Override
            public void onResponse(Call< ResultBase< UserBean > > call, Response< ResultBase< UserBean > > response) {
                 ResultBase<UserBean> resultBase =  response.body();
                 if (resultBase != null && resultBase.getData() != null){
                     resultBase.getData();
                     String str = resultBase.getData().getUsername() + "," + resultBase.getData().getEmail();
                     Toast.makeText( TestRetrofitActivity.this, "", Toast.LENGTH_SHORT ).show();
                 }
            }

            @Override
            public void onFailure(Call< ResultBase< UserBean > > call, Throwable t) {

            }
        } );
//        call.enqueue(new Callback<ResponseBody>() {
//
//
//
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Log.d(TAG, "onResponse: " + response.body().string());
//                } catch (IOException e) {
//                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage(), t);
//            }
//        });
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName,password,loginCode);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                   Log.d(TAG, "onResponse: " + response.body().string());

                    } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });

    }
    private void callEnqueueLogin(Call<ResponseBody> call) {

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

//                    Log.d(TAG, "onResponse: " + response.body().string());

                    if (response.body() != null) {
                        Type type = new TypeToken< ResultBase< LoginResultDTO > >() {
                    }.getType();
                        ResultBase< LoginResultDTO > loginResult = null;
                        try {
                            loginResult = gson.fromJson( response.body().string(), type );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (loginResult != null && loginResult.getData() != null){
                        String token = loginResult.getData().getTokenValue();
                        etToken.setText(token);


                    } else if (loginResult != null && !TextUtils.isEmpty( loginResult.getMsg() ) ){
                            Toast.makeText( TestRetrofitActivity.this, loginResult.getMsg(), Toast.LENGTH_SHORT ).show();
                        }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });

    }
    private void callEnqueueUserInfo(Call<ResponseBody> call) {

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
 //                   Log.d(TAG, "onResponse: " + response.body().string());

                    Type type =new TypeToken<ResultBase<UserBean>>(){}.getType();
                    ResultBase<UserBean> resultBase = gson.fromJson( response.body().string(), type );
                    if (resultBase != null && resultBase.getData() != null) {
                        UserBean userBean = resultBase.getData();

                        String str = String.format( "用户名: %s,密码: %s， email: %s, 用户类型： %s",
                                userBean.getUsername(), userBean.getPassword(), userBean.getEmail(), userBean.getUserType() );
                        Toast.makeText( TestRetrofitActivity.this, str, Toast.LENGTH_SHORT ).show();
                    }
//                    if(resultBase != null && resultBase.getMsg() != null){
//                        Toast.makeText( TestRetrofitActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();
//                    }
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });

    }

    //创建方法   工厂
    private void createrRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory( GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }

}
