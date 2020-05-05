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

/**
 * 设备Fragment类
 */
public class EquipmentFragment extends BaseFragment {

    /*
     * 创建fragment实例
     * */
    public static EquipmentFragment newInstance(){
        EquipmentFragment fragment = new EquipmentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectDependencies() {

    }
    /*
     * 实现initView方法。。。
     * */
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvfragment1 = view.findViewById(R.id.TV_fragment);
        tvfragment1.setText("消息");

    }
}
