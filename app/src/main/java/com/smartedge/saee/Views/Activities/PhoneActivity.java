package com.smartedge.saee.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Networking.Models.Auth.ForgotPassword;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class PhoneActivity extends AppCompatActivity implements CallBack {
    Button check;
    CountryCodePicker ext;
    EditText phoneNumber;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        initials();
        clicks();
    }

    private void clicks() {
        this.check.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.PhoneActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PhoneActivity.this.phoneNumber.getText().toString().isEmpty()) {
                    PhoneActivity.this.verifyPhone();
                    return;
                }
                PhoneActivity phoneActivity = PhoneActivity.this;
                Toast.makeText(phoneActivity, phoneActivity.getString(R.string.all_fields_are_required), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initials() {
        this.ext = (CountryCodePicker) findViewById(R.id.ccp_login);
        this.phoneNumber = (EditText) findViewById(R.id.phone_number);
        this.check = (Button) findViewById(R.id.check);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyPhone() {
        HashMap<String, String> params = new HashMap<>();
        params.put("number", this.ext.getSelectedCountryCodeWithPlus() + this.phoneNumber.getText().toString());
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().postLogin(this, AppConstants.Forgot_Password_URL, 3, ForgotPassword.class, params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        ForgotPassword forgotPassword = (ForgotPassword) result;
        if (forgotPassword.getStatus().booleanValue()) {
            PreferencesUtils.setResetToken(forgotPassword.getData().getToken());
            Toast.makeText(this, forgotPassword.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, VerificationActivity.class).putExtra("phoneNumber", this.ext.getSelectedCountryCodeWithPlus() + this.phoneNumber.getText().toString()));
            return;
        }
        Toast.makeText(this, forgotPassword.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            ForgotPassword forgotPassword = (ForgotPassword) result;
            Toast.makeText(this, forgotPassword.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
