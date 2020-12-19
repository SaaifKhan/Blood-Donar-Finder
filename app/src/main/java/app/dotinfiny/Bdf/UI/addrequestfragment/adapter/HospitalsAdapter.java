package app.dotinfiny.Bdf.UI.addrequestfragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.addrequestfragment.model.HospitalModel;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.ViewHolder> {

    private final int selected_position = -1;
    CLickListener HospitalNameCLickListener;


    // private List<HospitalModel> hospitalModels;
    List<HospitalModel> hospitalModels = new ArrayList<>();


    public HospitalsAdapter(List<HospitalModel> hospitalModels, CLickListener cLickListener) {
        this.hospitalModels = hospitalModels;
        this.HospitalNameCLickListener = cLickListener;

    }

    @NonNull
    @Override
    public HospitalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.layout_row_items_hospitals, viewGroup, false);
        return new ViewHolder(itemView);
    }

//    public HospitalsAdapter(List<HospitalModel> hospitalModelList, RequestFragment requestFragment) {
//
//    }

    @Override
    public void onBindViewHolder(@NonNull HospitalsAdapter.ViewHolder viewHolder, final int i) {
        HospitalModel hospitalModel = hospitalModels.get(i);

        viewHolder.hospitalName.setText(hospitalModel.getHospitalName());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HospitalNameCLickListener.onClick(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return hospitalModels.size();
    }

    public interface CLickListener {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.tvHospitalNames);

        }
    }
}
