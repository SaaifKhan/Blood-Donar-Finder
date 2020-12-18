package app.dotinfiny.Bdf.UI.addrequestfragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import app.dotinfiny.Bdf.R;
import app.dotinfiny.Bdf.UI.addrequestfragment.adapter.BloodGroupAdapter;


public class RequestFragment extends Fragment {
    RecyclerView recyclerView;
    TextView RequiredBloodGroupOfReceiver;
    public BloodGroupAdapter bloodGroupAdapter;
    ImageView imgBack;
    EditText TvHospitals, Tvlocation;
    EditText TvDesc;
    TextView TvName;
    TextView title;
    TextView btnReqBlood;
    TextView TextViewBloodForDonar,BloodGroupOfDonar;
    TextView TvError, TvErrorLocations, TvErrorHospital;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Drawable error = TvError.getBackground(R.drawable.error_drawable,);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bloodGroupAdapter = new BloodGroupAdapter(new BloodGroupAdapter.CLickListener() {
            @Override
            public void onClick(int position) {

            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
        checkEdittextWatcher(TvDesc, TvError);
        checkEdittextWatcher(TvHospitals, TvErrorHospital);
         checkEdittextWatcher(Tvlocation,TvErrorLocations);
        RequestFragmentArgs args = RequestFragmentArgs.fromBundle(getArguments());
        Toast.makeText(getContext(), "It Donor " + args.getIsDonar(), Toast.LENGTH_SHORT).show();
        if (args.getIsDonar()) {

            title.setText("Request to be Donar");
            TextViewBloodForDonar.setVisibility(View.VISIBLE);
            BloodGroupOfDonar.setVisibility(View.VISIBLE);

        } else {

            title.setText("Request For Blood");
            RequiredBloodGroupOfReceiver.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void clickListener() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bloodGroupAdapter);

//        recyclerViewDonar.setHasFixedSize(true);
  //      recyclerViewDonar.setAdapter(bloodGroupAdapter);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
        btnReqBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(TvDesc.getText().toString())) {
                    if (!TextUtils.isEmpty(TvHospitals.getText().toString())) {
                        if (!TextUtils.isEmpty(Tvlocation.getText().toString())) {
                            //savedata
                        } else {
                            setErrorMessage(TvErrorLocations, Tvlocation);
                        }
                    } else {
                        setErrorMessage(TvErrorLocations, TvHospitals);
                    }
                } else {
                    setErrorMessage(TvError, TvDesc);
                }


            }
        });


    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewBloodGroup);
//        recyclerViewDonar = view.findViewById(R.id.RecyclerViewBloodDonar);
        imgBack = view.findViewById(R.id.imgBackRequest);
        btnReqBlood = view.findViewById(R.id.btnSubmitRequestBlood);
        TvName = view.findViewById(R.id.EtUsernameBloodRequest);
        TvDesc = view.findViewById(R.id.EdDescRequest);
        TvHospitals = view.findViewById(R.id.Hospitals);
        TvName = view.findViewById(R.id.LocationRequestBlood);
        TvError = view.findViewById(R.id.tvErrorDesc);
        TvErrorHospital = view.findViewById(R.id.tvErrorHospital);
        TvErrorLocations = view.findViewById(R.id.tvErrorLocation);
        Tvlocation = view.findViewById(R.id.LocationRequestBlood);
        title = view.findViewById(R.id.title);
        TextViewBloodForDonar = view.findViewById(R.id.TvBloodGroupDonar);
        BloodGroupOfDonar = view.findViewById(R.id.BloodGroupofDonar);
        RequiredBloodGroupOfReceiver = view.findViewById(R.id.TvRequiredBloodGroup);
    }


    void setErrorMessage(TextView textView, EditText editText) {
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


}
