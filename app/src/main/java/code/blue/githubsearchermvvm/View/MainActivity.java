package code.blue.githubsearchermvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import code.blue.githubsearchermvvm.Model.ApiInterface;
import code.blue.githubsearchermvvm.Model.Users;
import code.blue.githubsearchermvvm.Model.UsersResult;
import code.blue.githubsearchermvvm.R;
import code.blue.githubsearchermvvm.ViewModel.UsersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private List<Users> users;
    private RecyclerView recyclerView;
    private UsersAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.searchView);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InitRetrofit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
    }

    private void InitRetrofit(String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       retrofit.create(ApiInterface.class).getUsers(query)
               .enqueue(new Callback<UsersResult>() {
            @Override
            public void onResponse(Call<UsersResult> call, Response<UsersResult> response) {
                users = response.body().getItems();
                adapter = new UsersAdapter(MainActivity.this, users);
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "Bueno", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsersResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No es bueno", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
