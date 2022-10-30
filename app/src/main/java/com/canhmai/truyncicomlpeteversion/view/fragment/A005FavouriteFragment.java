package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;

import com.canhmai.truyncicomlpeteversion.App;
import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.adapter.Tab2RecycleViewAdapter;
import com.canhmai.truyncicomlpeteversion.databinding.A005FavouriteFragmentBinding;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.CommonViewModel;

public class A005FavouriteFragment extends BaseFragment<A005FavouriteFragmentBinding, CommonViewModel> {
    public static final String TAG = A005FavouriteFragment.class.getName();
    Tab2RecycleViewAdapter recycleViewAdapter;

    @Override
    protected void initViews() {
        mBinding.includeFavourite.searchview.setQueryHint("Tìm kiếm truyện");
        mBinding.includeFavourite.ivMenuVsBack.setImageResource(R.drawable.btt_back_state);

        recycleViewAdapter = new Tab2RecycleViewAdapter(mContext, App.getInstance().getStorage().listFavouriteStory);
        mBinding.recyclerviewVertical.setAdapter(recycleViewAdapter);

        mBinding.includeFavourite.tvCountStory.setText(App.getInstance().getStorage().listFavouriteStory.size() + "");
        recycleViewAdapter.getClickStory().observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                App.getInstance().getStorage().listStory = App.getInstance().getStorage().listFavouriteStory;
                App.getInstance().getStorage().setChoosenStory(story);
                onMainCallBack.showFragment(A004DetailFragment.TAG, null, true, false);
            }
        });
        recycleViewAdapter.getDelStory().observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                if (story != null) {
                    story.isFavourite = 0;
                    App.getInstance().getAppDB().getStoriesDAO().updateStory(story);
                    App.getInstance().getStorage().listFavouriteStory.remove(story);
                    mBinding.includeFavourite.tvCountStory.setText(App.getInstance().getStorage().listFavouriteStory.size() + "");
                    updateAdapter();

                }
            }
        });

        mBinding.includeFavourite.ivMenuVsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.onBackPressed();
            }
        });
        mBinding.includeFavourite.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private void updateAdapter() {

    }

    private void showDetail() {
        //  onMainCallBack.showFragment(DetailFragment2.TAG, null, true, true);
        Toast.makeText(mContext, "DA mo story", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Class<CommonViewModel> getClassViewModel() {
        return CommonViewModel.class;
    }

    @Override
    protected A005FavouriteFragmentBinding intitFragmentBinding(LayoutInflater inflater) {
        return A005FavouriteFragmentBinding.inflate(inflater);
    }
}
