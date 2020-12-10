package app.dotinfiny.Bdf.UI.authentication.forgotpassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.dotinfiny.Bdf.R;



public class ForgotFragment extends Fragment {

    ImageView Close;
    RelativeLayout relativeLayoutSignIn;





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
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init(view);
        onclickListener();
    }

    private void onclickListener() {
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Navigation.findNavController(v).popBackStack();
            }
        });

        relativeLayoutSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

    }



    private void Init(View view) {
        Close =  view.findViewById(R.id.forgotClose);
        relativeLayoutSignIn = view.findViewById(R.id.RelativeSignIn);

    }
}