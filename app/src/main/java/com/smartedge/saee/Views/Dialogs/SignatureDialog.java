package com.smartedge.saee.Views.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.Views.Fragments.ChangeStatusFragment;
import com.smartedge.saee.Views.Fragments.OrdersUpcomingFragment;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.smartedge.saee.R;

import java.io.File;

/* loaded from: classes10.dex */
public class SignatureDialog extends Dialog {
    Activity activity;
    Button apply;
    CallBack callBack;
    ImageView clear;
    ImageView closeIcon;
    Context context;
    String flag;
    SignaturePad signatureBtn;

    public SignatureDialog(Context context, CallBack callBack, Activity activity, String flag) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.callBack = callBack;
        this.flag = flag;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_signature);
        getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        initials();
        clicks();
    }

    private void clicks() {
        this.closeIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.SignatureDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SignatureDialog.this.dismiss();
            }
        });
        this.apply.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.SignatureDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (flag.equalsIgnoreCase("upcoming")) {
                    Bitmap photo = SignatureDialog.this.signatureBtn.getSignatureBitmap();
                    Uri tempUri = OrdersUpcomingFragment.getImageUri(SignatureDialog.this.getContext().getApplicationContext(), photo);
                    OrdersUpcomingFragment.filePathSignature = OrdersUpcomingFragment.getRealPathFromURI(tempUri, SignatureDialog.this.activity, "signature");
                    OrdersUpcomingFragment.fileSignature = new File(OrdersUpcomingFragment.filePathSignature);
                    OrdersUpcomingFragment.changeStatus(SignatureDialog.this.context, SignatureDialog.this.callBack);
                } else {
                    Bitmap photo2 = SignatureDialog.this.signatureBtn.getSignatureBitmap();
                    Uri tempUri2 = ChangeStatusFragment.getImageUri(SignatureDialog.this.getContext().getApplicationContext(), photo2);
                    ChangeStatusFragment.filePathSignature = ChangeStatusFragment.getRealPathFromURI(tempUri2, SignatureDialog.this.activity, "signature");
                    ChangeStatusFragment.fileSignature = new File(ChangeStatusFragment.filePathSignature);
                }
                SignatureDialog.this.dismiss();
            }
        });
        this.clear.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.SignatureDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SignatureDialog.this.signatureBtn.clear();
            }
        });
        this.signatureBtn.setOnSignedListener(new SignaturePad.OnSignedListener() { // from class: com.smartedge.saee.Views.Dialogs.SignatureDialog.4
            @Override // com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener
            public void onStartSigning() {
            }

            @Override // com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener
            public void onSigned() {
            }

            @Override // com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener
            public void onClear() {
            }
        });
    }

    private void initials() {
        this.closeIcon = (ImageView) findViewById(R.id.close);
        this.apply = (Button) findViewById(R.id.apply_button);
        this.signatureBtn = (SignaturePad) findViewById(R.id.signature_pad);
        this.clear = (ImageView) findViewById(R.id.clear);
    }
}
