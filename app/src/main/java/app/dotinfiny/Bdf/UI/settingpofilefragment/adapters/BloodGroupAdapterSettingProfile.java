package app.dotinfiny.Bdf.UI.settingpofilefragment.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.dotinfiny.Bdf.R;

public class BloodGroupAdapterSettingProfile extends RecyclerView.Adapter<BloodGroupAdapterSettingProfile.ViewHolder> {

    static List<String> BloodGroups;
    CLickListener BloodGroupSettingClickListener;
    SharedPreferences preferences;
    private Context context;
    private TextView selectedTextView = null;
    private String isSelectedBlood = null;


    public BloodGroupAdapterSettingProfile(List<String> BloodGroups, CLickListener bloodGroupcCLickListener) {
        //  this.isSelectedBlood = isSelectBlood;
        BloodGroupSettingClickListener = bloodGroupcCLickListener;
        BloodGroupAdapterSettingProfile.BloodGroups = BloodGroups;
    }

    @NonNull
    @Override
    public BloodGroupAdapterSettingProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.items_rows_blood_group_profile_setting_fragment, parent, false);
        return new BloodGroupAdapterSettingProfile.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BloodGroupAdapterSettingProfile.ViewHolder holder, final int position) {

        holder.bloodGroupsSettingProfile.setText(BloodGroups.get(position));


        if (isSelectedBlood != null) {
            if (BloodGroups.get(position).equalsIgnoreCase(isSelectedBlood)) {
                if (selectedTextView != null) {
                    selectedTextView.setBackgroundResource(R.drawable.circleoutline);
                    int bgColor = ContextCompat.getColor(holder.bloodGroupsSettingProfile.getContext(), R.color.colorPrimaryDark);
                    selectedTextView.setTextColor(bgColor);


                    holder.bloodGroupsSettingProfile.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(holder.bloodGroupsSettingProfile.getContext(), R.color.colorWhite);
                    holder.bloodGroupsSettingProfile.setTextColor(bgColor1);
                    selectedTextView = holder.bloodGroupsSettingProfile;

                } else {
                    holder.bloodGroupsSettingProfile.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(holder.bloodGroupsSettingProfile.getContext(), R.color.colorWhite);
                    holder.bloodGroupsSettingProfile.setTextColor(bgColor1);
                    selectedTextView = holder.bloodGroupsSettingProfile;

                }
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTextView != null) {
                    selectedTextView.setBackgroundResource(R.drawable.circleoutline);
                    int bgColor = ContextCompat.getColor(holder.bloodGroupsSettingProfile.getContext(), R.color.colorPrimaryDark);
                    selectedTextView.setTextColor(bgColor);

                    holder.bloodGroupsSettingProfile.setBackgroundResource(R.drawable.circle);
                    int bgColor1 = ContextCompat.getColor(holder.bloodGroupsSettingProfile.getContext(), R.color.colorWhite);
                    holder.bloodGroupsSettingProfile.setTextColor(bgColor1);
                    selectedTextView = holder.bloodGroupsSettingProfile;

                } else {
                    holder.bloodGroupsSettingProfile.setBackgroundResource(R.drawable.circle);
                    int bgColor = ContextCompat.getColor(holder.bloodGroupsSettingProfile.getContext(), R.color.colorWhite);
                    holder.bloodGroupsSettingProfile.setTextColor(bgColor);
                    selectedTextView = holder.bloodGroupsSettingProfile;
                }

                BloodGroupSettingClickListener.onClick(position);


                // holder.bloodGroupsSettingProfile.setText(new ArrayList());


            }


        });

    }

    @Override
    public int getItemCount() {
        return BloodGroups.size();
    }

    public void setIsSelectedBlood(String isSelectBlood) {
        isSelectedBlood = isSelectBlood;
    }

    public interface CLickListener {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bloodGroupsSettingProfile;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            bloodGroupsSettingProfile = itemView.findViewById(R.id.tvbloodGroupsettingprofile);

        }
    }


}




