package app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.homeFragment.model.RequestsModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChildProfileAdapter extends RecyclerView.Adapter<ChildProfileAdapter.ViewHolder> {
    List<RequestsModel> requestsModelList;
    Context context;


    public ChildProfileAdapter(List<RequestsModel> requestsModelList) {
        this.requestsModelList = requestsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cards_rows, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ChildProfileAdapter.ViewHolder holder, int position) {
        RequestsModel requestsModel = requestsModelList.get(position);

        holder.bloodGroupTv.setText(requestsModel.getBloodGroup());
        holder.area.setText(requestsModel.getLocation());
        // holder.tvType.setText(requestsModel.getRequestType());
        holder.name.setText(requestsModel.getUserName());
        //  String imageUrl = dataSnapshot.child(Constants.IMAGE).getValue(String.class);
        context = holder.itemView.getContext();

        Glide.with(context)
                .load(requestsModel.getUserImage())
                //.placeholder()
                .into(holder.circleImageView);

        if (requestsModel.getRequestType().equals("0")) {
            holder.requestType.setText("Donar");

        } else {
            requestsModel.getRequestType().equals("1");
            holder.requestType.setText("Receiver");
        }


    }

    @Override
    public int getItemCount() {
        return requestsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name, area, bloodGroupTv;
        TextView requestType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.profileImg);
            name = itemView.findViewById(R.id.Name);
            area = itemView.findViewById(R.id.area);
            bloodGroupTv = itemView.findViewById(R.id.bloodGroup);
            requestType = itemView.findViewById(R.id.donorReceiver);
        }
    }
}
