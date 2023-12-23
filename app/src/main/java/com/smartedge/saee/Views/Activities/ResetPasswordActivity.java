package com.smartedge.saee.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.Auth.ResetPassword;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class ResetPasswordActivity extends AppCompatActivity implements CallBack {
    EditText conformPassword;
    EditText password;
    Button reset;
    ImageView visibleConfirm;
    ImageView visibleNew;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initials();
        clicks();
    }

    private void clicks() {
        this.reset.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.ResetPasswordActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!ResetPasswordActivity.this.password.getText().toString().isEmpty() && !ResetPasswordActivity.this.conformPassword.getText().toString().isEmpty()) {
                    if (ResetPasswordActivity.this.password.getText().toString().equals(ResetPasswordActivity.this.conformPassword.getText().toString())) {
                        ResetPasswordActivity resetPasswordActivity = ResetPasswordActivity.this;
                        if (resetPasswordActivity.isPasswordComplex(resetPasswordActivity.password.getText().toString())) {
                            ResetPasswordActivity.this.resetPassword();
                            return;
                        }
                        return;
                    }
                    ResetPasswordActivity resetPasswordActivity2 = ResetPasswordActivity.this;
                    Toast.makeText(resetPasswordActivity2, resetPasswordActivity2.getString(R.string.passwords_must_be_matched), Toast.LENGTH_SHORT).show();
                    return;
                }
                ResetPasswordActivity resetPasswordActivity3 = ResetPasswordActivity.this;
                Toast.makeText(resetPasswordActivity3, resetPasswordActivity3.getString(R.string.all_fields_are_required), Toast.LENGTH_SHORT).show();
            }
        });
        this.visibleNew.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.ResetPasswordActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ResetPasswordActivity.this.isPasswordVisible) {
                    ResetPasswordActivity.this.password.setInputType(129);
                    ResetPasswordActivity.this.visibleNew.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    ResetPasswordActivity.this.password.setInputType(1);
                    ResetPasswordActivity.this.visibleNew.setImageResource(R.drawable.ic_visibility);
                }
                ResetPasswordActivity resetPasswordActivity = ResetPasswordActivity.this;
                resetPasswordActivity.isPasswordVisible = true ^ resetPasswordActivity.isPasswordVisible;
                ResetPasswordActivity.this.password.setSelection(ResetPasswordActivity.this.password.getText().length());
            }
        });
        this.visibleConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.ResetPasswordActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ResetPasswordActivity.this.isConfirmPasswordVisible) {
                    ResetPasswordActivity.this.conformPassword.setInputType(129);
                    ResetPasswordActivity.this.visibleConfirm.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    ResetPasswordActivity.this.conformPassword.setInputType(1);
                    ResetPasswordActivity.this.visibleConfirm.setImageResource(R.drawable.ic_visibility);
                }
                ResetPasswordActivity resetPasswordActivity = ResetPasswordActivity.this;
                resetPasswordActivity.isConfirmPasswordVisible = true ^ resetPasswordActivity.isConfirmPasswordVisible;
                ResetPasswordActivity.this.conformPassword.setSelection(ResetPasswordActivity.this.conformPassword.getText().length());
            }
        });
    }

    private void initials() {
        this.password = (EditText) findViewById(R.id.password);
        this.conformPassword = (EditText) findViewById(R.id.confirm_password);
        this.visibleNew = (ImageView) findViewById(R.id.visible);
        this.visibleConfirm = (ImageView) findViewById(R.id.confirm_visible);
        this.reset = (Button) findViewById(R.id.reset);
    }

    public boolean isPasswordComplex(String password) {
        if (password.length() < 8) {
            Toast.makeText(this, getString(R.string.the_password_length_mush_be_more_than_8_characters), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
            Toast.makeText(this, getString(R.string.the_password_mush_contain_uppercase_and_lowercase_letters), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.matches(".*\\d.*")) {
            Toast.makeText(this, getString(R.string.the_password_mush_contain_one_number_at_least), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPassword() {
        HashMap<String, String> params = new HashMap<>();
        params.put("password", this.password.getText().toString());
        params.put("password_confirmation", this.conformPassword.getText().toString());
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().postReset(this, AppConstants.Reset_Password_URL, 5, ResetPassword.class, params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        ResetPassword resetPassword = (ResetPassword) result;
        if (resetPassword.getStatus().booleanValue()) {
            Toast.makeText(this, resetPassword.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        Toast.makeText(this, resetPassword.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            ResetPassword resetPassword = (ResetPassword) result;
            Toast.makeText(this, resetPassword.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
