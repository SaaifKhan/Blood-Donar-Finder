package app.dotinfiny.Bdf.UI.profilefragment.childProfile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.dotinfiny.Bdf.Constants;
import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.homeFragment.model.RequestsModel;

import static android.content.ContentValues.TAG;


public class MyProfile extends Fragment {
    RecyclerView recyclerViewMyProfile;
    MyProfileAdapter myProfileAdapter;
    public DatabaseReference myRef;
    public String userId;

    int selectedPosition;
    List<RequestsModel> requestsModelListDonar = new ArrayList<>();
    List<RequestsModel> requestsModelListRequest = new ArrayList<>();


    public MyProfile(int position) {
        this.selectedPosition = position;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference();


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

        myRef.child("BloodRequests").child(userId).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Log.d(TAG, "onDataChange: " + ds.getValue());
                    String id = ds.child(Constants.ID).getValue().toString();
                    String Details = ds.child(Constants.DETAILS).getValue().toString();
                    String Location = ds.child(Constants.AREA).getValue().toString();
                    String Hospital = ds.child(Constants.HOSPITAL).getValue().toString();
                    String bloodGroup = ds.child(Constants.BLOOD_GROUP).getValue().toString();
                    String requestType = ds.child(Constants.REQUEST_TYPE).getValue().toString();
                    String userName = ds.child(Constants.USER_NAME).getValue().toString();
                    String userImage = ds.child(Constants.IMAGE).getValue(String.class);
                    String userPhone = ds.child(Constants.USER_PHONE).getValue(String.class);
                    if (requestType.equals("0")) {
                        requestsModelListDonar.add(new RequestsModel(id, Details, Location, Hospital, bloodGroup, requestType, userName, userImage, userPhone));
                    } else {
                        requestsModelListRequest.add(new RequestsModel(id, Details, Location, Hospital, bloodGroup, requestType, userName, userImage, userPhone));
                    }
                    Log.d("Data", "" + ds.getChildrenCount());
                }
                myProfileAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void init(View view) {
        recyclerViewMyProfile = view.findViewById(R.id.item_recyclerViewChildMyprofile);
        if (selectedPosition == 0) {
            myProfileAdapter = new MyProfileAdapter(requestsModelListDonar);
        } else {
            myProfileAdapter = new MyProfileAdapter(requestsModelListRequest);
        }
        recyclerViewMyProfile.setAdapter(myProfileAdapter);

    }

    private void Oncreate() {
        recyclerViewMyProfile.setHasFixedSize(true);
    }

//    private void arrayLog(){
//        Log.d(TAG, "arrayLog: "+ getDetails());
////        for (i= 0; array.lenght; i++) {
////            //
////            Log.d(TAG, "arrayLog: "+);
////        }
////
//        }
//
}
    
    
    
    

