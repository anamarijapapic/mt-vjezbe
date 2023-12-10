package org.oss.lab2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {
    @GET("search/repositories?q=stars:>100000&per_page=100")
    Call<GitHubResponse> getRepos();
}
