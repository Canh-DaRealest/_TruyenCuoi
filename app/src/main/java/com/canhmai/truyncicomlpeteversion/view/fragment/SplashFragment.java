package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.os.Handler;
import android.view.LayoutInflater;

import com.canhmai.truyncicomlpeteversion.App;

import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.CommonViewModel;
import com.example.truyencuoi.R;
import com.example.truyencuoi.databinding.SplashFragmentBinding;

public class SplashFragment extends BaseFragment<SplashFragmentBinding, CommonViewModel> {
    public static final String TAG = SplashFragment.class.getName();

    @Override
    protected void initViews() {

        boolean state = App.getInstance().getStorage().isModeNigh;

            MainActivity mainActivity = (MainActivity) mContext;
            mainActivity.getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color));



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onMainCallBack.showFragment(A001MainFragment.TAG, null, false, false);
            }
        }, 1500);
    }

    @Override
    protected Class<CommonViewModel> getClassViewModel() {
        return CommonViewModel.class;
    }

    @Override
    protected SplashFragmentBinding intitFragmentBinding(LayoutInflater inflater) {
        return SplashFragmentBinding.inflate(inflater);
    }


}
