package code.blue.githubsearchermvvm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResult {

    @SerializedName("items")
    @Expose
    List<Users> items = null;

    public List<Users> getItems() {
        return items;
    }

    public void setItems(List<Users> items) {
        this.items = items;
    }
}
