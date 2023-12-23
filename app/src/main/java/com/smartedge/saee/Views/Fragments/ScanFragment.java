package com.smartedge.saee.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.OrderDetails.OrderDetails;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Activities.MainActivity;
import com.google.zxing.Result;

import java.util.HashMap;

/* loaded from: classes3.dex */
public class ScanFragment extends Fragment implements CallBack {
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    Button check;
    private CodeScanner mCodeScanner;
    EditText refCode;
    private CodeScannerView scannerView;

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mCodeScanner.startPreview();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        initials(view);
        clicks();
        if (ContextCompat.checkSelfPermission(requireContext(), "android.permission.CAMERA") != 0) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.CAMERA"}, 100);
        }
        return view;
    }

    private void clicks() {
        this.check.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ScanFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ScanFragment.this.refCode.getText().toString().length() == 10) {
                    ScanFragment scanFragment = ScanFragment.this;
                    scanFragment.getOrderByRef(scanFragment.refCode.getText().toString());
                    return;
                }
                Toast.makeText(ScanFragment.this.getContext(), ScanFragment.this.getString(R.string.please_check_the_reference_code), Toast.LENGTH_SHORT).show();
            }
        });
        this.scannerView.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ScanFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScanFragment.this.mCodeScanner.startPreview();
            }
        });
    }

    private void initials(View view) {
        this.refCode = (EditText) view.findViewById(R.id.code);
        this.check = (Button) view.findViewById(R.id.check);
        this.scannerView = (CodeScannerView) view.findViewById(R.id.scanner_view);
        CodeScanner codeScanner = new CodeScanner(getActivity(), this.scannerView);
        this.mCodeScanner = codeScanner;
        codeScanner.setDecodeCallback(new DecodeCallback() { // from class: com.smartedge.saee.Views.Fragments.ScanFragment.3
            @Override // com.budiyev.android.codescanner.DecodeCallback
            public void onDecoded(final Result result) {
                ScanFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.smartedge.saee.Views.Fragments.ScanFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ScanFragment.this.getOrderByRef(result.getText().toString());
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOrderByRef(String ref) {
        HashMap<String, Object> params = new HashMap<>();
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().get(getContext(), AppConstants.Order_By_Ref_URL + ref, 13, OrderDetails.class, params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        OrderDetails orderDetails = (OrderDetails) result;
        if (orderDetails.getStatus().booleanValue()) {
            setFragment(new OrderDetailsFragment(String.valueOf(orderDetails.getData().getItem().getId())));
        } else {
            Toast.makeText(getContext(), orderDetails.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        this.mCodeScanner.startPreview();
        try {
            OrderDetails orderDetails = (OrderDetails) result;
            Toast.makeText(getContext(), orderDetails.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = ((MainActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
