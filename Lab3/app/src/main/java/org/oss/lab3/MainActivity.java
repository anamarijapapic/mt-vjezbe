package org.oss.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    AppDatabase db;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private final List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "database-name").build();

        customAdapter = new CustomAdapter(notes);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(customAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NoteDetailsActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        executorService.execute(() -> {
            List<Note> notes = db.noteDao().getAll();
            handler.post(() -> {
                customAdapter = new CustomAdapter(notes);
                recyclerView.setAdapter(customAdapter);
            });
        });
    }
}