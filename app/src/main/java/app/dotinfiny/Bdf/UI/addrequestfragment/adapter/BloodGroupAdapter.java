package app.dotinfiny.Bdf.UI.addrequestfragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessController;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.homeFragment.adapter.HomeProfileAdapter;
import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileAdapter;

public class BloodGroupAdapter  extends RecyclerView.Adapter<BloodGroupAdapter.ViewHolder>{
    CLickListener BloodGroupcCLickListener;
    private Context Context;
    private TextView selectedTextview = null;

    public BloodGroupAdapter(CLickListener bloodGroupcCLickListener) {
        BloodGroupcCLickListener = bloodGroupcCLickListener;
    }

    public  interface CLickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public BloodGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.items_rows_blood_group,parent,false);
        return new BloodGroupAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final BloodGroupAdapter.ViewHolder viewHolder,final int i) {

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTextview != null){
                    selectedTextview.setBackgroundResource(R.drawable.circleoutline);
                    int bgColor = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorPrimaryDark);
                    selectedTextview.setTextColor(bgColor);

                    viewHolder.BloodGroups.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorWhite);
                    viewHolder.BloodGroups.setTextColor(bgColor1);
                    selectedTextview = viewHolder.BloodGroups;

                }else{
                    viewHolder.BloodGroups.setBackgroundResource(R.drawable.circle);
                    int bgColor = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorWhite);
                    viewHolder.BloodGroups.setTextColor(bgColor);
                    selectedTextview = viewHolder.BloodGroups;
                }

                BloodGroupcCLickListener.onClick(i);

            }


        });

    }

    @Override
    public int getItemCount() {
        return 8;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BloodGroups;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            BloodGroups = itemView.findViewById(R.id.tvbloodGroups);
        }
    }
}

