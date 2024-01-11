package com.smartedge.saee.Views.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Dialogs.ConfirmationDialog;
import com.smartedge.saee.Views.Dialogs.LanguageDialog;
import com.smartedge.saee.Views.Fragments.ChatFragment;
import com.smartedge.saee.Views.Fragments.OrdersFragment;
import com.smartedge.saee.Views.Fragments.OrdersUpcomingFragment;
import com.smartedge.saee.Views.Fragments.ProfileFragment;
import com.smartedge.saee.Views.Fragments.ScanFragment;
import com.smartedge.saee.Views.Fragments.TransactionsFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

/* loaded from: classes5.dex */
public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    LinearLayout chat;
    FirebaseDatabase database;
    RoundedImageView home;
    ImageView langIcon;
    LinearLayout orders;
    LinearLayout profile;
    DatabaseReference reference;
    ImageView scanIcon;
    LinearLayout transactions;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initials();
        clicks();
        loadDefaultFragment(new OrdersUpcomingFragment());
    }

    private void clicks() {
        this.transactions.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.replaceFragment(new TransactionsFragment());
            }
        });
        this.chat.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.replaceFragment(new ChatFragment());
            }
        });
        this.orders.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.replaceFragment(new OrdersFragment());
            }
        });
        this.profile.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.replaceFragment(new ProfileFragment());
            }
        });
        this.home.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.replaceFragment(new OrdersUpcomingFragment());
            }
        });
        this.scanIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.replaceFragment(new ScanFragment());
            }
        });
        this.langIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanguageDialog languageDialog = new LanguageDialog(MainActivity.this);
                languageDialog.show();
            }
        });
    }

    private void initials() {
        this.transactions = (LinearLayout) findViewById(R.id.transactions);
        this.chat = (LinearLayout) findViewById(R.id.chat);
        this.orders = (LinearLayout) findViewById(R.id.orders);
        this.profile = (LinearLayout) findViewById(R.id.profile);
        this.home = (RoundedImageView) findViewById(R.id.home);
        this.scanIcon = (ImageView) findViewById(R.id.qr_icon);
        this.langIcon = (ImageView) findViewById(R.id.language_icon);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        DatabaseReference child = firebaseDatabase.getReference().child("locations").child(PreferencesUtils.getUserID());
        this.reference = child;
        child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity.8
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    MainActivity.this.reference.child("lat").setValue(Double.valueOf(0.0d));
                    MainActivity.this.reference.child("lng").setValue(Double.valueOf(0.0d));
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void loadDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            ConfirmationDialog confirmationDialog = new ConfirmationDialog(this);
            confirmationDialog.show();
            return;
        }
        getSupportFragmentManager().popBackStack();
    }

    private void startLocationUpdates() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        LocationRequest locationRequest = LocationRequest.create().setPriority(100).setInterval(3000L);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1001);
        } else {
            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() { // from class: com.smartedge.saee.Views.Activities.MainActivity.9
                @Override // com.google.android.gms.location.LocationCallback
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        MainActivity.this.updateFirebaseLocation(latitude, longitude);
                    }
                }
            }, (Looper) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFirebaseLocation(double latitude, double longitude) {
        this.reference.child("lat").setValue(Double.valueOf(latitude));
        this.reference.child("lng").setValue(Double.valueOf(longitude));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001 && grantResults.length > 0 && grantResults[0] == 0) {
            if (!isLocationEnabled()) {
                showEnableLocationDialog();
            } else {
                startLocationUpdates();
            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }

    private void showEnableLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.location_services_are_required_enable_them)).setCancelable(false).setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.smartedge.saee.Views.Activities.MainActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.m44x916b17f7(dialogInterface, i);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showEnableLocationDialog$0$com.smartedge.saee-Views-Activities-MainActivity  reason: not valid java name */
    public /* synthetic */ void m44x916b17f7(DialogInterface dialog, int id) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1001);
        } else if (!isLocationEnabled()) {
            showEnableLocationDialog();
        } else {
            startLocationUpdates();
        }
    }
}
