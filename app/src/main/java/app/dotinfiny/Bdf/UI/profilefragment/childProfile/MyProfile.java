package app.dotinfiny.Bdf.UI.profilefragment.childProfile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileAdapter;


public class MyProfile extends Fragment {
    RecyclerView recyclerViewMyProfile;
    MyProfileAdapter myProfileAdapter;
    public DatabaseReference myRef;
    public String userID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

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


        myRef.child("BloodRequest").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                showData(snapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showData(DataSnapshot snapshot) {
        try {
//            final ProgressDialog progressDialog = new ProgressDialog(getContext());
//            progressDialog.setTitle("Data getting..");
//            progressDialog.show();
            String Uid = snapshot.child("uid").getValue(String.class);
            String BloodGroupFromDb = snapshot.child("bloodgroup").getValue(String.class);
            String DetailFromDb = snapshot.child("detail").getValue(String.class);
            String HospitalFromDb = snapshot.child("hospital").getValue(String.class);
            String LocationFromDb = snapshot.child("location").getValue(String.class);

//            if (snapshot.hasChild("image")) {
//                String imageUrl = snapshot.child("image").getValue(String.class);
//                Glide.with(getActivity())
//                        .load(imageUrl)
//                        //.placeholder()
//                       // .into(circleImageView);
//            }


//            selectedBloodGroup = bloodgroup;
//            bloodGroupAdapterSettingProfile.setIsSelectedBlood(selectedBloodGroup);
//            bloodGroupAdapterSettingProfile.notifyDataSetChanged();
//            Username.setText(usernameFromDB);
//            Phone.setText(userPhoneFromDB);
//            Email.setText(userEmailfromDB);

        } catch (Exception e) {
            e.printStackTrace();
        }
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