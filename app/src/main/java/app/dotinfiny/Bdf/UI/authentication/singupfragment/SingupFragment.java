package app.dotinfiny.Bdf.UI.authentication.singupfragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import app.dotinfiny.Bdf.R;


public class SingupFragment extends Fragment {

    EditText EtUserName, EtEmail, EtPassword, EtPhone;
    TextView btnSingup;
    ImageView imgClose;


    Boolean isvalidate = true;

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
        onCreate();
    }


    private void onCreate() {

        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(EtUserName.getText())) {
                    EtUserName.setError("Type UserName");
                }
                if (TextUtils.isEmpty(EtEmail.getText())) {
                    EtEmail.setError("Type Email");
                }
                if (TextUtils.isEmpty(EtPhone.getText())) {
                    EtPhone.setError("Phone Missing");
                }
                if (TextUtils.isEmpty(EtPassword.getText())) {
                    EtPassword.setError("Password Missing");
                }


                if (TextUtils.isEmpty(EtUserName.getText())) {
                    isvalidate = false;
                } else if (TextUtils.isEmpty(EtEmail.getText())) {
                    isvalidate = false;
                } else if (TextUtils.isEmpty(EtPhone.getText())) {
                    isvalidate = false;
                } else if (TextUtils.isEmpty(EtPassword.getText())) {
                    isvalidate = false;

                } else {
                    isvalidate = true;
                }

                if (isvalidate) {
                    Toast.makeText(getActivity(),"All Perfect!",Toast.LENGTH_SHORT).show();

                }
            }

        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();

            }
        });



    }

    private void init(View view) {
        EtUserName = view.findViewById(R.id.ed_name_singup);
        EtEmail = view.findViewById(R.id.edEmail_singup);
        EtPhone = view.findViewById(R.id.etNumber_singup);
        EtPassword = view.findViewById(R.id.etPassword_singup);
        btnSingup = view.findViewById(R.id.btnSingUp);
        imgClose = view.findViewById(R.id.imgClose_signup);


    }

}