package app.dotinfiny.Bdf.UI.addrequestfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import app.dotinfiny.Bdf.R;


public class AddRequestFragment extends Fragment {
    TextView btnRequestForBlood, btnDonar;


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
        return inflater.inflate(R.layout.fragment_addrequest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
    }

    private void clickListener() {
        btnRequestForBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(AddRequestFragmentDirections.actionAddRequestFragmentToRequestFragment(true));


            }
        });

        btnDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(AddRequestFragmentDirections.actionAddRequestFragmentToRequestFragment(false));
            }
        });


    }

    private void init(View view) {
        btnRequestForBlood = view.findViewById(R.id.btnRequestforDonar);
        btnDonar = view.findViewById(R.id.btnDonarRequest);

    }
}
