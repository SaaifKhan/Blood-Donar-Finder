package app.dotinfiny.Bdf.UI.homeFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import app.dotinfiny.Bdf.UI.homeFragment.adapter.HomeProfileAdapter;
import app.dotinfiny.Bdf.UI.homeFragment.model.RequestsModel;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeProfileAdapter homeProfileAdapter;
    public DatabaseReference myRef;
    public String userId;
    List<RequestsModel> requestsModelList = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        userId = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference();



        //
        myRef.child("BloodRequests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    for (DataSnapshot child : ds.getChildren()) {
                        Log.d(TAG, "onDataChange: " + child.getValue());
                        String id = child.child("id").getValue().toString();
                        String Details = child.child(Constants.DETAILS).getValue(String.class);
                        String Location = child.child(Constants.AREA).getValue(String.class);
                        String Hospital = child.child(Constants.HOSPITAL).getValue(String.class);
                        String BloodGroup = child.child(Constants.BLOOD_GROUP).getValue(String.class);
                        String RequestType = child.child(Constants.REQUEST_TYPE).getValue(String.class);
                        String usernameFromDB = child.child(Constants.USER_NAME).getValue(String.class);
                        String userImage = child.child(Constants.IMAGE).getValue(String.class);
                        String userPhone = child.child(Constants.USER_PHONE).getValue(String.class);
                        requestsModelList.add(new RequestsModel(id, Details, Location, Hospital, BloodGroup, RequestType, usernameFromDB, userImage, userPhone));
                    }
                    Log.d("sa", "" + ds.getChildrenCount());
                }

                homeProfileAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();


            }
        });
    }

//    private void ShowDataRequest(DataSnapshot dataSnapshot) {
//        try {
//
//            String usernameFromDB = dataSnapshot.child("UserName").getValue(String.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();

        if (requestsModelList.size() > 0) {
            progressBar.setVisibility(View.GONE);
        } else {

        }

    }

    private void clickListener() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeProfileAdapter);


    }

    private void init(final View view) {
        progressBar = view.findViewById(R.id.ProgressBarHome);
        recyclerView = view.findViewById(R.id.item_recyclerView);
        homeProfileAdapter = new HomeProfileAdapter(requestsModelList, new HomeProfileAdapter.CLickListener() {
            @Override
            public void onClick(int position) {
                RequestsModel model = requestsModelList.get(position);
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToProfileDetail2(model));

            }
        });
    }
}
