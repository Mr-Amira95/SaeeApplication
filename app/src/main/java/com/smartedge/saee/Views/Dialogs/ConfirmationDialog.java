package com.smartedge.saee.Views.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.smartedge.saee.R;
import com.smartedge.saee.Views.Activities.MainActivity;

/* loaded from: classes10.dex */
public class ConfirmationDialog extends Dialog {
    Button cancel;
    Button confirm;
    Context context;

    public ConfirmationDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirmation);
        getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        initials();
        clicks();
    }

    private void clicks() {
        this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.ConfirmationDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((MainActivity) ConfirmationDialog.this.context).finish();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.ConfirmationDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ConfirmationDialog.this.dismiss();
            }
        });
    }

    private void initials() {
        this.confirm = (Button) findViewById(R.id.confirm_button);
        this.cancel = (Button) findViewById(R.id.cancel_button);
    }
}
