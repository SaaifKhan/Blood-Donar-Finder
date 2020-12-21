package app.dotinfiny.Bdf.UI.profilefragment.childProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileAdapter;

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.ViewHolder> {
    @NonNull
    @Override
    public MyProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_cards_rows_profile_child, parent, false);
        return new MyProfileAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProfileAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
