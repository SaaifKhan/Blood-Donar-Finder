package app.dotinfiny.Bdf.UI.authentication.loginFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import app.dotinfiny.Bdf.MainActivity;
import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.authentication.AuthenticationActivity;
import app.dotinfiny.Bdf.UI.settingpofilefragment.SettingProfileFragment;


public class LoginFragment extends Fragment {

    public FirebaseAuth mAuth;
    RelativeLayout singUp;
    public FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView btnSignin, btnforgotPassword;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    EditText etEmail, etPassword;
    TextView emailError, passwordError;
    FirebaseUser mUser;
    FirebaseAuth.AuthStateListener mAuthListner;
    ProgressDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getView().getApplicationWindowToken(), 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
//                if (mFirebaseUser != null) {
//                    moveToHomeActivity(mFirebaseUser);
//                } else {
//                    Toast.makeText(getActivity(), "login", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        };

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

//    private void moveToHomeActivity(FirebaseUser mFirebaseUser) {
//        firebaseDatabase.getReference().child(mFirebaseUser.getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        UserDetail userDetail = snapshot.getValue(UserDetail.class);
//                        String name = userDetail.getUsername() + " " + userDetail.getPhoneNumber();
//                        Intent i = new Intent(getActivity(), HomeFragment.class);
//                        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        onClickListener();

        checkEdittextWatcher(etEmail, emailError);
        checkEdittextWatcher(etPassword, passwordError);
    }

    private void onClickListener() {
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etEmail.getText().toString())) {
                    if (!TextUtils.isEmpty(etPassword.getText().toString())) {
                        hitLogin(etEmail.getText().toString(), etPassword.getText().toString());
                    } else {
                        SetErrorMessage(passwordError, etPassword);
                    }


                } else {
                    SetErrorMessage(emailError, etEmail);
                }

//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
            }
        });


        btnforgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgotFragment);
            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_singupFragment);
            }
        });
    }


    private void init(View view) {
        btnSignin = view.findViewById(R.id.btnSignin);
        btnforgotPassword = view.findViewById(R.id.tvForgotPassword);
        singUp = view.findViewById(R.id.RlSingup);
// for authentication using FirebaseAuth.
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth.getInstance().signOut();
        etEmail = view.findViewById(R.id.ed_name);
        etPassword = view.findViewById(R.id.ed_password);
        emailError = view.findViewById(R.id.tvErrorEmail);
        passwordError = view.findViewById(R.id.tvErrorPasswordLogin);


        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    void SetErrorMessage(TextView textView, EditText editText) {
        textView.setVisibility(View.VISIBLE);
        editText.setBackground(
                ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.error_drawable));

    }

    public void checkEdittextWatcher(final EditText editText, final TextView textView) {
        final boolean[] isValidate = {false};
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.setVisibility(View.GONE);

                editText.setBackgroundResource(R.drawable.rectangle);

            }
        });

    }


    public void hitLogin(String email, String password) {


//        dialog.setMessage("Loging in please wait...");
//        dialog.setIndeterminate(true);
//        dialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(), "Login Successfully !!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), SettingProfileFragment.class));
                        getActivity().finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Login Failed !!", Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }
        });


    }

    //This function helps in verifying whether the email is verified or not.


}



