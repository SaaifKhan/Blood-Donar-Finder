package app.dotinfiny.Bdf.UI.profiledetail;

import android.content.Intent;
import android.net.Uri;
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

import app.dotinfiny.Bdf.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileDetail extends Fragment {
    ViewPager2 myViewPager2;
    ViewPagerAdapter myAdapter;
    TabLayout tabLayout;
    ImageView BackButton;
    TextView Name, tvBloodGroup;
    ProfileDetailArgs args;
    CircleImageView circleImageView;
    TextView totalNumofDonation, totalNumOfRequestBlood;
    ImageView PhoneDialler, smsDialler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        args = ProfileDetailArgs.fromBundle(getArguments());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profiledetail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();


        String name = args.getRequestModelData().getUserName();
        String bloodGroup = args.getRequestModelData().getBloodGroup();
        String profileImage = args.getRequestModelData().getUserImage();
        String userPhone = args.getRequestModelData().getUserPhone();

        PhoneDialler.setOnClickListener(v -> {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                    "tel", userPhone, null));
            startActivity(phoneIntent);
        });
        smsDialler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageToSend = "Assalmualaikum \n" + name + "here \n i need this" + "\n"
                        + bloodGroup +
                        "BloodGroup" + "\n so contact me on this Number";

                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "sms", userPhone, null));
                smsIntent.putExtra("sms_body", messageToSend);


                startActivity(smsIntent);
            }
        });


        Name.setText(name);
        tvBloodGroup.setText(bloodGroup);

        Glide.with(this)
                .load(profileImage)
                //.placeholder()
                .into(circleImageView);


    }

    private void clickListener() {
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();

            }
        });


    }

    private void init(View view) {
        smsDialler = view.findViewById(R.id.Sms);
        PhoneDialler = view.findViewById(R.id.UserPhoneDialler);

        tvBloodGroup = view.findViewById(R.id.bloodgrpTv);
        Name = view.findViewById(R.id.Name);
        circleImageView = view.findViewById(R.id.profileImgProfileDetails);
        BackButton = view.findViewById(R.id.backBtnProfileDetail);
        tabLayout = view.findViewById(R.id.tabProfileDetail);
        myViewPager2 = view.findViewById(R.id.view_pager);
        myViewPager2.setOffscreenPageLimit(2);
        //  myViewPager2.setUserInputEnabled(false);
        myAdapter = new ViewPagerAdapter(this, args.getRequestModelData().getId());
        myViewPager2.setAdapter(myAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, myViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Donars");

                } else if (position > 0) {
                    tab.setText("Requests For Blood");

                }

            }
        });
        tabLayoutMediator.attach();

    }
}
