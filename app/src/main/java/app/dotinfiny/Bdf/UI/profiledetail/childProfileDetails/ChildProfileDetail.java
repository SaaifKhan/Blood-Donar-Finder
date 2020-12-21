package app.dotinfiny.Bdf.UI.profiledetail.childProfileDetails;

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


public class ChildProfileDetail extends Fragment {
    RecyclerView recyclerView;
    ChildProfileAdapter childProfileAdapter;


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
        return inflater.inflate(R.layout.fragment_childprofile, container, false);
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
        recyclerView = view.findViewById(R.id.item_recyclerViewChildDetail);
        childProfileAdapter = new ChildProfileAdapter();
        recyclerView.setAdapter(childProfileAdapter);
        childProfileAdapter.notifyDataSetChanged();


    }
}
