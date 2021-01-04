package app.dotinfiny.Bdf.UI.profilefragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.dotinfiny.Bdf.UI.profilefragment.childProfile.MyProfile;

public class ProfileViewPagerAdapter extends FragmentStateAdapter {


    public ProfileViewPagerAdapter(@NonNull Fragment fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new MyProfile(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
