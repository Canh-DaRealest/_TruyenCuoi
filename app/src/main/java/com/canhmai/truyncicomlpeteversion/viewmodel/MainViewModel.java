package com.canhmai.truyncicomlpeteversion.viewmodel;

import android.content.res.AssetManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.canhmai.truyncicomlpeteversion.db.entity.Story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Story>> mutableLiveData = new MutableLiveData<List<Story>>();
    private List<Story> storyList;
    private String topicname = "Con gái";

    public List<Story> getStoryList() {
        return storyList;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public MutableLiveData<List<Story>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void showListName(AssetManager assetManager) {
        List<Story> storyList = new ArrayList<>();

        try {

            InputStream inputStream = assetManager.open("text/" + topicname + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String storyName = null;
            StringBuilder content = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                if (storyName == null) {
                    storyName = line;
                } else if (line.contains("','0');")) {
                    Story story = new Story(storyName, content.toString(),  topicname,  0);
                    storyList.add(story);
                    storyName = null;
                    content = new StringBuilder();
                } else if (!line.isEmpty()) {
                    content.append(line).append("\n");
                }
                line = bufferedReader.readLine();
            }
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("MAINVIEWMODEL", "ListStoryModel = " + storyList.size());
        mutableLiveData.postValue(storyList);
    }
}
