package app.dotinfiny.Bdf.UI.profilefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.dotinfiny.Bdf.Constants;
import app.dotinfiny.Bdf.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    ViewPager2 myViewPager2MyProfile;
    ProfileViewPagerAdapter profileViewPagerAdapter;
    TabLayout tabLayoutmyProfile;
    ImageView BackButtonmyProfile;
    ImageView SetProfilebtn;
    public DatabaseReference myRef;
    public String userID;
    public String selectedBloodGroup = null;

    TextView txtUsernameProfile;
    TextView txtBloodGroupProfile;
    CircleImageView circleImageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();

        //     Glide.get(getActivity()).clearMemory();


//        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
//        Glide.with(context).load().apply(requestOptions).into(circleImageView);

        myRef.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showDataProfile(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showDataProfile(DataSnapshot dataSnapshot) {
        try {
//            final ProgressDialog progressDialog = new ProgressDialog(getContext());
//            progressDialog.setTitle("Data getting..");
//            progressDialog.show();
            String Uid = dataSnapshot.child("uid").getValue(String.class);
            String userEmailfromDB = dataSnapshot.child(Constants.USER_EMAIL).getValue(String.class);
            String usernameFromDB = dataSnapshot.child(Constants.USER_NAME).getValue(String.class);
            String userPhoneFromDB = dataSnapshot.child(Constants.USER_PHONE).getValue(String.class);
            String bloodgroup = dataSnapshot.child(Constants.BLOOD_GROUP).getValue(String.class);

            if (dataSnapshot.hasChild(Constants.IMAGE)) {
                String imageUrl = dataSnapshot.child(Constants.IMAGE).getValue(String.class);
                Glide.with(getActivity())
                        .load(imageUrl)
                        // .thumbnail(0.25f)
                        //.placeholder()
                        .into(circleImageView);

            }
            txtUsernameProfile.setText(usernameFromDB);
            txtBloodGroupProfile.setText(bloodgroup);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void clickListener() {
        SetProfilebtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_settingProfileFragment);
        });
        BackButtonmyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });


    }

    private void init(View view) {

        SetProfilebtn = view.findViewById(R.id.SettingProfile);
        BackButtonmyProfile = view.findViewById(R.id.backBtn_myProfile);
        tabLayoutmyProfile = view.findViewById(R.id.tabs_myProfile);
        myViewPager2MyProfile = view.findViewById(R.id.view_pager_myprofile);
        myViewPager2MyProfile.setOffscreenPageLimit(2);
        //  myViewPager2.setUserInputEnabled(false);
        profileViewPagerAdapter = new ProfileViewPagerAdapter(this);
        myViewPager2MyProfile.setAdapter(profileViewPagerAdapter);
        txtUsernameProfile = view.findViewById(R.id.Name_myProfile);
        txtBloodGroupProfile = view.findViewById(R.id.BloodGroup_myProfile);
        circleImageView = view.findViewById(R.id.profileImg_myProfile);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayoutmyProfile, myViewPager2MyProfile, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Donar Request");

                } else if (position > 0) {
                    tab.setText("Blood Request");

                }

            }
        });
        tabLayoutMediator.attach();

    }


}

