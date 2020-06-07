package com.example.hiot_cloud.ui.base;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiot_cloud.App;
import com.example.hiot_cloud.injection.component.ActivityComponent;
import com.example.hiot_cloud.injection.component.ApplicationComponent;
import com.example.hiot_cloud.injection.component.DaggerActivityComponent;
import com.example.hiot_cloud.injection.module.ActivityModule;
import com.example.hiot_cloud.ui.login.LoginActivity;

import javax.inject.Inject;

/**
 * MVP架构基类
 */
public abstract class BaseActivity <V extends BaseView,P extends BasePresenter<V>>extends AppCompatActivity implements BaseView {

    private P presenter;
    /**
     * 活动注入器
     */
    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectIndependencies();
        presenter = createPresenter();
        if(presenter != null) {
            presenter.setView((V) this);
        }
    }
    //定义抽象方法createPresenter
    public abstract P createPresenter();

    public abstract void injectIndependencies();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public ActivityComponent getActivityComponent() {
        if (null == mActivityComponent) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public ApplicationComponent getApplicationComponent() {

        Application application = getApplication();
        App app = (App) application;
        return app.component();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * 实现基类中的登录成功，弹出登录成功
     * @param message
     */
    @Override
    public void showMessage(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();

    }

    /**打开新界面，关闭本界面
     * class<?>表示泛型的类型是通用的
     * @param cls
     */
    protected void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity( intent );
        finish();
    }
    /**打开新界面，不关闭本界面
     * class<?>表示泛型的类型是通用的
     * @param cls
     */
    protected void startActivityWithoutFinish(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity( intent );

    }

    @Override
    public void tokenOut(){
        startActivity( LoginActivity.class );

    }
}
