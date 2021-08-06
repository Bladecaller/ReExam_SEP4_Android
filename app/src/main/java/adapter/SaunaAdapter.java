package adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


import model.room.entity.Sauna.Sauna;

public class SaunaAdapter extends RecyclerView.Adapter<SaunaAdapter.ViewHolder>{

    private List<Sauna> saunas;
    Context mContext;
    private ArrayList<Integer> image;
    private OnSaunaListener mOnSaunaListener;

    public SaunaAdapter(Context context, List<Sauna> itemList,ArrayList<Integer> imgList,OnSaunaListener onSaunaListener){
        this.mOnSaunaListener = onSaunaListener;
        this.mContext = context;
        this.saunas = itemList;
        this.image = imgList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sauna,parent,false);

        return new ViewHolder(mItemView,this,mOnSaunaListener);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Sauna current = saunas.get(position);

        holder.img.setImageResource(image.get(position));
        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getAdapterPosition();
            }
        });

    }


    @Override
    public int getItemCount() {
        if (saunas==null){
            return 0;
        }
        return saunas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SaunaAdapter adapter;
        private Button bookBtn;
        private ImageView img;
        OnSaunaListener listener;


        public ViewHolder(@NonNull @NotNull View itemView, SaunaAdapter adapter,OnSaunaListener listener){
            super(itemView);

            this.adapter = adapter;
            this.listener = listener;
            bookBtn = itemView.findViewById(R.id.btnBooking);
            img = itemView.findViewById(R.id.saunaIMG);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onSaunaClick(getAdapterPosition());
        }

    }

    public interface OnSaunaListener{
        void onSaunaClick(int position);
    }

    public interface OnButtonListener{
        void onButtonClick(int position);
    }
}

