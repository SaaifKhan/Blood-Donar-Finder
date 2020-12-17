package app.dotinfiny.Bdf.UI.addrequestfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.addrequestfragment.adapter.BloodGroupAdapter;
import app.dotinfiny.Bdf.UI.homeFragment.adapter.HomeProfileAdapter;


public class RequestFragment extends Fragment {
    RecyclerView recyclerView;
  public   BloodGroupAdapter bloodGroupAdapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bloodGroupAdapter = new BloodGroupAdapter();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
    }

    private void clickListener() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bloodGroupAdapter);



    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewBloodGroup);

    }
}
