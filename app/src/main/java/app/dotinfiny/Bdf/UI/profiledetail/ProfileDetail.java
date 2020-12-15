package app.dotinfiny.Bdf.UI.profiledetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import app.dotinfiny.Bdf.R;


public class ProfileDetail extends Fragment {
    ViewPager2 myViewPager2;
    ViewPagerAdapter myAdapter;
    TabLayout tabLayout;


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
        return inflater.inflate(R.layout.fragment_profiledetail, container, false);

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
        tabLayout = view.findViewById(R.id.tabProfileDetail);
        myViewPager2 = view.findViewById(R.id.view_pager);
        myViewPager2.setOffscreenPageLimit(2);
        myAdapter = new ViewPagerAdapter(this);
        myViewPager2.setAdapter(myAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, myViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position ==0){
                    tab.setText("Receiver");

                }
                else if(position > 0){
                    tab.setText("donar");

                }

            }
        });
        tabLayoutMediator.attach();

    }
}
