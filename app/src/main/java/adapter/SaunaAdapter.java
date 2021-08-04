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

    public SaunaAdapter(Context context, List<Sauna> itemList,ArrayList<Integer> imgList){
        this.mContext = context;
        this.saunas = itemList;
        this.image = imgList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sauna,parent,false);


        return new ViewHolder(mItemView,this);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Sauna current = saunas.get(position);
        holder.img.setImageResource(image.get(position));

    }


    @Override
    public int getItemCount() {
        if (saunas==null){
            return 0;
        }
        return saunas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        SaunaAdapter adapter;
        private Button bookBtn;
        private ImageView img;

        public ViewHolder(@NonNull @NotNull View itemView, SaunaAdapter adapter){
            super(itemView);

            this.adapter = adapter;
            bookBtn = itemView.findViewById(R.id.btnBook1);
            img = itemView.findViewById(R.id.saunaIMG);
        }
    }
}

