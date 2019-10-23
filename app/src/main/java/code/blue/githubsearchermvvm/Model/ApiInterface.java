package code.blue.githubsearchermvvm.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://api.github.com
    @GET("/search/users")
    Call<UsersResult> getUsers(@Query("q") String query);

    @GET("users/{username}")
    Call<UserDetailPojo> getUserDetail(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<UserRepo>> getUserRepo(@Path("username") String username);
}
