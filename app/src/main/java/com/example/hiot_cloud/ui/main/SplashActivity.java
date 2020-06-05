package com.example.hiot_cloud.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.R;
import com.example.hiot_cloud.data.SharedPreferencesHelper;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.base.BasePresenter;
import com.example.hiot_cloud.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private static final int HANDLER_MSG_OPEN_NEW = 1;
    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //如果已登录，跳转主界面
            Intent intent =null;
            if(!TextUtils.isEmpty(  sharedPreferencesHelper.getUserToke())){
                 intent = new Intent(SplashActivity.this,MainActivity.class);
            }else {
                //如果未登录，跳转登录界面
                 intent = new Intent( SplashActivity.this, LoginActivity.class );
            }

            startActivity(intent);
            finish();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置定时器

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessageAtTime(HANDLER_MSG_OPEN_NEW,3000);
            }
        }, 3000);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependencies() {
        getActivityComponent().inject( this );
    }
}
