package code.blue.githubsearchermvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import code.blue.githubsearchermvvm.Model.ApiInterface;
import code.blue.githubsearchermvvm.Model.UserDetailPojo;
import code.blue.githubsearchermvvm.Model.UserRepo;
import code.blue.githubsearchermvvm.R;
import code.blue.githubsearchermvvm.ViewModel.UserRepoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetail extends AppCompatActivity {

    private TextView userName, email, location, joinDate, followers, following, bio;
    private ImageView avatar;
    private UserDetailPojo userDetails;
    private List<UserRepo> userRepoList;
    private RecyclerView recyclerView;
    private UserRepoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        userName = findViewById(R.id.userNameTextView2);
        email = findViewById(R.id.emailTextView);
        location = findViewById(R.id.locationTextView);
        joinDate = findViewById(R.id.joinDateTextView);
        followers = findViewById(R.id.followersTextView);
        following = findViewById(R.id.followingTextView);
        bio = findViewById(R.id.aboutMeTextView);
        avatar = findViewById(R.id.profileImageView2);

        recyclerView = findViewById(R.id.repoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        initUserDetailRetrofit(username);
        initUserRepoRetrofit(username);
    }

    private void initUserRepoRetrofit(String username) {
        //Repo Retrofit call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(ApiInterface.class).getUserRepo(username)
                .enqueue(new Callback<List<UserRepo>>() {
                    @Override
                    public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                        userRepoList = response.body();
                        adapter = new UserRepoAdapter(UserDetail.this, userRepoList);
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(UserDetail.this, "Excellent!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                        Toast.makeText(UserDetail.this, "Muahaha!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initUserDetailRetrofit(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(ApiInterface.class).getUserDetail(username)
                .enqueue(new Callback<UserDetailPojo>() {
                    @Override
                    public void onResponse(Call<UserDetailPojo> call, Response<UserDetailPojo> response) {
                        userDetails = response.body();
                        populateData(userDetails);
                        Toast.makeText(UserDetail.this, "Excellent!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserDetailPojo> call, Throwable t) {
                        Toast.makeText(UserDetail.this, "Muahaha!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateData(UserDetailPojo userDetailPojo) {

        String rawDate = userDetailPojo.getJoinDate();
        String formatDate = "Join Date: "  + (rawDate.substring(0, 10));
        String rawEmail = userDetailPojo.getEmail();
        String notIndicated = "Email: Not Available";


        userName.setText("Username: " + (userDetailPojo.getLogin()));
        email.setText("Email: " + (userDetailPojo.getEmail()));
        if (rawEmail == null){
            email.setText(notIndicated);
        }
        location.setText("Location: " + (userDetailPojo.getLocation()));
        joinDate.setText(formatDate);
        followers.setText("Followers: " + (userDetailPojo.getFollowers().toString()));
        following.setText("Following: " + (userDetailPojo.getFollowing().toString()));
        bio.setText("About Me: " + (userDetailPojo.getBio()));
        Picasso.get().load(userDetailPojo.getAvatarUrl()).into(avatar);
    }
}
