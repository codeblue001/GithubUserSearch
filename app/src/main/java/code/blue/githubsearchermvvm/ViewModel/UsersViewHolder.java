package code.blue.githubsearchermvvm.ViewModel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import code.blue.githubsearchermvvm.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    TextView username, repoCount;
    ImageView avatar;
    CardView cardView;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.userName_tv);
        avatar = itemView.findViewById(R.id.profileImageView);
        repoCount = itemView.findViewById(R.id.repositoryCountTextView);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
