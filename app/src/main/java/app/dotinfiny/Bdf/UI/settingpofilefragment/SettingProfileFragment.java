package app.dotinfiny.Bdf.UI.settingpofilefragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.dotinfiny.Bdf.Constants;
import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.settingpofilefragment.adapters.BloodGroupAdapterSettingProfile;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class SettingProfileFragment extends Fragment {
    public DatabaseReference myRef;
    public String userID;
    public String selectedBloodGroup = null;
    RecyclerView recyclerView;
    TextView tvErrorUname, tvErrorPhone;
    EditText Username, Phone;
    TextView Email, SubmitBtn;
    BloodGroupAdapterSettingProfile bloodGroupAdapterSettingProfile;
    private FirebaseAuth mAuth;
    public static final String BLOOD_DONAR_FINDER = "blooddonarfinders";
    ImageView gallerybtn;
    CircleImageView circleImageView;
    StorageReference mStorageRef;
    Image image;
    ProgressBar progressBar;
    ImageView backbtn;

    //StorageReference storageRef = storage.getReference();


    //  int RequestCode = 100;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference("users");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_profile, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);
        onClick();

        myRef.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onClick() {
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(Username.getText().toString())) {
                    if (!TextUtils.isEmpty(Phone.getText().toString())) {

                        HitSubmit(Username.getText().toString(), Phone.getText().toString(), Email.getText().toString(), selectedBloodGroup);
                        //   Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();


                    } else {
                        SetErrorMessage(tvErrorPhone, Phone);
                    }


                } else {
                    SetErrorMessage(tvErrorUname, Username);
                }
            }


        });


        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.create(SettingProfileFragment.this)
                        .start();


            }

        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();

            }
        });


    }

    private void uploadImage() {
        if (image != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = mStorageRef.child("images/" + FirebaseAuth.getInstance().getUid() + "/");
            ref.putFile(image.getUri())
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // getting image uri and converting into string
                                Uri downloadUrl = uri;
                                String fileUrl = downloadUrl.toString();
                                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("image").setValue(fileUrl);
                            }
                        });
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }

    }

    private void HitSubmit(String name, String PhoneNumber, String Email, String bloodGroup) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Updating...");
        progressDialog.show();
        Map<String, Object> updatedValue = new HashMap<>();
        updatedValue.put(Constants.ID, userID);
        updatedValue.put(Constants.USER_NAME, name);
        updatedValue.put(Constants.USER_PHONE, PhoneNumber);
        updatedValue.put(Constants.USER_EMAIL, Email);
        updatedValue.put(Constants.BLOOD_GROUP, bloodGroup);

        myRef.child("users").child(userID).updateChildren(updatedValue).addOnSuccessListener(aVoid -> {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE);
            preferences.edit().putBoolean(Constants.ISNEWUSER, false).apply();
        });
    }


    private void init(View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewBloodGroupprofilesetting);
        Username = view.findViewById(R.id.ed_name_profilesetting);
        Phone = view.findViewById(R.id.etNumber_profilesetting);
        Email = view.findViewById(R.id.edEmail_profilesetting);
        SubmitBtn = view.findViewById(R.id.Submit_profile_settings);
        mAuth = FirebaseAuth.getInstance();
        tvErrorPhone = view.findViewById(R.id.tvErrorPhone_profilesetting);
        tvErrorUname = view.findViewById(R.id.TvErrorUsername_profilesetting);
        gallerybtn = view.findViewById(R.id.ImageSelection);
        circleImageView = view.findViewById(R.id.SettingProfileFragmentPicture);
        progressBar = view.findViewById(R.id.progress_bar);
        backbtn = view.findViewById(R.id.back);
        bloodGroupAdapterSettingProfile = new BloodGroupAdapterSettingProfile(getListOfBloodGroup(), new BloodGroupAdapterSettingProfile.CLickListener() {
            @Override
            public void onClick(int position) {
                selectedBloodGroup = getListOfBloodGroup().get(position);
                bloodGroupAdapterSettingProfile.setIsSelectedBlood(selectedBloodGroup);
                bloodGroupAdapterSettingProfile.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(bloodGroupAdapterSettingProfile);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void showData(DataSnapshot dataSnapshot) {


        try {
//            final ProgressDialog progressDialog = new ProgressDialog(getContext());
//            progressDialog.setTitle("Data getting..");
//            progressDialog.show();
            String Uid = dataSnapshot.child(Constants.ID).getValue(String.class);
            String userEmailfromDB = dataSnapshot.child(Constants.USER_EMAIL).getValue(String.class);
            String usernameFromDB = dataSnapshot.child(Constants.USER_NAME).getValue(String.class);
            String userPhoneFromDB = dataSnapshot.child(Constants.USER_PHONE).getValue(String.class);
            String bloodgroup = dataSnapshot.child(Constants.BLOOD_GROUP).getValue(String.class);

            if (dataSnapshot.hasChild(Constants.IMAGE)) {
                String imageUrl = dataSnapshot.child(Constants.IMAGE).getValue(String.class);
                Glide.with(getActivity())
                        .load(imageUrl)
                        //.placeholder()
                        .into(circleImageView);
            }


            selectedBloodGroup = bloodgroup;
            bloodGroupAdapterSettingProfile.setIsSelectedBlood(selectedBloodGroup);
            bloodGroupAdapterSettingProfile.notifyDataSetChanged();
            Username.setText(usernameFromDB);
            Phone.setText(userPhoneFromDB);
            Email.setText(userEmailfromDB);


        } catch (Exception e) {

        }
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

    private ArrayList<String> getListOfBloodGroup() {
        ArrayList<String> bloodGroups = new ArrayList<String>();
        bloodGroups.add("A+");
        bloodGroups.add("A-");
        bloodGroups.add("B+");
        bloodGroups.add("O-");
        bloodGroups.add("O+");
        bloodGroups.add("AB+");
        bloodGroups.add("AB-");
        return bloodGroups;
    }

//    @Override
//    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
//        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
//            // Get a list of picked images
//            // or get a single image only
//            Image image = ImagePicker.getFirstImageOrNull(data);
//            if (image != null) {
//                Glide.with(getActivity())
//                        .load(image.getUri())
//                        .override(300, 200)
//                        .into(circleImageView);
//            } else {
//
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            // or get a single image only
            image = ImagePicker.getFirstImageOrNull(data);
            if (image != null) {

                Glide.with(getActivity())
                        .load(image.getUri())
                        .override(300, 200)
                        .into(circleImageView);
                uploadImage();
            } else {

            }
        }
    }


//    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//        DatabaseReference ref = FirebaseDatabase.getInstance()
//                .getReference(BLOOD_DONAR_FINDER)
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("imageUrl");
//        ref.setValue(imageEncoded);
}


