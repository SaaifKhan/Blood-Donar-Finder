package app.dotinfiny.Bdf.UI.homeFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.dotinfiny.Bdf.Constants;
import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.homeFragment.model.RequestsModel;

public class HomeProfileAdapter extends RecyclerView.Adapter<HomeProfileAdapter.ViewHolder> {
    List<RequestsModel> requestsModelList;
    Context context;
    //    public static int datetimeParse;
//    SimpleDateFormat formDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
//    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
//    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    CLickListener cLickListener;


    public HomeProfileAdapter(List<RequestsModel> requestsModelList, CLickListener cLickListener) {
        this.requestsModelList = requestsModelList;
        this.cLickListener = cLickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProfileAdapter.ViewHolder holder, final int position) {
        RequestsModel requestsModel = requestsModelList.get(position);
        holder.tvBloodGroup.setText(requestsModel.getBloodGroup());
        holder.tvArea.setText(requestsModel.getLocation());
        // holder.tvType.setText(requestsModel.getRequestType());
        holder.tvName.setText(requestsModel.getUserName());
        //  String imageUrl = dataSnapshot.child(Constants.IMAGE).getValue(String.class);
        context = holder.itemView.getContext();

        Glide.with(context)
                .load(requestsModel.getUserImage())
                //.placeholder()
                .into(holder.profileImg);

        if (requestsModel.getRequestType().equals("0")) {
            holder.tvType.setText("Donar");

        } else {
            requestsModel.getRequestType().equals("1");
            holder.tvType.setText("Receiver");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cLickListener.onClick(position);

            }
        });

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
    public int getItemCount() {
        return requestsModelList.size();
    }

    public interface CLickListener {
        void onClick(int position);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImg;
        TextView tvName, tvArea, tvType, tvBloodGroup;


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
