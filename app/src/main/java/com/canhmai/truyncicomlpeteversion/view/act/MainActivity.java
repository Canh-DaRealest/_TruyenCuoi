package com.canhmai.truyncicomlpeteversion.view.act;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.canhmai.truyncicomlpeteversion.App;
import com.canhmai.truyncicomlpeteversion.MySharePreference;
import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.databinding.ActivityMainBinding;
import com.canhmai.truyncicomlpeteversion.view.fragment.A003DetailFragment;
import com.canhmai.truyncicomlpeteversion.view.fragment.A001MainFragment;
import com.canhmai.truyncicomlpeteversion.view.fragment.SplashFragment;
import com.canhmai.truyncicomlpeteversion.viewmodel.CommonViewModel;

import java.util.ArrayList;


public class MainActivity extends BaseActivity<ActivityMainBinding, CommonViewModel> {


    @Override
    protected void initViews() {

        getWindow().setStatusBarColor(getColor(R.color.dark));

        updateList();
        checkFirstInstall();
        App.getInstance().getStorage().randomStoryList = new ArrayList<>();
        App.getInstance().getStorage().randomStoryList = App.getInstance().getAppDB().getStoriesDAO().get20Story();

        showFragment(SplashFragment.TAG, null, false, false);
    }

    private void checkFirstInstall() {
        boolean value = MySharePreference.getInstance().getBooleanValue(MySharePreference.FIRST_INSTALL);
        if (!value) {
            App.getInstance().getStorage().text_size = "Nhỏ";
            App.getInstance().getStorage().background_color = "Trắng";
            App.getInstance().getStorage().text_color = "Đen";

            MySharePreference.getInstance().setBooleanValue(MySharePreference.FIRST_INSTALL, true);
        } else {
            App.getInstance().getStorage().text_size = MySharePreference.getInstance().getStringValue(MySharePreference.SAVE_TEXT_SIZE);
            if (App.getInstance().getStorage().text_size == null)
                App.getInstance().getStorage().text_size = "Nhỏ";


            App.getInstance().getStorage().background_color = MySharePreference.getInstance().getStringValue(MySharePreference.SAVE_BG_COLOR);
            if (App.getInstance().getStorage().background_color == null)
                App.getInstance().getStorage().background_color = "Trắng";
            App.getInstance().getStorage().text_color = MySharePreference.getInstance().getStringValue(MySharePreference.SAVE_TEXT_COLOR);
            if (App.getInstance().getStorage().text_color == null) ;
            App.getInstance().getStorage().text_color = "Đen";
        }
    }

    private void updateList() {
        App.getInstance().getStorage().charList = new ArrayList<>();
        App.getInstance().getStorage().charList.add("A");
        App.getInstance().getStorage().charList.add("Ă");
        App.getInstance().getStorage().charList.add("Â");
        App.getInstance().getStorage().charList.add("B");
        App.getInstance().getStorage().charList.add("C");
        App.getInstance().getStorage().charList.add("D");
        App.getInstance().getStorage().charList.add("Đ");
        App.getInstance().getStorage().charList.add("E");
        App.getInstance().getStorage().charList.add("Ê");
        App.getInstance().getStorage().charList.add("G");
        App.getInstance().getStorage().charList.add("H");
        App.getInstance().getStorage().charList.add("I");
        App.getInstance().getStorage().charList.add("K");
        App.getInstance().getStorage().charList.add("L");
        App.getInstance().getStorage().charList.add("M");
        App.getInstance().getStorage().charList.add("N");
        App.getInstance().getStorage().charList.add("O");
        App.getInstance().getStorage().charList.add("Ô");
        App.getInstance().getStorage().charList.add("Ơ");
        App.getInstance().getStorage().charList.add("P");
        App.getInstance().getStorage().charList.add("Q");
        App.getInstance().getStorage().charList.add("R");
        App.getInstance().getStorage().charList.add("S");
        App.getInstance().getStorage().charList.add("T");
        App.getInstance().getStorage().charList.add("U");
        App.getInstance().getStorage().charList.add("Ư");
        App.getInstance().getStorage().charList.add("V");
        App.getInstance().getStorage().charList.add("X");
        App.getInstance().getStorage().charList.add("Y");
    }

    @Override
    protected Class<CommonViewModel> getClassViewModel() {
        return CommonViewModel.class;
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onBackPressed() {


        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onRestart() {
        Log.e("RRRRRRRRR", "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("RRRRRRRRR", "onDestroy: ");

    }

    @Override
    public void onDetachedFromWindow() {
        Log.e("RRRRRRRRR", "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
    }

    @Override
    protected void onStop() {
        Log.e("RRRRRRRRR", "onStop: ");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.e("RRRRRRRRR", "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.e("RRRRRRRRR", "onStart: ");
        super.onStart();
    }

}