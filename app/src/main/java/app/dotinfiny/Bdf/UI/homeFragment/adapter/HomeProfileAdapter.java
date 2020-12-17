package app.dotinfiny.Bdf.UI.homeFragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

import app.dotinfiny.Bdf.R;

public class HomeProfileAdapter extends RecyclerView.Adapter<HomeProfileAdapter.ViewHolder>  {

    public static int datetimeParse;
    SimpleDateFormat formDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    CLickListener cLickListener;

    public  interface CLickListener{
        void onClick(int position);


    }

    public HomeProfileAdapter(CLickListener cLickListener) {
        this.cLickListener = cLickListener;
    }

    @NonNull
    @Override
    public HomeProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_cards_rows, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProfileAdapter.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cLickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImg;
        TextView tvName,tvArea,tvType,tvBloodGroup;




        public ViewHolder(@NonNull View itemView) {



            super(itemView);
            profileImg = itemView.findViewById(R.id.profileImg);
            tvName = itemView.findViewById(R.id.Name);
            tvArea = itemView.findViewById(R.id.area);
            tvType = itemView.findViewById(R.id.donorReceiver);
            tvBloodGroup = itemView.findViewById(R.id.bloodGroup);





        }

    }
}
