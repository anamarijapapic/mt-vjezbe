package org.oss.lab2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class GitHubRepository implements Parcelable {
    @SerializedName("full_name")
    @Expose
    String repositoryName;

    @SerializedName("owner")
    @JsonAdapter(UsernameDeserializer.class)
    @Expose
    String ownerAvatarUrl;

    @SerializedName("stargazers_count")
    @Expose
    Integer stargazersCount;

    public GitHubRepository(String repositoryName, String ownerAvatarUrl, Integer stargazersCount) {
        this.repositoryName = repositoryName;
        this.ownerAvatarUrl = ownerAvatarUrl;
        this.stargazersCount = stargazersCount;
    }

    protected GitHubRepository(Parcel in) {
        repositoryName = in.readString();
        ownerAvatarUrl = in.readString();
        if (in.readByte() == 0) {
            stargazersCount = null;
        } else {
            stargazersCount = in.readInt();
        }
    }

    public static final Creator<GitHubRepository> CREATOR = new Creator<GitHubRepository>() {
        @Override
        public GitHubRepository createFromParcel(Parcel in) {
            return new GitHubRepository(in);
        }

        @Override
        public GitHubRepository[] newArray(int size) {
            return new GitHubRepository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(repositoryName);
        dest.writeString(ownerAvatarUrl);
        if (stargazersCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(stargazersCount);
        }
    }

    public static class UsernameDeserializer implements JsonDeserializer<String> {
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return json.getAsJsonObject().get("avatar_url").getAsString();
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "GitHubRepository{" +
                "repositoryName='" + repositoryName + '\'' +
                ", ownerAvatarUrl='" + ownerAvatarUrl + '\'' +
                ", stargazersCount=" + stargazersCount +
                '}';
    }
}
