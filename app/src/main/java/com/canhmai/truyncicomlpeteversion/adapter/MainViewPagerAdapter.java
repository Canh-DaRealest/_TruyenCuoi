package com.canhmai.truyncicomlpeteversion.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.canhmai.truyncicomlpeteversion.view.fragment.A00SettingFragment;
import com.canhmai.truyncicomlpeteversion.view.fragment.A005FavouriteFragment;


public class MainViewPagerAdapter extends FragmentStateAdapter {
    private final String[] listTabName = {"Danh mục", "Yêu thích"};

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MainViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new A00SettingFragment();
            case 1:
                return new A005FavouriteFragment();
            default:
                return new A00SettingFragment();
        }

    }

    @Override
    public int getItemCount() {
        return listTabName.length;
    }
}
