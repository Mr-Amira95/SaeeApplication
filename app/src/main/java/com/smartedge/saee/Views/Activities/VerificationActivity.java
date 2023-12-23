package com.smartedge.saee.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Networking.Models.Auth.ForgotPassword;
import com.smartedge.saee.Networking.Models.Auth.VerificationResults;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class VerificationActivity extends AppCompatActivity implements CallBack {
    String phoneNumber;
    TextView subTitle;
    TextView timer;
    EditText verificationCode;
    Button verify;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        initials();
        clicks();
    }

    private void clicks() {
        this.timer.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.VerificationActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerificationActivity.this.verifyPhone();
            }
        });
        this.verify.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.VerificationActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!VerificationActivity.this.verificationCode.getText().toString().isEmpty() && VerificationActivity.this.verificationCode.getText().toString().length() == 6) {
                    VerificationActivity.this.verifyCode();
                    return;
                }
                VerificationActivity verificationActivity = VerificationActivity.this;
                Toast.makeText(verificationActivity, verificationActivity.getString(R.string.please_check_the_verification_code), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initials() {
        this.subTitle = (TextView) findViewById(R.id.sub_title);
        this.verificationCode = (EditText) findViewById(R.id.code);
        this.verify = (Button) findViewById(R.id.verify);
        this.timer = (TextView) findViewById(R.id.timer);
        this.phoneNumber = getIntent().getStringExtra("phoneNumber");
        setTimer();
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.smartedge.saee.Views.Activities.VerificationActivity$3] */
    private void setTimer() {
        new CountDownTimer(30000L, 1000L) { // from class: com.smartedge.saee.Views.Activities.VerificationActivity.3
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                VerificationActivity.this.timer.setText(VerificationActivity.this.getString(R.string.resend_code_in_n) + (millisUntilFinished / 1000));
                VerificationActivity.this.timer.setClickable(false);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                VerificationActivity.this.timer.setText(VerificationActivity.this.getString(R.string.resend));
                VerificationActivity.this.timer.setClickable(true);
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyCode() {
        HashMap<String, String> params = new HashMap<>();
        params.put("code", this.verificationCode.getText().toString());
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().postReset(this, AppConstants.Verify_Code_URL, 4, VerificationResults.class, params);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyPhone() {
        HashMap<String, String> params = new HashMap<>();
        params.put("number", this.phoneNumber);
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().postLogin(this, AppConstants.Forgot_Password_URL, 3, ForgotPassword.class, params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        switch (tag) {
            case 3:
                ForgotPassword forgotPassword = (ForgotPassword) result;
                if (!forgotPassword.getStatus().booleanValue()) {
                    Toast.makeText(this, forgotPassword.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                setTimer();
                PreferencesUtils.setResetToken(forgotPassword.getData().getToken());
                Toast.makeText(this, forgotPassword.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            case 4:
                VerificationResults verificationCode = (VerificationResults) result;
                if (verificationCode.getStatus().booleanValue()) {
                    Toast.makeText(this, verificationCode.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, ResetPasswordActivity.class));
                    finish();
                    return;
                }
                Toast.makeText(this, verificationCode.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            default:
                return;
        }
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            switch (tag) {
                case 3:
                    ForgotPassword forgotPassword = (ForgotPassword) result;
                    Toast.makeText(this, forgotPassword.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    VerificationResults verificationCode = (VerificationResults) result;
                    Toast.makeText(this, verificationCode.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return;
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
