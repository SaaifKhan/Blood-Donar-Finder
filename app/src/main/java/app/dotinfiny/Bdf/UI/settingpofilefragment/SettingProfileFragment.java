package app.dotinfiny.Bdf.UI.settingpofilefragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.settingpofilefragment.adapters.BloodGroupAdapterSettingProfile;


public class SettingProfileFragment extends Fragment {
    public DatabaseReference myRef;
    public String userID;
    public String selectedBloodGroup = null;
    RecyclerView recyclerView;
    TextView tvErrorUname, tvErrorPhone;
    EditText Username, Phone;
    TextView Email, SubmitBtn;
    SharedPreferences preferences;
    BloodGroupAdapterSettingProfile bloodGroupAdapterSettingProfile;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        myRef.child("users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        bloodGroupAdapterSettingProfile = new BloodGroupAdapterSettingProfile(selectedBloodGroup, getListOfBloodGroup(), new BloodGroupAdapterSettingProfile.CLickListener() {
            @Override
            public void onClick(int position) {
                selectedBloodGroup = getListOfBloodGroup().get(position);
                bloodGroupAdapterSettingProfile.setIsSelectedBlood(selectedBloodGroup);

            }
        });


        return inflater.inflate(R.layout.fragment_setting_profile, container, false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);
        onClick();
    }

    private void onClick() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bloodGroupAdapterSettingProfile);


        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(Username.getText().toString())) {
                    if (!TextUtils.isEmpty(Phone.getText().toString())) {

                        HitSubmit(Username.getText().toString(), Phone.getText().toString(), Email.getText().toString(), selectedBloodGroup);
                        //   Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();


                    } else {
                        SetErrorMessage(tvErrorPhone, Phone);
                    }


                } else {
                    SetErrorMessage(tvErrorUname, Username);
                }
            }
        });

    }

    private void HitSubmit(String name, String PhoneNumber, String Email, String bloodGroup) {
        HashMap<String, String> updatedValue = new HashMap<>();
        updatedValue.put("uid", userID);
        updatedValue.put("UserName", name);
        updatedValue.put("UserPhone", PhoneNumber);
        updatedValue.put("UserEmail", Email);
        updatedValue.put("BloodGroup", bloodGroup);
        myRef.child("users").child(userID).setValue(updatedValue).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                boolean newUser = preferences.getBoolean("IsNewUser", false);

                if (newUser) {
                    Navigation.findNavController(getView()).navigate(R.id.action_settingProfileFragment_to_homeFragment);


                } else {

                }


            }
        });
    }


    private void init(View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewBloodGroupprofilesetting);
        Username = view.findViewById(R.id.ed_name_profilesetting);
        Phone = view.findViewById(R.id.etNumber_profilesetting);
        Email = view.findViewById(R.id.edEmail_profilesetting);
        SubmitBtn = view.findViewById(R.id.Submit_profile_settings);
        mAuth = FirebaseAuth.getInstance();
        tvErrorPhone = view.findViewById(R.id.tvErrorPhone_profilesetting);
        tvErrorUname = view.findViewById(R.id.TvErrorUsername_profilesetting);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void showData(DataSnapshot dataSnapshot) {
        try {

            String Uid = dataSnapshot.child("uid").getValue(String.class);
            String userEmailfromDB = dataSnapshot.child("UserEmail").getValue(String.class);
            String usernameFromDB = dataSnapshot.child("UserName").getValue(String.class);
            String userPhoneFromDB = dataSnapshot.child("UserPhone").getValue(String.class);
            String bloodgroup = dataSnapshot.child("BloodGroup").getValue(String.class);

            selectedBloodGroup = bloodgroup;
            bloodGroupAdapterSettingProfile.setIsSelectedBlood(selectedBloodGroup);
            bloodGroupAdapterSettingProfile.notifyDataSetChanged();
            Username.setText(usernameFromDB);
            Phone.setText(userPhoneFromDB);
            Email.setText(userEmailfromDB);


        } catch (Exception e) {

        }
    }


    void SetErrorMessage(TextView textView, EditText editText) {
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


