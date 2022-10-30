package com.canhmai.truyncicomlpeteversion.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.PagerAdapter;

import com.canhmai.truyncicomlpeteversion.App;
import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;

import java.util.List;

public class DetailAdpater extends PagerAdapter {
    private final Context context;
    private final List<Story> storyModelsList;
    private final MutableLiveData<Story> storyLiveData = new MutableLiveData<>();
    private Story storyModel;

    public DetailAdpater(Context context, List<Story> storyList) {
        this.context = context;
        this.storyModelsList = storyList;
    }

    public Story getStoryModel() {
        return storyModel;
    }

    public MutableLiveData<Story> getStoryLiveData() {
        return storyLiveData;
    }

    @Override
    public int getCount() {
        if (storyModelsList != null) {
            return storyModelsList.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup viewPager, int position, @NonNull Object object) {
        viewPager.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_story, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.ln_detai_story);
        TextView tvname = view.findViewById(R.id.tv_story_name);
        TextView tvDetail = view.findViewById(R.id.tv_detail);
        ImageView favourite = view.findViewById(R.id.iv_favourite);
        ImageView share = view.findViewById(R.id.iv_share_detail);

        Story data = storyModelsList.get(position);

        if (data.getIsFavourite() == 0) {
            favourite.setImageResource(R.drawable.notlike);
        } else {
            favourite.setImageResource(R.drawable.ic_like);
        }

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getIsFavourite() == 0) {
                    favourite.setImageResource(R.drawable.ic_like);
                    data.setIsFavourite(1);
                    Toast.makeText(context, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    favourite.setImageResource(R.drawable.notlike);
                    data.setIsFavourite(0);
                    Toast.makeText(context, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();

                }
                new Thread() {
                    @Override
                    public void run() {
                        App.getInstance().getAppDB().getStoriesDAO().updateStory(data);
                    }
                }.start();
                notifyDataSetChanged();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, data.getName());
                intent.putExtra(Intent.EXTRA_TEXT, data.getContent());
                context.startActivity(Intent.createChooser(intent, "Chia sẻ qua "));
            }
        });
        storyModel = data;

        tvname.setText(data.getName());
        tvDetail.setText(data.getContent());


        if (App.getInstance().getStorage().text_size.equals("Nhỏ")) {
            tvname.setTextSize(26);
            tvDetail.setTextSize(16);
        } else if (App.getInstance().getStorage().text_size.equals("Lớn")) {
            tvname.setTextSize(36);
            tvDetail.setTextSize(26);
        }

        if (App.getInstance().getStorage().text_color.equals("Đen")) {
            tvname.setTextColor(context.getColor(R.color.red));
            tvDetail.setTextColor(context.getColor(R.color.black));
        } else if (App.getInstance().getStorage().background_color.equals("Vàng")) {
            tvname.setTextColor(context.getColor(R.color.yellow));
            tvDetail.setTextColor(context.getColor(R.color.yellow));
        }


        if (App.getInstance().getStorage().background_color.equals("Trắng")) {
            linearLayout.setBackgroundColor(context.getColor(R.color.white));
        } else if (App.getInstance().getStorage().background_color.equals("Vàng")) {
            linearLayout.setBackgroundColor(context.getColor(R.color.yellow));
        }

        container.addView(view);
        return view;

    }
}
