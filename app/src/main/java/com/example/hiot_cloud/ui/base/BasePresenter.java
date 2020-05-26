package com.example.hiot_cloud.ui.base;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVP架构presenter基类
 */
//定义泛型类型V,继承自BaseView，它的泛型上限必须是BaseView的子类
public class BasePresenter<V extends BaseView> {
    private V view;
    public BasePresenter() {
    }
    public void setView(V view){
        this.view = view;
    }
    public V getView(){
        return view;
    }

    public void destroy(){
        if (viewAttached()){
            view = null;
        }
    }
    public boolean viewAttached(){
        return view != null;
    }

    /**
     * subscrib封装的方法，好处：节省每次都输入 observable.subscribeOn(Schedulers.io())
     *                 .observeOn(AndroidSchedulers.mainThread())
     *                 .unsubscribeOn(Schedulers.io())
     *                 .subscribe(new Observer<T>() {
     *                 这些java的配置
     * @param observable
     * @param callback
     * @param <T>
     */

    public <T>void subscribe(Observable<T> observable, final RequestCallback<T> callback){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callback.onSubscribe(d);
                    }

                    @Override
                    public void onNext(T t) {
                        callback.onNext(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * 回调类
     */
    public abstract class RequestCallback<T>{

        private static final String TAG = "RequestCallback";

        public void onSubscribe(Disposable d) {

        }

        public abstract void onNext(T t);

        public void onError(Throwable e) {
            Log.e(TAG, "onError: ", e);
        }

        public void onComplete() {

        }
    }
}
