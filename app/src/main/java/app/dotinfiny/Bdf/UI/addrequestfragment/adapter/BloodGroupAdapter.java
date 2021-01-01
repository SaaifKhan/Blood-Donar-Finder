package app.dotinfiny.Bdf.UI.addrequestfragment.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.settingpofilefragment.adapters.BloodGroupAdapterSettingProfile;

public class BloodGroupAdapter extends RecyclerView.Adapter<BloodGroupAdapter.ViewHolder> {
    static List<String> bloodGroups;
    private Context Context;
    private final TextView selectedTextview = null;
    BloodGroupAdapter.CLickListener BloodGroupSettingClickListener;
    SharedPreferences preferences;
    private Context context;
    private String isSelectedBlood = null;
    private TextView selectedTextView = null;


    public BloodGroupAdapter(List<String> BloodGroups, BloodGroupAdapter.CLickListener bloodGroupcCLickListener) {
        BloodGroupSettingClickListener = bloodGroupcCLickListener;
        bloodGroups = BloodGroups;
    }


    @NonNull
    @Override
    public BloodGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.items_rows_blood_group, parent, false);
        return new BloodGroupAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final BloodGroupAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.BloodGroups.setText(bloodGroups.get(i));

        if (isSelectedBlood != null) {
            if (bloodGroups.get(i).equalsIgnoreCase(isSelectedBlood)) {
                if (selectedTextView != null) {
                    selectedTextView.setBackgroundResource(R.drawable.circleoutline);
                    int bgColor = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorPrimaryDark);
                    selectedTextView.setTextColor(bgColor);


                    viewHolder.BloodGroups.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorWhite);
                    viewHolder.BloodGroups.setTextColor(bgColor1);
                    selectedTextView = viewHolder.BloodGroups;

                } else {
                    viewHolder.BloodGroups.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorWhite);
                    viewHolder.BloodGroups.setTextColor(bgColor1);
                    selectedTextView = viewHolder.BloodGroups;

                }
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTextView != null) {
                    selectedTextView.setBackgroundResource(R.drawable.circleoutline);
                    int bgColor = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorPrimaryDark);
                    selectedTextView.setTextColor(bgColor);

                    viewHolder.BloodGroups.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorWhite);
                    viewHolder.BloodGroups.setTextColor(bgColor1);
                    selectedTextView = viewHolder.BloodGroups;

                } else {
                    viewHolder.BloodGroups.setBackgroundResource(R.drawable.circle);
                    int bgColor = ContextCompat.getColor(viewHolder.BloodGroups.getContext(), R.color.colorWhite);
                    viewHolder.BloodGroups.setTextColor(bgColor);
                    selectedTextView = viewHolder.BloodGroups;
                }

                BloodGroupSettingClickListener.onClick(i);


                // holder.bloodGroupsSettingProfile.setText(new ArrayList());


            }


        });

    }

    @Override
    public int getItemCount() {
        return bloodGroups.size();
    }

    public void setIsSelectedBlood(String isSelectBlood) {
        isSelectedBlood = isSelectBlood;
    }

    public interface CLickListener {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BloodGroups;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            BloodGroups = itemView.findViewById(R.id.tvbloodGroups);
        }
    }
}

