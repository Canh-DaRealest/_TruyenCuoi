package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;

import com.canhmai.truyncicomlpeteversion.App;

import com.canhmai.truyncicomlpeteversion.adapter.MainRecycleViewAdapter;
import com.canhmai.truyncicomlpeteversion.adapter.MainRecycleViewAdapterHorizontal;

import com.canhmai.truyncicomlpeteversion.db.entity.Category;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.MainViewModel;
import com.example.truyencuoi.R;
import com.example.truyencuoi.databinding.A002SearchFragmentBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class A002SearchFragment extends BaseFragment<A002SearchFragmentBinding, MainViewModel> {
    public static final String TAG = A002SearchFragment.class.getName();

    MainRecycleViewAdapter mainRecycleViewAdapter;
    MainRecycleViewAdapterHorizontal mainRecycleViewAdapterHorizontal;
    private AssetManager assetManager;


    @Override
    protected void initViews() {


        // checkNighMode();
        mBinding.includeDetailActionBar.searchview.setVisibility(View.VISIBLE);
        mBinding.includeDetailActionBar.tapToSearch.setVisibility(View.GONE);
        showSearchView();

        mBinding.includeDetailActionBar.searchview.setQueryHint("Nhập chủ đề");
        mBinding.includeDetailActionBar.ivMenuVsBack.setImageResource(R.drawable.btn_back);
        App.getInstance().getStorage().categoryList = new ArrayList<>();
        addCategories();




        setClickView();

    }

    private void showSearchView() {
        mBinding.includeDetailActionBar.searchview.setIconifiedByDefault(true);
        mBinding.includeDetailActionBar.searchview.setFocusable(true);
        mBinding.includeDetailActionBar.searchview.requestFocusFromTouch();
        mBinding.includeDetailActionBar.searchview.setIconified(false);

        mBinding.includeDetailActionBar.searchview.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, boolean hasFocus) {
                if (hasFocus) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(view.findFocus(), 0);
                        }
                    }, 200);
                }
            }
        });
    }

    private void setClickView() {
        mBinding.includeDetailActionBar.ivMenuVsBack.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.onBackPressed();

    }

    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected A002SearchFragmentBinding intitFragmentBinding(LayoutInflater inflater) {
        return A002SearchFragmentBinding.inflate(inflater);
    }

    //hien thi ten the loai truyen
    private void addCategories() {
        assetManager = requireActivity().getAssets();
        try {
            String[] listStoryCate = assetManager.list("photo/");
            for (String photo : listStoryCate
            ) {

                InputStream inputStream = assetManager.open("photo/" + photo);
                Bitmap bitImg = BitmapFactory.decodeStream(inputStream);
                String cate = photo.replace(".png", "");
                Category category = new Category(cate, bitImg);
                App.getInstance().getStorage().categoryList.add(category);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainRecycleViewAdapter = new MainRecycleViewAdapter(mContext, App.getInstance().getStorage().categoryList);

        mBinding.includeDetailActionBar.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mainRecycleViewAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mainRecycleViewAdapter.getFilter().filter(newText);


                return false;
            }
        });
        mainRecycleViewAdapter.getClickedStory().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                App.getInstance().getStorage().setCategoryName(s);

                String name = App.getInstance().getStorage().getCategoryName();
                App.getInstance().getStorage().listStory = App.getInstance().getAppDB().getStoriesDAO().getStoryByCatName(name);

                onMainCallBack.showFragment(A003DetailFragment.TAG, null, false, false);
            }
        });
        mBinding.vpRecyclerView.setAdapter(mainRecycleViewAdapter);

    }

}
