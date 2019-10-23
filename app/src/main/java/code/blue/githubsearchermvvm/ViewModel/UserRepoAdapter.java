package code.blue.githubsearchermvvm.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import code.blue.githubsearchermvvm.Model.UserRepo;
import code.blue.githubsearchermvvm.R;

public class UserRepoAdapter extends RecyclerView.Adapter<UserRepoViewHolder> {
    private Context context;
    private List<UserRepo> userRepos;

    public UserRepoAdapter(Context context, List<UserRepo> userRepoList) {
        this.context = context;
        this.userRepos = userRepoList;
    }

    @NonNull
    @Override
    public UserRepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_layout, parent, false);
        return new UserRepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRepoViewHolder holder, int position) {
        holder.repoName.setText(userRepos.get(position).getRepoName());
        holder.forkCount.setText("Forks : " + userRepos.get(position).getForksCount());
        holder.starCount.setText("Stars : " + userRepos.get(position).getStargazersCount());
    }

    @Override
    public int getItemCount() {
        return userRepos.size();
    }
}
