package org.oss.lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final ArrayList<GitHubRepository> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView repositoryName, stargazersCount;

        private final ImageView ownerAvatar;

        public ViewHolder(@NonNull View view) {
            super(view);

            repositoryName = view.findViewById(R.id.repositoryName);
            stargazersCount = view.findViewById(R.id.stargazersCount);
            ownerAvatar = view.findViewById(R.id.ownerAvatar);
        }

        public TextView getRepositoryName() {
            return repositoryName;
        }

        public TextView getStargazersCount() {
            return stargazersCount;
        }

        public ImageView getOwnerAvatar() {
            return ownerAvatar;
        }
    }

    public CustomAdapter(ArrayList<GitHubRepository> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder viewHolder, int position) {
        viewHolder.getRepositoryName().setText(localDataSet.get(position).repositoryName);

        viewHolder.getStargazersCount().setText(localDataSet.get(position).stargazersCount.toString());

        Glide
            .with(viewHolder.ownerAvatar.getContext())
            .load(localDataSet.get(position).ownerAvatarUrl)
            .into(viewHolder.getOwnerAvatar());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
