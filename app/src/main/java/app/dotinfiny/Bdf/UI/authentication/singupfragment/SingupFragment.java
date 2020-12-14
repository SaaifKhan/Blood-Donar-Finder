package app.dotinfiny.Bdf.UI.authentication.singupfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import app.dotinfiny.Bdf.R;


public class SingupFragment extends Fragment {

    EditText EtUserName,EtEmail,EtPassword,EtPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        oncreate();
    }


    private void oncreate() {
    }


    private void init(View view) {
        EtUserName = view.findViewById(R.id.ed_name_singup);
        EtEmail = view.findViewById(R.id.edEmail_singup);
        EtPhone = view.findViewById(R.id.etNumber_singup);
        EtPassword = view.findViewById(R.id.etPassword_singup);


    }

}