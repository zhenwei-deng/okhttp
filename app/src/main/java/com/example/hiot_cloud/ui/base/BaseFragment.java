package com.example.hiot_cloud.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hiot_cloud.ui.login.LoginActivity;

import butterknife.ButterKnife;

/**
 * Fragment模版类
 */
public abstract class BaseFragment<V extends BaseView,P extends BasePresenter<V>> extends Fragment implements BaseView {
    private P presenter;

    public abstract P createPresenter();

    public abstract void injectIndependencies();

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        injectIndependencies();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        if(presenter != null){
            presenter.setView((V)this);
        }
        //在view创建之后呢，我们就来绑定它
        ButterKnife.bind( this,view );

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null){
            presenter.destroy();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText( getActivity(),message, Toast.LENGTH_SHORT ).show();
        
    }

    /**打开新界面，关闭本界面
     * class<?>表示泛型的类型是通用的
     * @param cls
     */
    protected void startActivity(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity( intent );
        getActivity().finish();
    }
    /**打开新界面，不关闭本界面
     * class<?>表示泛型的类型是通用的
     * @param cls
     */
    protected void startActivityWithoutFinish(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity( intent );

    }

    @Override
    public void tokenOut() {
        startActivity( LoginActivity.class );
    }
}
