package com.example.hiot_cloud.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hiot_cloud.utils.Constans;

class MainViewPageAdapter extends FragmentPagerAdapter {

    private int position;

    public MainViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;


        switch (position) {
            case Constans.MAIN_VIEWPAGER_INDEX_MESSAGE:
                //创建消息fragment TODO
                fragment = MessageFragment.newInstance();
                break;
            case Constans.MAIN_FRAGMENT_EQUIPMENT:
                //创建设备fragment
                fragment = EquipmentFragment.newInstance();
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_SCENE:
                //创建场景fragment
                fragment = SceneFragment.newInstance();
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_MINE:
                //创建我的fragment
                fragment = MineFragment.newInstance();
                break;
            default:
        }
        return fragment;
    }



    @Override
    public int getCount() {
        return Constans.MAIN_FRAGMENT_COUNT;
    }
}
