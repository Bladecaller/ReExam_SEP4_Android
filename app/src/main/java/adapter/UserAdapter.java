package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import model.room.entity.Account.Account;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>  {
    private List<Account> users;
    Context mContext;
    private OnButtonListener mOnBtnListener;

    public UserAdapter(Context context, List<Account> itemList,OnButtonListener listener){
        this.mOnBtnListener = listener;
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

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account current = users.get(position);
        holder.tName.setText(current.getUsername());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBtnListener.OnButtonClick(holder.getAdapterPosition());
            }
        });

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
        private Button editBtn;

        public ViewHolder(@NonNull @NotNull View itemView,UserAdapter adapter) {
            super(itemView);

            this.adapter = adapter;
            editBtn = itemView.findViewById(R.id.btnEdit);
            tName = itemView.findViewById(R.id.username);
        }
    }

    public interface OnButtonListener{
        void OnButtonClick(int position);
    }
}
