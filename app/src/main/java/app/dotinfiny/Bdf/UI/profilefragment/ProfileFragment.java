package app.dotinfiny.Bdf.UI.profilefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import app.dotinfiny.Bdf.R;


public class ProfileFragment extends Fragment {

    ViewPager2 myViewPager2MyProfile;
    ProfileViewPagerAdapter profileViewPagerAdapter;
    TabLayout tabLayoutmyProfile;
    ImageView BackButtonmyProfile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    private void clickListener() {


    }

    private void init(View view) {

        BackButtonmyProfile = view.findViewById(R.id.backBtn_myProfile);
        tabLayoutmyProfile = view.findViewById(R.id.tabs_myProfile);
        myViewPager2MyProfile = view.findViewById(R.id.view_pager_myprofile);
        myViewPager2MyProfile.setOffscreenPageLimit(2);
        //  myViewPager2.setUserInputEnabled(false);
        profileViewPagerAdapter = new ProfileViewPagerAdapter(this);
        myViewPager2MyProfile.setAdapter(profileViewPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayoutmyProfile, myViewPager2MyProfile, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Request");

                } else if (position > 0) {
                    tab.setText("Request");

                }

            }
        });
        tabLayoutMediator.attach();

    }
}
