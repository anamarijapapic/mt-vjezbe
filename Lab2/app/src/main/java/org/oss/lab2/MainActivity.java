package org.oss.lab2;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<GitHubRepository> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GitHubRepository> topRepositories = new ArrayList<>();
        topRepositories.add(new GitHubRepository("linux", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));
        topRepositories.add(new GitHubRepository("git", "https://avatars.githubusercontent.com/u/92815435?v=4", 1000));
        topRepositories.add(new GitHubRepository("python", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));
        topRepositories.add(new GitHubRepository("java", "https://avatars.githubusercontent.com/u/92815435?v=4", 1000));
        topRepositories.add(new GitHubRepository("kotlin", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));
        topRepositories.add(new GitHubRepository("c", "https://avatars.githubusercontent.com/u/92815435?v=4", 1000));
        topRepositories.add(new GitHubRepository("c++", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));
        topRepositories.add(new GitHubRepository("c#", "https://avatars.githubusercontent.com/u/92815435?v=4", 1000));
        topRepositories.add(new GitHubRepository("javascript", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));
        topRepositories.add(new GitHubRepository("php", "https://avatars.githubusercontent.com/u/92815435?v=4", 1000));
        topRepositories.add(new GitHubRepository("ruby", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));
        topRepositories.add(new GitHubRepository("rust", "https://avatars.githubusercontent.com/u/92815435?v=4", 1000));
        topRepositories.add(new GitHubRepository("swift", "https://avatars0.githubusercontent.com/u/1024025?v=4", 1000));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<GitHubResponse> call = service.getRepos();

        call.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(@NonNull Call<GitHubResponse> call, @NonNull Response<GitHubResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                GitHubResponse gh_response = response.body();
                assert gh_response != null;
                Log.println(Log.INFO, "MainActivity", gh_response.toString());
                repos = gh_response.getItems();
                Log.println(Log.INFO, "MainActivity", repos.toString());

                customAdapter = new CustomAdapter(repos);
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<GitHubResponse> call, @NonNull Throwable t) {
                //
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}