package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.view.LayoutInflater;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.canhmai.truyncicomlpeteversion.App;
import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.adapter.DetailAdpater;
import com.canhmai.truyncicomlpeteversion.databinding.A004DetailFragmentBinding;
import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.MainViewModel;

public class A004DetailFragment extends BaseFragment<A004DetailFragmentBinding, MainViewModel> {
    public static final String TAG = A004DetailFragment.class.getName();
    private DetailAdpater adapter;

    @Override
    protected void initViews() {
        mBinding.includeactionbar.searchview.setVisibility(View.GONE);
        mBinding.includeactionbar.tapToSearch.setVisibility(View.GONE);
        mBinding.includeactionbar.trCountnumber.setVisibility(View.VISIBLE);

        mBinding.includeactionbar.ivMenuVsBack.setImageResource(R.drawable.btt_back_state);
        adapter = new DetailAdpater(mContext, App.getInstance().getStorage().listStory);
        mBinding.viewpager2.setAdapter(adapter);

        int currentPostition = App.getInstance().getStorage().listStory.indexOf(App.getInstance().getStorage().getChoosenStory());

        mBinding.viewpager2.setCurrentItem(currentPostition, false);
        //so luong truyen
        mBinding.includeactionbar.tvCountStory.setText(String.format("%d", App.getInstance().getStorage().listStory.size()));

        mBinding.includeactionbar.tvCurrentNumber.setText("Số truyện: ");
        if (currentPostition < 9)
            mBinding.number.setText(String.format("%s%d", "0", currentPostition + 1));
        else mBinding.number.setText(String.format("%s%d", "", currentPostition + 1));

        mBinding.viewpager2.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mBinding.number.setText(String.format("%s%d", (position < 9 ? "0" : ""), position + 1));
            }
        });
        mBinding.includeactionbar.ivMenuVsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
               mainActivity.onBackPressed();
            }
        });
    }

    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected A004DetailFragmentBinding intitFragmentBinding(LayoutInflater inflater) {
        return A004DetailFragmentBinding.inflate(inflater);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

    }
}
