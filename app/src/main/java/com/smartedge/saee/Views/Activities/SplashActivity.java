package com.smartedge.saee.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.R;

import java.util.Locale;

/* loaded from: classes5.dex */
public class SplashActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        switchLocal(this);
        new Handler().postDelayed(new Runnable() { // from class: com.smartedge.saee.Views.Activities.SplashActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (!PreferencesUtils.getUserToken().equalsIgnoreCase("-1")) {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                    return;
                }
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                SplashActivity.this.finish();
            }
        }, 3000L);
    }

    private void switchLocal(Context context) {
        if (PreferencesUtils.getLanguage().equalsIgnoreCase("")) {
            return;
        }
        Resources resources = context.getResources();
        Locale locale = new Locale(PreferencesUtils.getLanguage());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
