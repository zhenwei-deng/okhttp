package com.example.hiot_cloud.ui.main;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.ui.base.BaseActivity;
import com.example.hiot_cloud.ui.base.BasePresenter;
import com.example.hiot_cloud.utils.Constans;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        Log.d("tag","test");

        //设置Viewpager
        final ViewPager VP = findViewById(R.id.VR_main);
        //适配器MainViewPageAdapter
        VP.setAdapter(new MainViewPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        VP.setOffscreenPageLimit( Constans.MAIN_FRAGMENT_COUNT);
        RadioGroup rgMain = findViewById(R.id.RG_main);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RB_message:
                        VP.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MESSAGE);
                        break;
                    case R.id.RB_equipment:
                        VP.setCurrentItem(Constans.MAIN_FRAGMENT_EQUIPMENT);
                        break;
                    case R.id.RB_scene:
                        VP.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_SCENE);
                        break;
                    case R.id.RB_mine:
                        VP.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MINE);
                        break;
                    default:
                }
            }
        });
    }




    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }
}