package com.example.hiot_cloud.ui.base;

import com.example.hiot_cloud.ui.base.BaseView;

/*
 * MVP架构Presenter层基类
 * */
public class BasePresenter <V extends BaseView >{
    private V view;

    public BasePresenter() {

    }
    public void setView(V view){
        this.view=view;
    }
    public V getView(){
        return view;
    }
    //定义销毁方法，释放view
    public void destroy(){
        if (viewAttached()){
            view = null;
        }
    }
    public  boolean viewAttached(){
        return  view !=null;
    }
}
