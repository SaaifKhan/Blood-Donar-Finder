package app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

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


public class ChildProfileDetail extends Fragment {

    public DatabaseReference myRef;
    public String userId;

    RecyclerView recyclerView;
    ChildProfileAdapter childProfileAdapter;
    int selectedPosition;
    String selectedUserId;
    List<RequestsModel> requestsModelListDonar = new ArrayList<>();
    List<RequestsModel> requestsModelListRequest = new ArrayList<>();

    public ChildProfileDetail(int position, String userId) {
        this.selectedPosition = position;
        this.selectedUserId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        userId = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference();


        myRef.child("BloodRequests").child(selectedUserId).addValueEventListener(new ValueEventListener() {


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
                }
                // Log.d("Data", "" + ds.getChildrenCount());
                childProfileAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_childprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
    }

    private void clickListener() {


    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.item_recyclerViewChildDetail);
        if (selectedPosition == 0) {
            childProfileAdapter = new ChildProfileAdapter(requestsModelListDonar);
        } else {
            childProfileAdapter = new ChildProfileAdapter(requestsModelListRequest);
        }
        recyclerView.setAdapter(childProfileAdapter);


    }
}
