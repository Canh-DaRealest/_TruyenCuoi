package com.canhmai.truyncicomlpeteversion.view.fragment;

import static com.canhmai.truyncicomlpeteversion.MySharePreference.NIGH_MODE;
import static com.canhmai.truyncicomlpeteversion.MySharePreference.SAVE_BG_COLOR;
import static com.canhmai.truyncicomlpeteversion.MySharePreference.SAVE_TEXT_COLOR;
import static com.canhmai.truyncicomlpeteversion.MySharePreference.SAVE_TEXT_SIZE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;

import com.canhmai.truyncicomlpeteversion.App;
import com.canhmai.truyncicomlpeteversion.MySharePreference;
import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.databinding.A00SettingfragmentBinding;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.MainViewModel;

import java.util.List;

public class A00SettingFragment extends BaseFragment<com.canhmai.truyncicomlpeteversion.databinding.A00SettingfragmentBinding, MainViewModel> {
    public static final String TAG = A00SettingFragment.class.getName();

    private List<Story> storyList;

    @Override
    protected void initViews() {
        mBinding.btnDarkTheme.setChecked(App.getInstance().getStorage().isModeNigh);

        mBinding.btnDarkTheme.setChecked(App.getInstance().getStorage().isModeNigh);
        mBinding.btnDarkTheme.setOnClickListener(this);
        mBinding.setbackgroundColor.setOnClickListener(this);
        mBinding.setTextColor.setOnClickListener(this);
        mBinding.setTextSize.setOnClickListener(this);
        mBinding.btSettingBack.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        switch (v.getId()) {
            case R.id.btn_darkTheme:
                enableDarkTheme();
                break;
            case R.id.setTextSize:

                showDialogTextSize();
                break;

            case R.id.setTextColor:

                showDialogTextColor();
                break;
            case R.id.setbackgroundColor:

                showDialogBGColor();
                break;
            case R.id.bt_setting_back:
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.onBackPressed();
                break;
        }
    }

    private void enableDarkTheme() {
        if (App.getInstance().getStorage().isModeNigh) {
            mBinding.btnDarkTheme.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            MySharePreference.getInstance().setBooleanValue(NIGH_MODE, false);
            App.getInstance().getStorage().isModeNigh = false;
        } else {
            mBinding.btnDarkTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            MySharePreference.getInstance().setBooleanValue(NIGH_MODE, true);
            App.getInstance().getStorage().isModeNigh = true;
        }

    }

    private void showDialogBGColor() {
        String[] listSize = {"Tr???ng", "V??ng"};
        String[] value = new String[]{App.getInstance().getStorage().background_color};
        if (value[0] == null) {
            value[0] = "Tr???ng";
        }
        int index = 0;

        if (value[0].equals("V??ng")) {
            index = 1;
        }
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());

        alBuilder.setSingleChoiceItems(listSize, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                value[0] = listSize[which];
            }
        });

        alBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (value[0].equals("Tr???ng")) {
                    App.getInstance().getStorage().background_color = "Tr???ng";
                } else if (value[0].equals("V??ng")) {
                    App.getInstance().getStorage().background_color = "V??ng";
                }
                MySharePreference.getInstance().putStringValue(SAVE_BG_COLOR, App.getInstance().getStorage().background_color);
            }
        });
        alBuilder.show();
    }

    private void showDialogTextColor() {
        String[] listSize = {"??en", "V??ng"};
        String[] value = new String[]{App.getInstance().getStorage().text_color};
        if (value[0] == null) {
            value[0] = "??en";
        }
        int index = 0;

        if (value[0].equals("V??ng")) {
            index = 1;
        }
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());

        alBuilder.setSingleChoiceItems(listSize, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                value[0] = listSize[which];
            }
        });

        alBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (value[0].equals("??en")) {
                    App.getInstance().getStorage().text_color = "??en";
                } else if (value[0].equals("V??ng")) {
                    App.getInstance().getStorage().text_color = "V??ng";
                }
                MySharePreference.getInstance().putStringValue(SAVE_TEXT_COLOR, App.getInstance().getStorage().text_color);
            }
        });
        alBuilder.show();
    }

    private void showDialogTextSize() {
        String[] listSize = {"Nh???", "L???n"};
        String[] value = new String[]{App.getInstance().getStorage().text_size};
        if (value[0] == null) {
            value[0] = "Nh???";
        }
        int index = 0;

        if (value[0].equals("L???n")) {
            index = 1;
        }
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());

        alBuilder.setSingleChoiceItems(listSize, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                value[0] = listSize[which];
            }
        });

        alBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (value[0].equals("Nh???")) {
                    App.getInstance().getStorage().text_size = "Nh???";
                } else if (value[0].equals("L???n")) {
                    App.getInstance().getStorage().text_size = "L???n";
                }
                MySharePreference.getInstance().putStringValue(SAVE_TEXT_SIZE, App.getInstance().getStorage().text_size);
            }
        });
        alBuilder.show();
    }

    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected A00SettingfragmentBinding intitFragmentBinding(LayoutInflater inflater) {
        return A00SettingfragmentBinding.inflate(inflater);
    }
}
