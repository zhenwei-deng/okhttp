package com.example.hiot_cloud.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.ui.base.BaseFragment;
import com.example.hiot_cloud.ui.base.BasePresenter;

public class MineFragment extends BaseFragment {
    /**
     * fragment实例
     * @return
     */
    public static MineFragment newInstance(){
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependencies() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvFragmentMain = view.findViewById(R.id.tv_fragment_main);
        tvFragmentMain.setText("我的");
    }
}
