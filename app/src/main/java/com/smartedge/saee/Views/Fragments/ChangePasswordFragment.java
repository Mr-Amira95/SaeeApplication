package com.smartedge.saee.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.General;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;

import java.util.HashMap;

/* loaded from: classes3.dex */
public class ChangePasswordFragment extends Fragment implements CallBack {
    Button changePassword;
    EditText confirmPassword;
    EditText currentPassword;
    EditText newPassword;
    ImageView visibleConfirm;
    ImageView visibleCurrent;
    ImageView visibleNew;
    private boolean isPasswordVisible = false;
    private boolean isNewPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        initials(view);
        clicks();
        return view;
    }

    private void clicks() {
        this.changePassword.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ChangePasswordFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!ChangePasswordFragment.this.newPassword.getText().toString().isEmpty() && !ChangePasswordFragment.this.confirmPassword.getText().toString().isEmpty()) {
                    if (ChangePasswordFragment.this.newPassword.getText().toString().equals(ChangePasswordFragment.this.confirmPassword.getText().toString())) {
                        ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.this;
                        if (changePasswordFragment.isPasswordComplex(changePasswordFragment.newPassword.getText().toString())) {
                            ChangePasswordFragment.this.changePassword();
                            return;
                        }
                        return;
                    }
                    Toast.makeText(ChangePasswordFragment.this.getContext(), ChangePasswordFragment.this.getString(R.string.passwords_must_be_matched), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ChangePasswordFragment.this.getContext(), ChangePasswordFragment.this.getString(R.string.all_fields_are_required), Toast.LENGTH_SHORT).show();
            }
        });
        this.visibleCurrent.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ChangePasswordFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChangePasswordFragment.this.isPasswordVisible) {
                    ChangePasswordFragment.this.currentPassword.setInputType(129);
                    ChangePasswordFragment.this.visibleCurrent.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    ChangePasswordFragment.this.currentPassword.setInputType(1);
                    ChangePasswordFragment.this.visibleCurrent.setImageResource(R.drawable.ic_visibility);
                }
                ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.this;
                changePasswordFragment.isPasswordVisible = true ^ changePasswordFragment.isPasswordVisible;
                ChangePasswordFragment.this.currentPassword.setSelection(ChangePasswordFragment.this.currentPassword.getText().length());
            }
        });
        this.visibleNew.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ChangePasswordFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChangePasswordFragment.this.isNewPasswordVisible) {
                    ChangePasswordFragment.this.newPassword.setInputType(129);
                    ChangePasswordFragment.this.visibleNew.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    ChangePasswordFragment.this.newPassword.setInputType(1);
                    ChangePasswordFragment.this.visibleNew.setImageResource(R.drawable.ic_visibility);
                }
                ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.this;
                changePasswordFragment.isNewPasswordVisible = true ^ changePasswordFragment.isNewPasswordVisible;
                ChangePasswordFragment.this.newPassword.setSelection(ChangePasswordFragment.this.newPassword.getText().length());
            }
        });
        this.visibleConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ChangePasswordFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChangePasswordFragment.this.isConfirmPasswordVisible) {
                    ChangePasswordFragment.this.confirmPassword.setInputType(129);
                    ChangePasswordFragment.this.visibleConfirm.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    ChangePasswordFragment.this.confirmPassword.setInputType(1);
                    ChangePasswordFragment.this.visibleConfirm.setImageResource(R.drawable.ic_visibility);
                }
                ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.this;
                changePasswordFragment.isConfirmPasswordVisible = true ^ changePasswordFragment.isConfirmPasswordVisible;
                ChangePasswordFragment.this.confirmPassword.setSelection(ChangePasswordFragment.this.confirmPassword.getText().length());
            }
        });
    }

    private void initials(View view) {
        this.changePassword = (Button) view.findViewById(R.id.reset_password);
        this.currentPassword = (EditText) view.findViewById(R.id.current_password);
        this.newPassword = (EditText) view.findViewById(R.id.new_password);
        this.confirmPassword = (EditText) view.findViewById(R.id.confirm_new_password);
        this.visibleCurrent = (ImageView) view.findViewById(R.id.visible);
        this.visibleNew = (ImageView) view.findViewById(R.id.new_visible);
        this.visibleConfirm = (ImageView) view.findViewById(R.id.confirm_new_visible);
    }

    public boolean isPasswordComplex(String password) {
        if (password.length() < 8) {
            Toast.makeText(getContext(), getString(R.string.the_password_length_mush_be_more_than_8_characters), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
            Toast.makeText(getContext(), getString(R.string.the_password_mush_contain_uppercase_and_lowercase_letters), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.matches(".*\\d.*")) {
            Toast.makeText(getContext(), getString(R.string.the_password_mush_contain_one_number_at_least), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changePassword() {
        HashMap<String, String> params = new HashMap<>();
        params.put("old_password", this.currentPassword.getText().toString());
        params.put("password", this.newPassword.getText().toString());
        params.put("password_confirmation", this.confirmPassword.getText().toString());
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().post(getContext(), AppConstants.Change_Password_URL, 6, General.class, (Object) params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        General general = (General) result;
        if (general.getStatus().booleanValue()) {
            Toast.makeText(getContext(), general.getMessage(), Toast.LENGTH_SHORT).show();
            getFragmentManager().popBackStack();
            return;
        }
        Toast.makeText(getContext(), general.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            General general = (General) result;
            Toast.makeText(getContext(), general.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
