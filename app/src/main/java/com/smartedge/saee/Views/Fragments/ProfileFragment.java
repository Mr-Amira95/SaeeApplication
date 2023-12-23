package com.smartedge.saee.Views.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Networking.Models.Profile.ProfileResults;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Activities.LoginActivity;
import com.smartedge.saee.Views.Activities.MainActivity;

import java.util.HashMap;

/* loaded from: classes3.dex */
public class ProfileFragment extends Fragment implements CallBack {
    ImageView attachmentImage;
    TextView carLicense;
    Button changePassword;
    TextView driverLicense;
    TextView id;
    Button logout;
    TextView name;
    TextView nationalId;
    TextView phoneNumber;
    ProfileResults profileResults;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initials(view);
        clicks();
        return view;
    }

    private void clicks() {
        this.changePassword.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ProfileFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileFragment.this.setFragment(new ChangePasswordFragment());
            }
        });
        this.id.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ProfileFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Glide.with(ProfileFragment.this.getContext()).load(AppConstants.MEDIA_URL + ProfileFragment.this.profileResults.getData().getId()).into(ProfileFragment.this.attachmentImage);
                ProfileFragment.this.id.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.dark_primary)));
                ProfileFragment.this.carLicense.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.grey)));
                ProfileFragment.this.driverLicense.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.grey)));
            }
        });
        this.carLicense.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ProfileFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Glide.with(ProfileFragment.this.getContext()).load(AppConstants.MEDIA_URL + ProfileFragment.this.profileResults.getData().getCarLicense()).into(ProfileFragment.this.attachmentImage);
                ProfileFragment.this.carLicense.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.dark_primary)));
                ProfileFragment.this.id.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.grey)));
                ProfileFragment.this.driverLicense.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.grey)));
            }
        });
        this.driverLicense.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ProfileFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Glide.with(ProfileFragment.this.getContext()).load(AppConstants.MEDIA_URL + ProfileFragment.this.profileResults.getData().getDriveLicense()).into(ProfileFragment.this.attachmentImage);
                ProfileFragment.this.driverLicense.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.dark_primary)));
                ProfileFragment.this.carLicense.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.grey)));
                ProfileFragment.this.id.setBackgroundTintList(ColorStateList.valueOf(ProfileFragment.this.getResources().getColor(R.color.grey)));
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ProfileFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PreferencesUtils.clearDefaults(ProfileFragment.this.getContext());
                ProfileFragment.this.startActivity(new Intent(ProfileFragment.this.getContext(), LoginActivity.class));
                ProfileFragment.this.getActivity().finish();
            }
        });
    }

    private void initials(View view) {
        this.logout = (Button) view.findViewById(R.id.logout);
        this.changePassword = (Button) view.findViewById(R.id.change_password);
        this.id = (TextView) view.findViewById(R.id.id);
        this.driverLicense = (TextView) view.findViewById(R.id.driver_license);
        this.carLicense = (TextView) view.findViewById(R.id.car_license);
        this.attachmentImage = (ImageView) view.findViewById(R.id.attachment_image);
        this.name = (TextView) view.findViewById(R.id.name_value);
        this.phoneNumber = (TextView) view.findViewById(R.id.phone_value);
        this.nationalId = (TextView) view.findViewById(R.id.national_value);
        getProfile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = ((MainActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setProfile() {
        this.name.setText(this.profileResults.getData().getName());
        this.phoneNumber.setText(this.profileResults.getData().getNumber());
        this.nationalId.setText(this.profileResults.getData().getNationalId());
        Glide.with(this).load(AppConstants.MEDIA_URL + this.profileResults.getData().getId()).into(this.attachmentImage);
    }

    private void getProfile() {
        HashMap<String, Object> params = new HashMap<>();
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().get(getContext(), "profile", 8, ProfileResults.class, params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        ProfileResults profileResults = (ProfileResults) result;
        this.profileResults = profileResults;
        if (profileResults.getStatus().booleanValue()) {
            setProfile();
        } else {
            Toast.makeText(getContext(), this.profileResults.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            this.profileResults = (ProfileResults) result;
            Toast.makeText(getContext(), this.profileResults.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
