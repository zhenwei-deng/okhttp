package com.example.hiot_cloud.ui.opinion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOpinionActivity extends BaseActivity< MyOpinionView, MyOpinionPresenter > implements MyOpinionView {

    @Inject
    MyOpinionPresenter presenter;

    @BindView(R.id.myinfo_back)
    ImageView myinfoBack;

    @BindView(R.id.myinfo_username)
    TextView myinfoUsername;

    @BindView(R.id.myinfo_pushnum)
    TextView myinfoPushnum;

    @BindView(R.id.myinfo_scannum)
    TextView myinfoScannum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_opinion );
        ButterKnife.bind( this );
    }


    @Override
    public MyOpinionPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependencies() {
        getActivityComponent().inject( this );

    }

    /**
     * 点击返回我的主界面跳转操作，返回发到我的界面
     */
    public void returnmine() {
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
        finish();

    }

    @OnClick({R.id.myinfo_back, R.id.myinfo_username, R.id.myinfo_pushnum, R.id.myinfo_scannum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myinfo_back:

                /**
                 * 点击返回我的主界面按钮
                 */
                returnmine();
                break;
            case R.id.myinfo_username:
                break;
            case R.id.myinfo_pushnum:
                break;
            case R.id.myinfo_scannum:
                break;
        }
    }
}
