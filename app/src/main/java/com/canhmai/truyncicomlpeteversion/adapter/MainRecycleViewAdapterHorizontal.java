package com.canhmai.truyncicomlpeteversion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MainRecycleViewAdapterHorizontal extends RecyclerView.Adapter<MainRecycleViewAdapterHorizontal.ViewHolder> {

    private final List<Story> storyList;
    MutableLiveData<Story> clickedStory = new MutableLiveData<>();

    public MainRecycleViewAdapterHorizontal(List<Story> storyList) {
        this.storyList = storyList;
    }

    public MutableLiveData<Story> getClickedStory() {
        return clickedStory;
    }

    private void ClickItemView(Story story) {

        clickedStory.postValue(story);
        notifyItemRangeChanged(0, storyList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //anh xa layout item va tra ve mot viewholder chua item do
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_detail_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = storyList.get(position);
        holder.name.setText(story.getName());
        holder.content.setText(story.getContent());
        holder.materialCardView.setTag(story);
    }


    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView materialCardView;
        private final TextView name;
        private final TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.material_carview_hr);
            name = itemView.findViewById(R.id.tv_story_name_main);
            content = itemView.findViewById(R.id.tv_detail_main);
            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickItemView((Story) materialCardView.getTag());
                }
            });

        }
    }
}
