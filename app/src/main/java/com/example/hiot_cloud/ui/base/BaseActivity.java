package com.example.hiot_cloud.ui.base;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.example.hiot_cloud.injection.module.ActivityModule;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiot_cloud.App;
import com.example.hiot_cloud.injection.component.ActivityComponent;
import com.example.hiot_cloud.injection.component.ApplicationComponent;
import com.example.hiot_cloud.injection.component.*;


/**
 * MVP架构Activity的实现类
 */

//定义一个换型参数V view 视图，和P presenter,
//与BasePresenter中相对应    public class BasePresenter<V extends BaseView> {
//V extends BaseView 对应的是BasePresenter中的BaseView
//P extends BasePresenter对应的是BasePresenter中的BasePresenter

//这样就实现了BaseActivity中的泛型定义
//分别定义了视图和主持者泛型类型
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter > extends AppCompatActivity implements BaseView {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        //首先重写AppCompatActivity事件
        super.onCreate( savedInstanceState, persistentState );
    }


    //private P Presenter代表泛型类，而不是private BasePresenter；
    private P presenter;
    /**
     * 活动注入器
     */
    private ActivityComponent mActivityComponent;
    //onCreate事件中调用setView/getView方法

    //因为setView/getView方法都是presenter类的，
    //所以要用private调用它
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //使用基类的时候要先获取createPresenter();
        //才能用presenter它。
        // 基类的类变量presenter 就获得了值，才能调用presenter.setView(this);
        presenter = createPresenter();
        //setView中的View的视图层中的实现类是BaseActivity。
        //故this就代表传入的BaseActivity
        if (presenter !=null) {
            presenter.setView( this );
        }
    }

    //定义抽象方法createPresenter
    //createPresenter创建
    //BaseActivity抽象类中也必须有abstract这个关键字，使BaseActivity抽象化
    //抽象类
    public abstract P createPresenter();
    public abstract void injectIndependies();

    //重写onDestroy方法，调用presenter.destroy();
    //onDestroy销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //调用presenter
        presenter.destroy();


    }

    //重写onPause、onStop、onResume属于AppCompatActivity生命周期中的不同事件
    //onPause暂停
    //onStop停止
    //onResume恢复

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



}
