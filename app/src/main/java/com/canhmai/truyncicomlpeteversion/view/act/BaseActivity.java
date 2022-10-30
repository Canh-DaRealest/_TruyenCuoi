package com.canhmai.truyncicomlpeteversion.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.callback.OnMainCallBack;
import com.canhmai.truyncicomlpeteversion.view.fragment.BaseFragment;

public abstract class BaseActivity<T extends ViewBinding, V extends ViewModel> extends AppCompatActivity implements OnMainCallBack, View.OnClickListener {
    protected T binding;
    protected V viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(getClassViewModel());
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();

    protected abstract Class<V> getClassViewModel();

    protected abstract T initViewBinding();

    @Override
    public final void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        //do something
    }

    @Override
    public void showFragment(String tag, Object data, boolean isBacked, boolean isChangeAct) {
        try {
            Class<?> instance = Class.forName(tag);
            BaseFragment<?, ?> fragmentInstance = (BaseFragment<?, ?>) instance.newInstance();

            fragmentInstance.setmData(data);
            fragmentInstance.setOnMainCallBack(this);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fr_act_main, fragmentInstance);
            if (isBacked) {
                fragmentTransaction.addToBackStack(null);

            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showNotice(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}
