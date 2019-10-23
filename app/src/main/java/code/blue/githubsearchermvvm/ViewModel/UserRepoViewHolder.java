package code.blue.githubsearchermvvm.ViewModel;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import code.blue.githubsearchermvvm.R;

public class UserRepoViewHolder extends RecyclerView.ViewHolder {
    TextView repoName, forkCount, starCount;

    public UserRepoViewHolder(@NonNull View itemView) {
        super(itemView);
        repoName = itemView.findViewById(R.id.repoTextView);
        forkCount = itemView.findViewById(R.id.repoForkTextView);
        starCount = itemView.findViewById(R.id.repoStarTextView);
    }
}
