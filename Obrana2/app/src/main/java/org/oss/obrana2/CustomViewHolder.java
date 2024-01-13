package org.oss.obrana2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    private final TextView subjectName;

    public CustomViewHolder(@NonNull View view) {
        super(view);

        subjectName = view.findViewById(R.id.subjectName);
    }

    public TextView getSubjectName() {
        return subjectName;
    }
}
