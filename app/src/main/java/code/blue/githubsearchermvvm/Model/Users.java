package code.blue.githubsearchermvvm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("login")
    @Expose
    private String username;

    @SerializedName("avatar_url")
    @Expose
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
