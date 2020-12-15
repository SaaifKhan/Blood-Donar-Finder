package app.dotinfiny.Bdf.UI.profiledetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileDetail;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragmentManager) {
        super(fragmentManager);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ChildProfileDetail();
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}


