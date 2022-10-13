package com.canhmai.truyncicomlpeteversion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;


import com.canhmai.truyncicomlpeteversion.db.entity.Story;
import com.example.truyencuoi.R;

import java.util.ArrayList;
import java.util.List;

public class Tab2RecycleViewAdapter extends RecyclerView.Adapter<Tab2RecycleViewAdapter.ViewHolder> implements Filterable {
    private final Context context;

    private List<Story> storyList;
    private final List<Story> storyListOld;
    private final MutableLiveData<Story> delStory = new MutableLiveData<>();
    private final MutableLiveData<Story> clickStory = new MutableLiveData<>();

    public Tab2RecycleViewAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
        this.storyListOld = storyList;
    }

    public MutableLiveData<Story> getDelStory() {
        return delStory;
    }

    public MutableLiveData<Story> getClickStory() {
        return clickStory;
    }

    private void ClickItemView(Story story) {

        clickStory.postValue(story);
        notifyItemRangeChanged(0, storyList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //anh xa layout item va tra ve mot viewholder chua item do
        View view = LayoutInflater.from(context).inflate(R.layout.item_favourite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = storyList.get(position);

        holder.textView.setText(story.getName());
        holder.textView.setTag(story);
        holder.frameLayout.setBackgroundResource(R.drawable.clickitem);
        holder.trashbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delStory.postValue(story);
                notifyItemRangeChanged(0, storyList.size());
            }
        });
    }


    @Override
    public int getItemCount() {
        return storyList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchtxt = constraint.toString();
                if (searchtxt.isEmpty()) {
                    storyList = storyListOld;
                } else {
                    List<Story> searchList = new ArrayList<>();
                    for (Story story :
                            storyListOld) {
                        if (story.getName().toLowerCase().contains(searchtxt.toLowerCase())) {
                            searchList.add(story);
                        }
                    }
                    storyList = searchList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = storyList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                storyList = (List<Story>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayout;
        TextView textView;
        ImageView trashbin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.tablerow_favourite_story);
            textView = itemView.findViewById(R.id.tv_item_favourite_name);
            trashbin = itemView.findViewById(R.id.iv_trashbin);

            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickItemView((Story) textView.getTag());
                }
            });

        }
    }
}
