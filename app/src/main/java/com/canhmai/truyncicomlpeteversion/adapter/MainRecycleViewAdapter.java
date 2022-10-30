package com.canhmai.truyncicomlpeteversion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.db.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> implements Filterable {
    private final Context context;
    //list tim kiem
    private final List<Category> storyListOld;
    MutableLiveData<String> clickedStory = new MutableLiveData<>();
    private List<Category> storyList;

    public MainRecycleViewAdapter(Context context, List<Category> storyList) {
        this.context = context;
        this.storyList = storyList;
        this.storyListOld = storyList;
    }

    public MutableLiveData<String> getClickedStory() {
        return clickedStory;
    }

    private void ClickItemView(String name) {

        clickedStory.postValue(name);
        notifyItemRangeChanged(0, storyList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //anh xa layout item va tra ve mot viewholder chua item do
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category story = storyList.get(position);
        holder.image.setImageBitmap(story.getImage());
        holder.textView.setText(story.getName());
        holder.tableRow.setTag(story.getName());
        holder.tableRow.setBackgroundResource(R.drawable.clickitem);
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
                    List<Category> searchList = new ArrayList<>();
                    for (Category category :
                            storyListOld) {
                        if (category.getName().toLowerCase().contains(searchtxt.toLowerCase())) {
                            searchList.add(category);
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
                storyList = (List<Category>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TableRow tableRow;
        TextView textView;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tableRow = itemView.findViewById(R.id.tr_category);
            textView = itemView.findViewById(R.id.tv_categories);
            image = itemView.findViewById(R.id.iv_photo);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickItemView((String) tableRow.getTag());
                }
            });

        }
    }
}
