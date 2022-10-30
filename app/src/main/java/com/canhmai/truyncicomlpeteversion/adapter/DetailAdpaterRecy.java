package com.canhmai.truyncicomlpeteversion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canhmai.truyncicomlpeteversion.R;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;

import java.util.List;

public class DetailAdpaterRecy extends RecyclerView.Adapter<DetailAdpaterRecy.HolderAdapter> {
    private final Context context;
    private final List<Story> storyModelsList;

    public DetailAdpaterRecy(Context context, List<Story> storyModelsList) {
        this.context = context;
        this.storyModelsList = storyModelsList;
    }


    @NonNull
    @Override
    public HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_detail_story, parent, false);
        return new HolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapter holder, int position) {
        Story storyModel = storyModelsList.get(position);
        holder.tvName.setText(storyModel.getName());
        holder.tvContent.setText(storyModel.getContent());
        holder.number.setText((position+1)+"");
    }

    @Override
    public int getItemCount() {
        return storyModelsList.size();
    }

    public class HolderAdapter extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvContent;
        TextView number;

        public HolderAdapter(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_story_name);
            tvContent = itemView.findViewById(R.id.tv_detail);
            number = itemView.findViewById(R.id.number);
        }
    }


}
