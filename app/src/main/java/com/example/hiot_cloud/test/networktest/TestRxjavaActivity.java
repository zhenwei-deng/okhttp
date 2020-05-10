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
import com.example.hiot_cloud.data.NetService;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxjavaActivity extends AppCompatActivity {

    private static final String TAG = "TestRxjavaActivity";
    private Retrofit retrofit;

    private NetService service;
    private EditText etToken;
    private Object UserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_test_rxjava );

        //创建retrofit
        createrRetrofit();

        //editText
        etToken = findViewById( R.id.et_rxjava_token );



        //登录
        Button btnLogin = findViewById( R.id.btn_rxjava_login );
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("ab123","abc123");

            }
        } );

        //用户信息
         Button btnUserInfo = findViewById( R.id.btn_rxjava_userinfo);
         btnUserInfo.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //getUserInfo("authorization");
                 getUserInfo(etToken.getText().toString());


             }
         } );

        //修改邮箱
        Button btnUpdateEmail = findViewById( R.id.btn_rxjava_update_email );
        btnUpdateEmail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateEmail("28e50ffedbf84f2a8be44612e9a44fba_d519c19f7e8f4cf39dc2f46c9a37b690_use","apptest123456@qq.com");


            }
        } );


        //注册
        Button btnRegister = findViewById( R.id.btn_rxjava_register );
        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register( );

            }
        } );

    }

    /**
     * 注册
     *
     */

    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("apptest1234567");
        userBean.setEmail("apptest1234567890@qq.com");
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
                              if (resultBase != null && resultBase.getData() != null && UserBean != null) {
                              UserBean userBean1 = resultBase.getData();
                              Log.d( TAG, "onNext:" );

                        }
                        Log. d( TAG ,resultBase. getMsg()) ;

                        if(resultBase != null && !TextUtils.isEmpty(  resultBase.getMsg())){
                            Toast.makeText( TestRxjavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();}



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
     */
    private void getUpdateEmail(String authorization, String email) {
        service.updateEmail(authorization, email)
        .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe( new Observer< ResultBase< String > >() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase< String > resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            String string = resultBase.getData();
                            Log.d( TAG, "onNext:"  );


                        }
                        if(resultBase != null && !TextUtils.isEmpty(  resultBase.getMsg())){
                            Toast.makeText( TestRxjavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();}


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
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Observable< ResultBase< UserBean > > observable = service.getUserInfo( authorization );
        observable.observeOn( AndroidSchedulers.mainThread() ).subscribeOn( Schedulers.io() )
                .subscribe( new Observer< ResultBase< UserBean > >() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase< UserBean > resultBase) {

                        if(resultBase != null && resultBase.getData() != null){
                            UserBean userBean = resultBase.getData();
                            String str = String.format( "用户: %s,email: %s ",
                                    userBean.getUsername(),userBean.getEmail());
                            Toast.makeText( TestRxjavaActivity.this, str, Toast.LENGTH_SHORT ).show();
                        }
                        if(resultBase != null && !TextUtils.isEmpty(  resultBase.getMsg())){
                            Toast.makeText( TestRxjavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();
                        }

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
     * 登录
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        service.login( userName,password,"app" )
                .subscribeOn( Schedulers.io() )// 异步处理http请求
                .observeOn( AndroidSchedulers.mainThread())// 在UI线程处理结果
                .subscribe( new Observer< ResultBase< LoginResultDTO > >() {    // 订阅
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase< LoginResultDTO > resultBase) {

                        if (resultBase != null && resultBase.getData() != null) {
                            LoginResultDTO loginResult = resultBase.getData();
                            Log.d( TAG, "onNext:" + loginResult.getTokenValue() );
                            etToken.setText( loginResult.getTokenValue() );
                        }

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
     * 创建retrofit
     */
    private void createrRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(NetService.class);
    }
}
