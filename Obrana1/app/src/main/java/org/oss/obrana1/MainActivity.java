package org.oss.obrana1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // http://demo7168568.mockable.io/genres
    private final ArrayList<String> genres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genres.add("Action");
        genres.add("Adventure");
        genres.add("Animation");
        genres.add("Biography");
        genres.add("Comedy");
        genres.add("Crime");
        genres.add("Documentary");
        genres.add("Drama");
        genres.add("Family");
        genres.add("Fantasy");
        genres.add("Film Noir");
        genres.add("History");
        genres.add("Horror");
        genres.add("Music");

        CustomAdapter customAdapter = new CustomAdapter(genres);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}