package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import model.room.entity.Account.Account;

public class UserAdapter extends RecyclerView.Adapter  {
    private List<Account> users;
    Context mContext;

    public UserAdapter(Context context, List<Account> itemList){
        this.mContext = context;
        this.users = itemList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user,parent,false);
        return new ViewHolder(mItemView,this);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account current = users.get(position);
        holder.tName.setText(current.getUsername());
    }

    @Override
    public int getItemCount() {
        if (users==null){
            return 0;
        }
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        UserAdapter adapter;
        private TextView tName;

        public ViewHolder(@NonNull @NotNull View itemView,UserAdapter adapter) {
            super(itemView);

            this.adapter = adapter;
            tName = itemView.findViewById(R.id.username);
        }
    }
}
