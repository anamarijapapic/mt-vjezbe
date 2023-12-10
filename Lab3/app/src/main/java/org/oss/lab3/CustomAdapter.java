package org.oss.lab3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private final List<Note> localDataSet;
    public CustomAdapter(List<Note> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {
        Note item = localDataSet.get(position);
        customViewHolder.getUid().setText(String.valueOf(item.uid));
        customViewHolder.getTitle().setText(item.title);
        customViewHolder.getContent().setText(item.content);

        customViewHolder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), NoteDetailsActivity.class);
            intent.putExtra("uid", item.uid);
            intent.putExtra("title", item.title);
            intent.putExtra("content", item.content);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
