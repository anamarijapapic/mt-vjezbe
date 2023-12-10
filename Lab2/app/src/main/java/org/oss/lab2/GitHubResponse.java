package org.oss.lab2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GitHubResponse {
    @SerializedName("items")
    @Expose
    private ArrayList<GitHubRepository> items;

    public ArrayList<GitHubRepository> getItems() {
        return items;
    }

    @NotNull
    @Override
    public String toString() {
        return items.toString();
    }
}
