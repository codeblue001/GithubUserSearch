package code.blue.githubsearchermvvm.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import code.blue.githubsearchermvvm.Model.Users;
import code.blue.githubsearchermvvm.R;
import code.blue.githubsearchermvvm.View.UserDetail;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    private Context context;
    private List<Users> items;

    public UsersAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.items = usersList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, final int position) {
        holder.username.setText(items.get(position).getUsername());
//        holder.repoCount.setText("Repo: " + items.get(position).ge);
        Picasso.get().load(items.get(position).getAvatar()).into(holder.avatar);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = items.get(position).getUsername();
                Intent intent = new Intent(context, UserDetail.class);
                intent.putExtra("username", userName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
