package com.smartedge.saee.Views.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.smartedge.saee.Networking.Basic.PrefKeys;
import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Views.Activities.MainActivity;
import com.smartedge.saee.Views.Activities.SplashActivity;
import com.smartedge.saee.R;

/* loaded from: classes10.dex */
public class LanguageDialog extends Dialog {
    Button apply;
    RadioButton arabic;
    ImageView closeIcon;
    Context context;
    RadioButton english;

    public LanguageDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_language);
        getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        initials();
        clicks();
    }

    private void clicks() {
        this.closeIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.LanguageDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanguageDialog.this.dismiss();
            }
        });
        this.apply.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.LanguageDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LanguageDialog.this.arabic.isChecked() && !PreferencesUtils.getLanguage().equalsIgnoreCase("ar")) {
                    PreferencesUtils.setLanguage("ar");
                    Intent i = new Intent(LanguageDialog.this.context, SplashActivity.class);
                    LanguageDialog.this.context.startActivity(i);
                    ((MainActivity) LanguageDialog.this.context).finish();
                } else if (LanguageDialog.this.english.isChecked() && !PreferencesUtils.getLanguage().equalsIgnoreCase(PrefKeys.language)) {
                    PreferencesUtils.setLanguage(PrefKeys.language);
                    Intent i2 = new Intent(LanguageDialog.this.context, SplashActivity.class);
                    LanguageDialog.this.context.startActivity(i2);
                    ((MainActivity) LanguageDialog.this.context).finish();
                }
                LanguageDialog.this.dismiss();
            }
        });
    }

    private void initials() {
        this.closeIcon = (ImageView) findViewById(R.id.close);
        this.apply = (Button) findViewById(R.id.apply_button);
        this.arabic = (RadioButton) findViewById(R.id.arabic);
        this.english = (RadioButton) findViewById(R.id.english);
        if (PreferencesUtils.getLanguage().equalsIgnoreCase(PrefKeys.language)) {
            this.english.setChecked(true);
        } else {
            this.arabic.setChecked(true);
        }
    }
}
