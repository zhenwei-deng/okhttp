package com.example.hiot_cloud.ui.devicelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.data.bean.DeviceBean;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.base.BaseFragment;
import com.example.hiot_cloud.ui.scan.ScanActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备Fragment类
 */
public class DeviceListFragment extends BaseFragment< DeviceListView, DeviceListPresenter > implements DeviceListView {

    @Inject
    DeviceListPresenter presenter;

    @BindView(R.id.iv_device_list_add)
    ImageView ivDeviceList;

    @BindView(R.id.rv_device_list)
    RecyclerView rvDeviceList;

    @BindView(R.id.srl_device_list)
    SwipeRefreshLayout srlDeviceList;

    @BindView(R.id.tv_device_list_nodata)
    TextView tvDeviceListNodata;
    private DeviceListAdapter deviceListAdapter;

    /**
     * fragment实例
     *
     * @return
     */
    public static DeviceListFragment newInstance() {
        DeviceListFragment fragment = new DeviceListFragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public DeviceListPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependencies() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).getActivityComponent().inject( this );
        }
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_device_list, container, false );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
//        TextView tvFragmentMain = view.findViewById( R.id.tv_fragment_main );
//        tvFragmentMain.setText( "设备" );

        initWedget();


    }


    @OnClick(R.id.iv_device_list_add)
    public void onViewClicked() {
        startActivityWithoutFinish( ScanActivity.class );
    }

    /**
     * 初始化控件
     */
    private void initWedget() {
        //初始化文档框
        tvDeviceListNodata.setVisibility( View.VISIBLE );

        //初始化下拉控件
        srlDeviceList.setColorSchemeColors(
                getResources().getColor( android.R.color.holo_red_dark ),
                getResources().getColor( android.R.color.holo_green_dark ),
                getResources().getColor( android.R.color.holo_blue_dark )
        );
        //当它下拉刷新的时候，会触发事件
        srlDeviceList.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //调用加载
                loadDeviceList();

            }
        } );


        //初始化列表
        //线性布局管理器LinearLayoutManager
        rvDeviceList.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        rvDeviceList.setHasFixedSize( true );
        deviceListAdapter = new DeviceListAdapter( getActivity() );
        //设置适配器
        rvDeviceList.setAdapter( deviceListAdapter );

    }

    @Override
    public void onResume() {
        super.onResume();
        loadDeviceList();
    }

    /**
     * 加载设备列表
     */
    private void loadDeviceList() {
        //请求设备列表
        srlDeviceList.setRefreshing( true );
        presenter.loadDeviceList();

        //请求完后刷新用户界面的列表
        srlDeviceList.setRefreshing( false );
    }

    /**
     * 显示设备列表
     *
     * @param deviceList
     */
    @Override
    public void showDeviceList(List< DeviceBean > deviceList) {
        if (deviceList != null && !deviceList.isEmpty()) {
            tvDeviceListNodata.setVisibility( View.GONE );
        } else {
            tvDeviceListNodata.setVisibility( View.VISIBLE );
        }
        deviceListAdapter.setData( deviceList );

    }
}
