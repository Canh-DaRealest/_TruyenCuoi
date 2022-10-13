package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.canhmai.truyncicomlpeteversion.callback.OnMainCallBack;

public abstract class BaseFragment<T extends ViewBinding, V extends ViewModel> extends Fragment implements View.OnClickListener {
    protected T mBinding;
    protected V mViewModel;
    protected Object mData;
    protected Context mContext;
    protected OnMainCallBack onMainCallBack;

    public void setmData(Object mData) {
        this.mData = mData;
    }

    public void setOnMainCallBack(OnMainCallBack onMainCallBack) {
        this.onMainCallBack = onMainCallBack;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = intitFragmentBinding(inflater);
        mViewModel = new ViewModelProvider(this).get(getClassViewModel())
        ;
        return mBinding.getRoot();

    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    protected abstract void initViews();

    protected abstract Class<V> getClassViewModel();

    protected abstract T intitFragmentBinding(LayoutInflater inflater);

    protected void showNotice(String mess) {
        Toast.makeText(mContext, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public final void onClick(View v) {
        clickView(v);
    }

    protected void clickView(View v) {
        //do something

    }

}
