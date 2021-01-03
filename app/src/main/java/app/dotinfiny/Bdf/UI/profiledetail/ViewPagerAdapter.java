package app.dotinfiny.Bdf.UI.profiledetail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileDetail;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragmentManager) {
        super(fragmentManager);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ChildProfileDetail(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}


