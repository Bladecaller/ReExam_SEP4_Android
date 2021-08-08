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
import model.room.entity.Account.Reservation;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>  {
    private List<Reservation> reservations;
    Context mContext;

    public BookingAdapter(Context context, List<Reservation> itemList){
        this.mContext = context;
        this.reservations = itemList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservation,parent,false);
        return new ViewHolder(mItemView,this);

    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation current = reservations.get(position);
        holder.tFrom.setText(current.getFromDateTime());
        holder.tTo.setText(current.getToDateTime());
        holder.saunaID.setText(String.valueOf(current.getSaunaID()));


    }

    @Override
    public int getItemCount() {
        if (reservations==null){
            return 0;
        }
        return reservations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        BookingAdapter adapter;
        private TextView tFrom;
        private TextView tTo;
        private TextView saunaID;


        public ViewHolder(@NonNull @NotNull View itemView, BookingAdapter adapter) {
            super(itemView);

            this.adapter = adapter;
            tFrom = itemView.findViewById(R.id.fromTimeView);
            tTo = itemView.findViewById(R.id.toTimeView);
            saunaID = itemView.findViewById(R.id.textViewSaunaID);
        }
    }

}
