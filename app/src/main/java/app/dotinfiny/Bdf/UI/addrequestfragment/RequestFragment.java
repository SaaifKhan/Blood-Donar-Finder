package app.dotinfiny.Bdf.UI.addrequestfragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.addrequestfragment.adapter.BloodGroupAdapter;
import app.dotinfiny.Bdf.UI.addrequestfragment.adapter.HospitalsAdapter;
import app.dotinfiny.Bdf.UI.addrequestfragment.model.HospitalModel;


public class RequestFragment extends Fragment {
    RecyclerView recyclerView;
    TextView RequiredBloodGroupOfReceiver;
    public BloodGroupAdapter bloodGroupAdapter;
    ImageView imgBack;
    EditText TvHospitals, Tvlocation;
    EditText TvDesc;
    TextView TvName;
    public DatabaseReference myRefRequest;
    TextView title;
    TextView btnReqBlood;
    TextView TextViewBloodForDonar, BloodGroupOfDonar;
    TextView TvError, TvErrorLocations, TvErrorHospital;
    RecyclerView HospitalListRecyclerview;
    HospitalsAdapter hospitalsAdapter;
    ExpandableLayout expandableLayout;
    public String userID;
    public String selectedBloodGroup = null;
    TextView TvLocation;
    RequestFragmentArgs args;


    //  List<HospitalModel> hospitalModelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        myRefRequest = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        args = RequestFragmentArgs.fromBundle(getArguments());


        // Drawable error = TvError.getBackground(R.drawable.error_drawable,);


        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();

//        myRefRequest.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ShowData(dataSnapshot);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        checkEdittextWatcher(TvDesc, TvError);
        checkEdittextWatcher(TvHospitals, TvErrorHospital);
        checkEdittextWatcher(Tvlocation, TvErrorLocations);
        RequestFragmentArgs args = RequestFragmentArgs.fromBundle(getArguments());
        Toast.makeText(getContext(), "It Donor " + args.getIsDonar(), Toast.LENGTH_SHORT).show();
        if (args.getIsDonar()) {
            title.setText("Become Donar");
            TextViewBloodForDonar.setVisibility(View.VISIBLE);
            BloodGroupOfDonar.setVisibility(View.VISIBLE);

            myRefRequest.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ShowData(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        } else {


            title.setText("Request For Blood");
            RequiredBloodGroupOfReceiver.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            myRefRequest.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ShowDataRequest(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    private void ShowDataRequest(DataSnapshot dataSnapshot) {
        {
            try {

                String usernameFromDB = dataSnapshot.child("UserName").getValue(String.class);
                String bloodgroup = dataSnapshot.child("BloodGroup").getValue(String.class);


                TvName.setText(usernameFromDB);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void ShowData(DataSnapshot dataSnapshot) {
        try {
            String userEmailfromDB = dataSnapshot.child("UserEmail").getValue(String.class);
            String usernameFromDB = dataSnapshot.child("UserName").getValue(String.class);
            String userPhoneFromDB = dataSnapshot.child("UserPhone").getValue(String.class);
            selectedBloodGroup = dataSnapshot.child("BloodGroup").getValue(String.class);

            TvName.setText(usernameFromDB);
            BloodGroupOfDonar.setText(selectedBloodGroup);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clickListener() {


        recyclerView.setHasFixedSize(true);


        HospitalListRecyclerview.setHasFixedSize(true);
        hospitalsAdapter = new HospitalsAdapter(HospitalName(), new HospitalsAdapter.CLickListener() {
            @Override
            public void onClick(int position) {
                HospitalModel model = HospitalName().get(position);
                TvHospitals.setText(model.getHospitalName());
                expandableLayout.setExpanded(false, true);
            }
        });
        HospitalListRecyclerview.setAdapter(hospitalsAdapter);


//        recyclerViewDonar.setHasFixedSize(true);
        //      recyclerViewDonar.setAdapter(bloodGroupAdapter);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
        btnReqBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (args.getIsDonar()) {
                    if (!TextUtils.isEmpty(TvDesc.getText().toString())) {
                        if (!TextUtils.isEmpty(TvHospitals.getText().toString())) {
                            if (!TextUtils.isEmpty(Tvlocation.getText().toString())) {
                                //savedata
                                HitSubmit(TvDesc.getText().toString(), TvHospitals.getText().toString(), TvLocation.getText().toString(), selectedBloodGroup);
                            } else {
                                setErrorMessage(TvErrorLocations, Tvlocation);
                            }
                        } else {
                            setErrorMessage(TvErrorHospital, TvHospitals);
                        }
                    } else {
                        setErrorMessage(TvError, TvDesc);
                    }


                } else {
                    if (!TextUtils.isEmpty(TvDesc.getText().toString())) {
                        if (!TextUtils.isEmpty(TvHospitals.getText().toString())) {
                            if (!TextUtils.isEmpty(Tvlocation.getText().toString())) {
                                //savedata
                                HitSubmitRequest(selectedBloodGroup, TvDesc.getText().toString(), TvHospitals.getText().toString(), TvLocation.getText().toString());
                            } else {
                                setErrorMessage(TvErrorLocations, Tvlocation);
                            }
                        } else {
                            setErrorMessage(TvErrorHospital, TvHospitals);
                        }
                    } else {
                        setErrorMessage(TvError, TvDesc);
                    }

                }
            }

        });


        TvHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout.setExpanded(!expandableLayout.isExpanded(), true);
            }
        });


    }

    private void HitSubmitRequest(String BloodGroup, String Details, String Hospitals, String Location) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("submiting...");
        progressDialog.show();
        Map<String, Object> submitValue = new HashMap<>();
        // String uid = task.getResult().getUser().getUid();
        submitValue.put("id", userID);
        submitValue.put("detail", Details);
        submitValue.put("location", Hospitals);
        submitValue.put("hospital", Location);
        submitValue.put("bloodgroup", BloodGroup);

        myRefRequest.child("BloodRequest").child(userID).child(myRefRequest.push().getKey()).setValue(submitValue).addOnSuccessListener(aVoid -> {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "submitted", Toast.LENGTH_SHORT).show();
//                SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE);
//                preferences.edit().putBoolean(Constants.ISNEWUSER, false).apply();
        });


    }

    private void HitSubmit(String detail, String hospital, String location, String BloodGroup) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("submiting...");
        progressDialog.show();
        Map<String, Object> submitValue = new HashMap<>();
        // String uid = task.getResult().getUser().getUid();
        submitValue.put("id", userID);
        submitValue.put("detail", detail);
        submitValue.put("location", location);
        submitValue.put("hospital", hospital);
        submitValue.put("bloodgroup", BloodGroup);


        myRefRequest.child("BloodDonar").child(userID).child(myRefRequest.push().getKey()).setValue(submitValue).addOnSuccessListener(aVoid -> {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "submitted", Toast.LENGTH_SHORT).show();
//                SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE);
//                preferences.edit().putBoolean(Constants.ISNEWUSER, false).apply();
        });
    }


    private void init(View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewBloodGroup);
//        recyclerViewDonar = view.findViewById(R.id.RecyclerViewBloodDonar);
        imgBack = view.findViewById(R.id.imgBackRequest);
        btnReqBlood = view.findViewById(R.id.btnSubmitRequestBlood);
        TvName = view.findViewById(R.id.EtUsernameBloodRequest);
        TvDesc = view.findViewById(R.id.EdDescRequest);
        TvHospitals = view.findViewById(R.id.Hospitals);
        TvLocation = view.findViewById(R.id.LocationRequestBlood);
        TvError = view.findViewById(R.id.tvErrorDesc);
        TvErrorHospital = view.findViewById(R.id.tvErrorHospital);
        TvErrorLocations = view.findViewById(R.id.tvErrorLocation);
        Tvlocation = view.findViewById(R.id.LocationRequestBlood);
        title = view.findViewById(R.id.title);
        TextViewBloodForDonar = view.findViewById(R.id.TvBloodGroupDonar);
        BloodGroupOfDonar = view.findViewById(R.id.BloodGroupofDonar);
        RequiredBloodGroupOfReceiver = view.findViewById(R.id.TvRequiredBloodGroup);
        HospitalListRecyclerview = view.findViewById(R.id.recyclerviewHospitals);
        expandableLayout = view.findViewById(R.id.expandable_layout);


        bloodGroupAdapter = new BloodGroupAdapter(getListOfBloodGroup(), new BloodGroupAdapter.CLickListener() {
            @Override
            public void onClick(int position) {
                selectedBloodGroup = getListOfBloodGroup().get(position);
                bloodGroupAdapter.setIsSelectedBlood(selectedBloodGroup);
                bloodGroupAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(bloodGroupAdapter);


    }


    void setErrorMessage(TextView textView, EditText editText) {
        textView.setVisibility(View.VISIBLE);
        editText.setBackground(
                ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.error_drawable));
    }


    public void checkEdittextWatcher(final EditText editText, final TextView textView) {
        final boolean[] isValidate = {false};
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.setVisibility(View.GONE);

                editText.setBackgroundResource(R.drawable.rectangle);

            }
        });

    }

    public List<HospitalModel> HospitalName() {
        final List<HospitalModel> list = new ArrayList<>();

        list.add(new HospitalModel(1, "Liaquat National"));
        list.add(new HospitalModel(2, "Aga khan"));
        list.add(new HospitalModel(2, "Aga khan"));
        list.add(new HospitalModel(2, "Aga khan"));
        list.add(new HospitalModel(3, "abc"));

        return list;
    }

    private ArrayList<String> getListOfBloodGroup() {
        ArrayList<String> bloodGroups = new ArrayList<String>();
        bloodGroups.add("A+");
        bloodGroups.add("A-");
        bloodGroups.add("B+");
        bloodGroups.add("O-");
        bloodGroups.add("O+");
        bloodGroups.add("AB+");
        bloodGroups.add("AB-");
        return bloodGroups;
    }

}
