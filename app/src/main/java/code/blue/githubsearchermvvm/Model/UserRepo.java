package code.blue.githubsearchermvvm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRepo {

    @SerializedName("name")
    @Expose
    private String repoName;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;
    @SerializedName("forks_count")
    @Expose
    private Integer forksCount;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }
}