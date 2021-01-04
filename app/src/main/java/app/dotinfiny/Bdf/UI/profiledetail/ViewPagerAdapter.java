package app.dotinfiny.Bdf.UI.profiledetail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails.ChildProfileDetail;

public class ViewPagerAdapter extends FragmentStateAdapter {

    String userId;

    public ViewPagerAdapter(@NonNull Fragment fragmentManager, String userId) {
        super(fragmentManager);
        this.userId = userId;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ChildProfileDetail(position, userId);
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}


