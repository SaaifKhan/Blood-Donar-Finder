package app.dotinfiny.Bdf.UI.addrequestfragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.homeFragment.adapter.HomeProfileAdapter;
import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileAdapter;

public class BloodGroupAdapter  extends RecyclerView.Adapter<BloodGroupAdapter.ViewHolder>{
    public BloodGroupAdapter() {
    }

    @NonNull
    @Override
    public BloodGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.items_rows_blood_group,parent,false);
        return new BloodGroupAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull BloodGroupAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BloodGroups;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            itemView.findViewById(R.id.tvbloodGroups);
        }
    }
}

