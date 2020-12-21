package app.dotinfiny.Bdf.UI.profilefragment.childProfile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileAdapter;


public class MyProfile extends Fragment {
    RecyclerView recyclerViewMyProfile;
    MyProfileAdapter myProfileAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        Oncreate();
    }

    private void init(View view) {
        recyclerViewMyProfile = view.findViewById(R.id.item_recyclerViewChildMyprofile);
    }

    private void Oncreate() {
        myProfileAdapter = new MyProfileAdapter();
        recyclerViewMyProfile.setHasFixedSize(true);
        recyclerViewMyProfile.setAdapter(myProfileAdapter);
        myProfileAdapter.notifyDataSetChanged();
    }
}