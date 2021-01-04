package app.dotinfiny.Bdf.UI.profilefragment.childProfile;

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
import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.ViewHolder> {
    List<RequestsModel> requestsModelList;
    Context context;


    public MyProfileAdapter(List<RequestsModel> requestsModelList) {
        this.requestsModelList = requestsModelList;
    }

    @NonNull
    @Override
    public MyProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_cards_rows_profile_child, parent, false);
        return new MyProfileAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProfileAdapter.ViewHolder holder, int position) {
        RequestsModel requestsModel = requestsModelList.get(position);
        holder.myProfileBloodGroup.setText(requestsModel.getBloodGroup());
        holder.myProfileLocation.setText(requestsModel.getLocation());
        // holder.tvType.setText(requestsModel.getRequestType());
        holder.myProfileName.setText(requestsModel.getUserName());
        //  String imageUrl = dataSnapshot.child(Constants.IMAGE).getValue(String.class);
        context = holder.itemView.getContext();

//        Glide.with(context)
//                .load(requestsModel.getUserImage())
//                //.placeholder()
//                .into(holder.imageView);

        if (requestsModel.getRequestType().equals("0")) {
            holder.myProfileRequestType.setText("Donar");

        } else {
            requestsModel.getRequestType().equals("1");
            holder.myProfileRequestType.setText("Receiver");
        }


    }


    @Override
    public int getItemCount() {
        return requestsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView myProfileName, myProfileLocation, myProfileBloodGroup, myProfileRequestType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myProfileName = itemView.findViewById(R.id.Name);
            myProfileRequestType = itemView.findViewById(R.id.donorReceiver);
            myProfileLocation = itemView.findViewById(R.id.area);
            myProfileBloodGroup = itemView.findViewById(R.id.bloodGroup);
        }
    }
}
