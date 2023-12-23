package com.smartedge.saee.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Basic.PrefKeys;
import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Networking.Models.Auth.LoginResults;
import com.smartedge.saee.Networking.Models.LoginRequest;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity implements CallBack {
    CountryCodePicker ext;
    TextView forgotPassword;
    private boolean isPasswordVisible = false;
    ImageView langIcon;
    Button login;
    EditText password;
    EditText phoneNumber;
    ImageView visibility;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initials();
        clicks();
    }

    private void clicks() {
        this.login.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.LoginActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.loginUser(new LoginRequest(LoginActivity.this.ext.getSelectedCountryCodeWithPlus() + LoginActivity.this.phoneNumber.getText().toString(), LoginActivity.this.password.getText().toString()));
            }
        });
        this.forgotPassword.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.LoginActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, PhoneActivity.class));
            }
        });
        this.visibility.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.LoginActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LoginActivity.this.isPasswordVisible) {
                    LoginActivity.this.password.setInputType(129);
                    LoginActivity.this.visibility.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    LoginActivity.this.password.setInputType(1);
                    LoginActivity.this.visibility.setImageResource(R.drawable.ic_visibility);
                }
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.isPasswordVisible = true ^ loginActivity.isPasswordVisible;
                LoginActivity.this.password.setSelection(LoginActivity.this.password.getText().length());
            }
        });
        this.langIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.LoginActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PreferencesUtils.getLanguage().equalsIgnoreCase("ar")) {
                    PreferencesUtils.setLanguage(PrefKeys.language);
                } else {
                    PreferencesUtils.setLanguage("ar");
                }
                Intent i = new Intent(LoginActivity.this, SplashActivity.class);
                LoginActivity.this.startActivity(i);
                LoginActivity.this.finish();
            }
        });
    }

    private void initials() {
        this.ext = (CountryCodePicker) findViewById(R.id.ccp_login);
        this.phoneNumber = (EditText) findViewById(R.id.phone_number);
        this.password = (EditText) findViewById(R.id.password);
        this.login = (Button) findViewById(R.id.login);
        this.forgotPassword = (TextView) findViewById(R.id.forgot_password);
        this.visibility = (ImageView) findViewById(R.id.visible);
        this.langIcon = (ImageView) findViewById(R.id.language_icon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginUser(LoginRequest loginRequest) {
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().post(this, AppConstants.Login_URL, 1, LoginResults.class, loginRequest);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        LoginResults loginResults = (LoginResults) result;
        if (loginResults.getStatus().booleanValue()) {
            PreferencesUtils.setUserToken(loginResults.getData().getToken());
            PreferencesUtils.setUserID(loginResults.getData().getId());
            PreferencesUtils.setDriverID(loginResults.getData().getUserId());
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        Toast.makeText(this, loginResults.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            LoginResults loginResults = (LoginResults) result;
            Toast.makeText(this, loginResults.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
