package app.dotinfiny.Bdf.UI.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.homeFragment.adapter.HomeProfileAdapter;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeProfileAdapter homeProfileAdapter;


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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
    }

    private void clickListener() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeProfileAdapter);


    }

    private void init(final View view) {
        recyclerView = view.findViewById(R.id.item_recyclerView);
        homeProfileAdapter = new HomeProfileAdapter(new HomeProfileAdapter.CLickListener() {
            @Override
            public void onClick(int position) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileDetail2);

            }
        });
    }
}
