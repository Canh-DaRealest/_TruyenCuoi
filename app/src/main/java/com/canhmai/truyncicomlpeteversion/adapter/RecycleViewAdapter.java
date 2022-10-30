package com.canhmai.truyncicomlpeteversion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.db.entity.Category;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>  implements Filterable {
    private final Context context;
    private  List<Story> storyList;
    private  List<Story> storyListOld;
    MutableLiveData<Story> clickedStory = new MutableLiveData<>();

    public RecycleViewAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
        storyListOld =storyList;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_story_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = storyList.get(position);

        holder.textView.setText(story.getName());
        holder.textView.setTag(story);
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
        TableRow tableRow;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tableRow = itemView.findViewById(R.id.tablerow_item_story_name);
            textView = itemView.findViewById(R.id.tv_item_story_name);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickItemView((Story) textView.getTag());
                }
            });

        }
    }
}
