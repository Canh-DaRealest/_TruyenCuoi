package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.canhmai.truyncicomlpeteversion.App;

import com.canhmai.truyncicomlpeteversion.adapter.DetailAdpater;
import com.canhmai.truyncicomlpeteversion.adapter.RecycleViewAdapter;

import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.MainViewModel;
import com.example.truyencuoi.R;
import com.example.truyencuoi.databinding.A003DetailFragmentBinding;

import java.util.List;

public class A003DetailFragment extends BaseFragment<A003DetailFragmentBinding, MainViewModel> {
    public static final String TAG = A003DetailFragment.class.getName();
    List<Story> storyList;
    private DetailAdpater detailAdpater;
    private RecycleViewAdapter recycleViewAdapter;

    @Override
    protected void initViews() {
        mBinding.includeDetailActionBar.searchview.setQueryHint("Tìm kiếm truyện");
        mBinding.includeDetailActionBar.searchview.setVisibility(View.VISIBLE);
        mBinding.includeDetailActionBar.tapToSearch.setVisibility(View.GONE);
        mBinding.includeDetailActionBar.ivMenuVsBack.setImageResource(R.drawable.btt_back_state);

        mBinding.detailMain.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.abc_slide_in_right));
        mBinding.includeDetailActionBar.tvCountStory.setText(App.getInstance().getStorage().listStory.size() + "");

        recycleViewAdapter = new RecycleViewAdapter(mContext, App.getInstance().getStorage().listStory);
        recycleViewAdapter.getClickedStory().observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                showStoryDetail(story);
            }
        });
        mBinding.vpRecyclerView.setAdapter(recycleViewAdapter);


        mBinding.includeDetailActionBar.ivMenuVsBack.setOnClickListener(this);
        mBinding.includeDetailActionBar.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recycleViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recycleViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void showStoryDetail(Story storyModels) {
        App.getInstance().getStorage().setChoosenStory(storyModels);
        onMainCallBack.showFragment(A004DetailFragment.TAG, null, true, false);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        MainActivity mainActivity = (MainActivity) mContext;
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        fragmentManager.popBackStack();
    }


    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected A003DetailFragmentBinding intitFragmentBinding(LayoutInflater inflater) {
        return A003DetailFragmentBinding.inflate(inflater);
    }


//    @Override
//    protected DetailStoryFragmentBinding intitFragmentBinding(LayoutInflater inflater) {
//        return DetailStoryFragmentBinding.inflate(inflater);
//    }
}
