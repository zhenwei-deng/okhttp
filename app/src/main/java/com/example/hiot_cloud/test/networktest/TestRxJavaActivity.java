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
import com.example.hiot_cloud.data.NetworkService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxJavaActivity extends AppCompatActivity {

    private static final String TAG = "TestRxJavaActivity";
    private Retrofit retrofit;
    private NetworkService service;
    private EditText etToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);
        //创建
        createRetrofit();

        //editText
        etToken = findViewById(R.id.et_rxjava_token);

        //登录
        Button btnLogin = findViewById(R.id.btn_rxjava_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("deng123","abc1234");
            }
        });
        //获取用户信息
        Button btnUserInfo = findViewById(R.id.btn_rxjava_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfo(etToken.getText().toString());
            }
        });
        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_rxjava_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail(etToken.getText().toString(),"deng123@qq.com");

            }
        });
        //注册
        Button btnRegister = findViewById(R.id.btn_rxjava_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
        //修改密码
        Button btnGPassword = findViewById( R.id.btn_rxjava_password );
        btnGPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGPassword( etToken.getText().toString(), "abc1234", "abc123", "abc123" );


            }
        } );
    }

    /**
     * 修改密码
     * @param authorization
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     */

    private void getGPassword(String authorization, String oldpassword, String newpassword, String confirmpassword) {
        service.getGPassword(  authorization, oldpassword, newpassword, confirmpassword  )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Observer< ResultBase< String > >() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase< String > resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {

                            String string = resultBase.getData();
                            Log.d( TAG, "onNext:" + string );

                        }
                        Log.d( TAG, resultBase.getMsg() );
                        if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                            Toast.makeText( TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }
    /**
     * 注册用户
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("guoxl");
        userBean.setEmail("guoxl@qq.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        service.register(userBean ).subscribeOn( Schedulers.io())
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe( new Observer< ResultBase< UserBean > >() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase< UserBean > resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            UserBean userBean = resultBase.getData();
                            Log.d( TAG, "onNext:" + userBean);


                        }
                        Log. d( TAG ,resultBase. getMsg());

                        if(resultBase != null && !TextUtils.isEmpty(  resultBase.getMsg())){
                            Toast.makeText( TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();}



                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onRrror:" + e.getMessage(),e);

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }


    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        service.updateEmail(authorization,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<String> resultBase) {
                        if (resultBase != null && resultBase.getData() != null){
                            String str = resultBase.getData();
                            Log.d(TAG, "onNext: ");
                            Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                        }
                        Log.d(TAG, resultBase.getMsg());
                        if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
                            Toast.makeText(TestRxJavaActivity.this,resultBase.getMsg() , Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.getUserInfo(authorization);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null){
                            UserBean userBean = resultBase.getData();
                            String str = String.format("用户：%s，email：%s",
                                    userBean.getUsername(),userBean.getEmail());
                            Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                        }else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 登录
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        service.login(userName,password,"app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<LoginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            LoginResultDTO loginResult = resultBase.getData();
                            Log.d(TAG, "onNext: " + loginResult.getTokenValue());
                            etToken.setText(loginResult.getTokenValue());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(),e );

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 创建retrofit
     */
    private void createRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }
}
