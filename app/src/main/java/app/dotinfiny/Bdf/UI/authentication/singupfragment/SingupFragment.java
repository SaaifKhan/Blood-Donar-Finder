package app.dotinfiny.Bdf.UI.authentication.singupfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import app.dotinfiny.Bdf.Constants;
import app.dotinfiny.Bdf.MainActivity;
import app.dotinfiny.Bdf.R;

import static android.content.Context.MODE_PRIVATE;


public class SingupFragment<postListener> extends Fragment {

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    TextView btnSingup;
    ImageView imgClose;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView ErrorUsername, errorPhone, errorPassword, errorEmail;
    private FirebaseAuth mAuth;
    // private static final int MODE_PRIVATE = 1 ;
    EditText EtUserName, EtEmail, EtPassword, EtPhone;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    Boolean isvalidate = true;
    private UserDetail UserDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        UserDetail = new UserDetail();

        //  mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        onCreate();
        checkEdittextWatcher(EtUserName, ErrorUsername);
        checkEdittextWatcher(EtEmail, errorEmail);
        checkEdittextWatcher(EtPassword, errorPassword);
        checkEdittextWatcher(EtPhone, errorPhone);

    }

    private void init(View view) {
        EtUserName = view.findViewById(R.id.ed_name_singup);
        EtEmail = view.findViewById(R.id.edEmail_singup);
        EtPhone = view.findViewById(R.id.etNumber_singup);
        EtPassword = view.findViewById(R.id.etPassword_singup);
        btnSingup = view.findViewById(R.id.btnSingUp);
        imgClose = view.findViewById(R.id.imgClose_signup);
        errorEmail = view.findViewById(R.id.tvErrorEmail);
        errorPassword = view.findViewById(R.id.tvErrorPassword);
        errorPhone = view.findViewById(R.id.tvErrorPhone);
        ErrorUsername = view.findViewById(R.id.TvErrorUsername);

        mAuth = FirebaseAuth.getInstance();


        sharedPreferences = getActivity().getSharedPreferences("Singup", 0);
// get editor to edit in file
        editor = sharedPreferences.edit();


    }


    private void onCreate() {

        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(EtUserName.getText().toString())) {
                    if (!TextUtils.isEmpty(EtPhone.getText().toString())) {
                        if (!TextUtils.isEmpty(EtPassword.getText().toString())) {
                            if (!TextUtils.isEmpty(EtEmail.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(EtEmail.getText().toString()).matches()) {


                                hitSignUp(EtEmail.getText().toString(), EtPassword.getText().toString());


                            } else {
                                SetErrorMessage(errorEmail, EtEmail);

                            }


                        } else {
                            SetErrorMessage(errorPassword, EtPassword);

                        }


                    } else {
                        SetErrorMessage(errorPhone, EtPhone);

                    }


                } else {
                    SetErrorMessage(ErrorUsername, EtUserName);
                }


//                if (TextUtils.isEmpty(EtUserName.getText())) {
//                    EtUserName.setError("Type UserName");
//                }
//                if (TextUtils.isEmpty(EtEmail.getText())) {
//                    EtEmail.setError("Type Email");
//                }
//                if (TextUtils.isEmpty(EtPhone.getText())) {
//                    EtPhone.setError("Phone Missing");
//                }
//                if (TextUtils.isEmpty(EtPassword.getText())) {
//                    EtPassword.setError("Password Missing");
//                    EtPassword.requestFocus();
//                }
//                if (TextUtils.isEmpty(EtUserName.getText())) {
//                    isvalidate = false;
//                } else if (TextUtils.isEmpty(EtEmail.getText())) {
//                    isvalidate = false;
//                } else if (TextUtils.isEmpty(EtPhone.getText())) {
//                    isvalidate = false;
//                } else if (TextUtils.isEmpty(EtPassword.getText())) {
//                    isvalidate = false;
//                } else if (TextUtils.isEmpty(EtEmail.getText().toString()) && (TextUtils.isEmpty(EtPassword.getText().toString()))) {
//                    mFirebaseAuth.createUserWithEmailAndPassword(EtEmail.getText().toString(), EtPassword.getText().toString())
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (!task.isSuccessful()) {
//                                        Toast.makeText(getActivity(), "Signup unsuccessFull, try again", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                    else {
//                                        UserDetail userDetail = new UserDetail(EtUserName.getText().toString(), EtPassword.getText().toString(), EtPhone.getText().toString(), EtPassword.getText().toString());
//                                        String uid = task.getResult().getUser().getUid();
//                                        firebaseDatabase.getReference(uid).setValue(userDetail)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//
//                                                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                                        startActivity(intent);
//
//                                                    }
//
//                                                });
//
//
//                                    }
//                                }
//                            });
//
//                }
            }


        });

//        editor.putString("Username",EtUserName.getText().toString());
//        editor.putString("Email",EtEmail.getText().toString());
        editor.putBoolean("IsNewUser", false);
        editor.commit();


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();

            }
        });


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


    public void hitSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String uid = task.getResult().getUser().getUid();
                        databaseReference.child(uid).setValue(toMap(uid)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {


                                SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE);
                                Toast.makeText(getActivity(), "Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                preferences.edit().putBoolean(Constants.ISNEWUSER, true).apply();

                            }
                        });
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Registration Error !!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public Map<String, String> toMap(String id) {
        HashMap<String, String> result = new HashMap<>();
        result.put("uid", id);
        result.put(Constants.USER_NAME, EtUserName.getText().toString());
        result.put(Constants.USER_EMAIL, EtEmail.getText().toString());
        result.put(Constants.USER_PHONE, EtPhone.getText().toString());


        return result;
    }

//
//    SharedPreferences pref = getActivity().getSharedPreferences("YOUR_PREF_NAME", Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = pref.edit();
//

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */


}

