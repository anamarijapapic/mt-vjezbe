package org.oss.lab3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    private final TextView uid, title, content;

    public CustomViewHolder(@NonNull View view) {
        super(view);

        this.uid = view.findViewById(R.id.uid);
        this.title = view.findViewById(R.id.title);
        this.content = view.findViewById(R.id.content);
    }

    public TextView getUid() { return uid; }
    public TextView getTitle() {
        return title;
    }

    public TextView getContent() {
        return content;
    }
}
