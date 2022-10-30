package com.canhmai.truyncicomlpeteversion.view.fragment;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;

import com.canhmai.truyncicomlpeteversion.App;
import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.adapter.MainRecycleViewAdapter;
import com.canhmai.truyncicomlpeteversion.adapter.MainRecycleViewAdapterHorizontal;
import com.canhmai.truyncicomlpeteversion.databinding.A001MainFramentBinding;
import com.canhmai.truyncicomlpeteversion.db.entity.Category;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.canhmai.truyncicomlpeteversion.view.act.MainActivity;
import com.canhmai.truyncicomlpeteversion.viewmodel.MainViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class A001MainFragment extends BaseFragment<A001MainFramentBinding, MainViewModel> {
    public static final String TAG = A001MainFragment.class.getName();
    private final String[] listTabName = {"Danh mục", "Yêu thích"};
    MainRecycleViewAdapter mainRecycleViewAdapter;
    MainRecycleViewAdapterHorizontal mainRecycleViewAdapterHorizontal;
    private AssetManager assetManager;

    @Override
    protected void initViews() {
        // checkNighMode();
        intiUI();
        App.getInstance().getStorage().categoryList = new ArrayList<>();
       addCategories();
       // initPhotosTopics();
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.getWindow().setStatusBarColor(getResources().getColor(R.color.red));

        mainRecycleViewAdapterHorizontal = new MainRecycleViewAdapterHorizontal(App.getInstance().getStorage().randomStoryList);
        mainRecycleViewAdapterHorizontal.getClickedStory().observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                if (story != null) {
                    goToDetailScreen(story);
                }
            }
        });
        mBinding.recyclerviewHorizlotal.setAdapter(mainRecycleViewAdapterHorizontal);
        setClickView();

    }


    private void intiUI() {
        mBinding.ln2.removeAllViews();

        for (int i = 0; i < App.getInstance().getStorage().charList.size() - 7; i += 7) {
            View v1 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);
            View v2 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);
            View v3 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);
            View v4 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);
            View v5 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);
            View v6 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);
            View v7 = LayoutInflater.from(mContext).inflate(R.layout.item_character, null);

            TextView tv1 = v1.findViewById(R.id.tv_char);
            TextView tv2 = v2.findViewById(R.id.tv_char);
            TextView tv3 = v3.findViewById(R.id.tv_char);
            TextView tv4 = v4.findViewById(R.id.tv_char);
            TextView tv5 = v5.findViewById(R.id.tv_char);
            TextView tv6 = v6.findViewById(R.id.tv_char);
            TextView tv7 = v7.findViewById(R.id.tv_char);

            tv1.setText(App.getInstance().getStorage().charList.get(i));
            tv2.setText(App.getInstance().getStorage().charList.get(i + 1));
            tv3.setText(App.getInstance().getStorage().charList.get(i + 2));
            tv4.setText(App.getInstance().getStorage().charList.get(i + 3));
            tv5.setText(App.getInstance().getStorage().charList.get(i + 4));
            tv6.setText(App.getInstance().getStorage().charList.get(i + 5));
            tv7.setText(App.getInstance().getStorage().charList.get(i + 6));

            tv1.setTag(tv1.getText().toString());
            tv2.setTag(tv2.getText().toString());
            tv3.setTag(tv3.getText().toString());
            tv4.setTag(tv4.getText().toString());
            tv5.setTag(tv5.getText().toString());
            tv6.setTag(tv6.getText().toString());
            tv7.setTag(tv7.getText().toString());


            tv1.setOnClickListener(this);
            tv2.setOnClickListener(this);
            tv3.setOnClickListener(this);
            tv4.setOnClickListener(this);
            tv5.setOnClickListener(this);
            tv6.setOnClickListener(this);
            tv7.setOnClickListener(this);

            TableRow tableRow = new TableRow(mContext);
            tableRow.setGravity(Gravity.CENTER);

            tableRow.addView(v1, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.addView(v2, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.addView(v3, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.addView(v4, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.addView(v5, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.addView(v6, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.addView(v7, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            mBinding.ln2.addView(tableRow);
        }

    }

    private void goToDetailScreen(Story story) {
        App.getInstance().getStorage().setChoosenStory(story);
        App.getInstance().getStorage().listStory = App.getInstance().getStorage().randomStoryList;
        onMainCallBack.showFragment(A004DetailFragment.TAG, null, true, false);
    }

    private void setClickView() {
        mBinding.includeMainActionBar.ivMenuVsBack.setOnClickListener(this);
        mBinding.includeMainActionBar.tapToSearch.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvYeuthich.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvInfo.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvOtherApp.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvShare.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvRate.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvSetting.setOnClickListener(this);
        mBinding.includeDrawerlayout.tvEmail.setOnClickListener(this);

    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        switch (v.getId()) {
            case R.id.iv_menu_vs_back:
                mBinding.dlMyCustonDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.tap_to_search:
                onMainCallBack.showFragment(A002SearchFragment.TAG, null, true, false);
                break;
            case R.id.tv_info:
                Toast.makeText(mContext, "Phiên bản: 1.0.0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_other_app:
                Toast.makeText(mContext, "Chưa có app nào!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_rate:
                Toast.makeText(mContext, "thankyou", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String name = "Truyện cười";
                String text = "Đây là link của ứng dụng này";

                intent.putExtra(Intent.EXTRA_SUBJECT, name);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(intent, "Chia sẻ qua "));
                break;
            case R.id.tv_setting:
                onMainCallBack.showFragment(A00SettingFragment.TAG, null, true, false);
                break;
            case R.id.tv_email:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("mailto:Canhmaidev@gmail.com");
                intent1.setData(uri);
                String Subject = "Bao cao loi";
                String mess = " thay doi gi do";
                intent1.putExtra(Intent.EXTRA_SUBJECT, Subject);
                intent1.putExtra(Intent.EXTRA_TEXT, mess);
                startActivity(intent1);
                break;

            case R.id.tv_yeuthich:
                App.getInstance().getStorage().listFavouriteStory = new ArrayList<>();
                App.getInstance().getStorage().listFavouriteStory = App.getInstance().getAppDB().getStoriesDAO().getFavouriteStory();
                Log.e("RRRRRRRRR", App.getInstance().getStorage().listFavouriteStory.size() + "");
                if (App.getInstance().getStorage().listFavouriteStory.size() > 0) {
                    onMainCallBack.showFragment(A005FavouriteFragment.TAG, null, true, false);
                } else {
                    Toast.makeText(mContext, "Danh sách trống", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                String tag = v.getTag().toString();
                App.getInstance().getStorage().listStory = App.getInstance().getAppDB().getStoriesDAO().getStoryByChar(tag);
                if (App.getInstance().getStorage().listStory.size() > 0) {
                    onMainCallBack.showFragment(A003DetailFragment.TAG, null, true, false);
                } else {
                    Toast.makeText(mContext, "Không tìm thấy truyện phù hợp", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected A001MainFramentBinding intitFragmentBinding(LayoutInflater inflater) {
        return A001MainFramentBinding.inflate(inflater);
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
        mainRecycleViewAdapter.getClickedStory().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                App.getInstance().getStorage().setCategoryName(s);

                String name = App.getInstance().getStorage().getCategoryName();
                App.getInstance().getStorage().listStory = App.getInstance().getAppDB().getStoriesDAO().getStoryByCatName(name);

                onMainCallBack.showFragment(A003DetailFragment.TAG, null, true, false);
            }
        });
        mBinding.recyclerviewVertical.setAdapter(mainRecycleViewAdapter);

    }

    private void openCategory(View v) {
        String topicName = v.getTag().toString();
        App.getInstance().getStorage().setCategoryName(topicName);

        onMainCallBack.showFragment(A003DetailFragment.TAG, null, true, false);
        //  showListStory();
    }

    private void initPhotosTopics() {
        //asset manager giups chungs ta truy cap den thu muc asset
       assetManager = requireActivity().getAssets();

        try {
            //.list: lay duoc danh sach duong dan anh
            String[] listFileName = assetManager.list("photo/");

            mBinding.lnAddTopic.removeAllViews(); //xóa hết các view cũ có trước đó
            for (String photoPath : listFileName
            ) {
                //ánh xạ layout item_topic ra code(lôi ra)
                View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);
                TextView tvName = itemView.findViewById(R.id.tv_categories);
                ImageView ivPhoto = itemView.findViewById(R.id.iv_photo);

                InputStream in = assetManager.open("photo/" + photoPath);
                //   Decode an inputstream into a bitmap
                Bitmap img = BitmapFactory.decodeStream(in);
                //thay đổi ảnh và text
                ivPhoto.setImageBitmap(img);

//                //xoa duoi png va PNG
                String text = photoPath.replace(".PNG", "");
                tvName.setText(text.replace(".png", ""));

                //click
                itemView.setTag(tvName.getText().toString());
                itemView.setOnClickListener(v -> openTopic(v));
                //thêm vào ln layout
               mBinding.lnAddTopic.addView(itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openTopic(View viewTopic) {

        //sau khi chon xong drawerlayout dong vao
        App.getInstance().getStorage().setCategoryName(viewTopic.getTag().toString());

        String name = App.getInstance().getStorage().getCategoryName();
        App.getInstance().getStorage().listStory = App.getInstance().getAppDB().getStoriesDAO().getStoryByCatName(name);

        onMainCallBack.showFragment(A003DetailFragment.TAG, null, true, false);

    }

}
