package com.smartedge.saee.Views.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.Views.Fragments.OrdersFragment;
import com.smartedge.saee.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes10.dex */
public class FilterDialog extends Dialog {
    TextView all;
    Button apply;
    CheckBox atCompany;
    CallBack callBack;
    ImageView closeIcon;
    CheckBox confirmed;
    Context context;
    CheckBox delivered;
    TextView dropOff;
    Button endDate;
    TextView pickUp;
    CheckBox received;
    CheckBox rejected;
    private Calendar selectedDate;
    Button startDate;
    String type;

    public FilterDialog(Context context, CallBack callBack) {
        super(context);
        this.type = "";
        this.context = context;
        this.callBack = callBack;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_filter);
        getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        initials();
        clicks();
    }

    private void clicks() {
        this.all.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FilterDialog.this.type = "";
                FilterDialog.this.all.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.dark_primary)));
                FilterDialog.this.dropOff.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.pickUp.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.all.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.dropOff.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.grey)));
                FilterDialog.this.pickUp.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.grey)));
            }
        });
        this.dropOff.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FilterDialog.this.type = "drop_off";
                FilterDialog.this.dropOff.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.dark_primary)));
                FilterDialog.this.all.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.pickUp.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.dropOff.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.all.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.grey)));
                FilterDialog.this.pickUp.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.grey)));
            }
        });
        this.pickUp.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FilterDialog.this.type = "pick_up";
                FilterDialog.this.pickUp.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.dark_primary)));
                FilterDialog.this.all.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.dropOff.setBackgroundTintList(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.pickUp.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.white)));
                FilterDialog.this.all.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.grey)));
                FilterDialog.this.dropOff.setTextColor(ColorStateList.valueOf(FilterDialog.this.context.getResources().getColor(R.color.grey)));
            }
        });
        this.startDate.setOnClickListener(new AnonymousClass4());
        this.endDate.setOnClickListener(new AnonymousClass5());
        this.closeIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FilterDialog.this.dismiss();
            }
        });
        this.apply.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!FilterDialog.this.startDate.getText().toString().equalsIgnoreCase("Start Date")) {
                    OrdersFragment.startDate = FilterDialog.this.startDate.getText().toString();
                } else {
                    OrdersFragment.startDate = "";
                }
                if (!FilterDialog.this.endDate.getText().toString().equalsIgnoreCase("End Date")) {
                    OrdersFragment.endDate = FilterDialog.this.endDate.getText().toString();
                } else {
                    OrdersFragment.endDate = "";
                }
                OrdersFragment.confirmed = Boolean.valueOf(FilterDialog.this.confirmed.isChecked());
                OrdersFragment.at_company = Boolean.valueOf(FilterDialog.this.atCompany.isChecked());
                OrdersFragment.received = Boolean.valueOf(FilterDialog.this.received.isChecked());
                OrdersFragment.delivered = Boolean.valueOf(FilterDialog.this.delivered.isChecked());
                OrdersFragment.rejected = Boolean.valueOf(FilterDialog.this.rejected.isChecked());
                OrdersFragment.type = FilterDialog.this.type;
                OrdersFragment.getOrders(FilterDialog.this.callBack, FilterDialog.this.context);
                FilterDialog.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.smartedge.saee.Views.Dialogs.FilterDialog$4  reason: invalid class name */
    /* loaded from: classes10.dex */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(FilterDialog.this.context, new DatePickerDialog.OnDateSetListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog$4$$ExternalSyntheticLambda0
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    AnonymousClass4.this.m45lambda$onClick$0$comexamplesaeeViewsDialogsFilterDialog$4(datePicker, i, i2, i3);
                }
            }, FilterDialog.this.selectedDate.get(1), FilterDialog.this.selectedDate.get(2), FilterDialog.this.selectedDate.get(5));
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com.smartedge.saee-Views-Dialogs-FilterDialog$4  reason: not valid java name */
        public /* synthetic */ void m45lambda$onClick$0$comexamplesaeeViewsDialogsFilterDialog$4(DatePicker v, int year, int month, int dayOfMonth) {
            FilterDialog.this.selectedDate.set(year, month, dayOfMonth);
            Calendar today = Calendar.getInstance();
            if (FilterDialog.this.selectedDate.after(today)) {
                FilterDialog.this.selectedDate = today;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            FilterDialog.this.startDate.setText(sdf.format(FilterDialog.this.selectedDate.getTime()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.smartedge.saee.Views.Dialogs.FilterDialog$5  reason: invalid class name */
    /* loaded from: classes10.dex */
    public class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(FilterDialog.this.context, new DatePickerDialog.OnDateSetListener() { // from class: com.smartedge.saee.Views.Dialogs.FilterDialog$5$$ExternalSyntheticLambda0
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    AnonymousClass5.this.m46lambda$onClick$0$comexamplesaeeViewsDialogsFilterDialog$5(datePicker, i, i2, i3);
                }
            }, FilterDialog.this.selectedDate.get(1), FilterDialog.this.selectedDate.get(2), FilterDialog.this.selectedDate.get(5));
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com.smartedge.saee-Views-Dialogs-FilterDialog$5  reason: not valid java name */
        public /* synthetic */ void m46lambda$onClick$0$comexamplesaeeViewsDialogsFilterDialog$5(DatePicker v, int year, int month, int dayOfMonth) {
            FilterDialog.this.selectedDate.set(year, month, dayOfMonth);
            Calendar today = Calendar.getInstance();
            if (FilterDialog.this.selectedDate.after(today)) {
                FilterDialog.this.selectedDate = today;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            FilterDialog.this.endDate.setText(sdf.format(FilterDialog.this.selectedDate.getTime()));
        }
    }

    private void initials() {
        this.selectedDate = Calendar.getInstance();
        this.dropOff = (TextView) findViewById(R.id.drop_off);
        this.pickUp = (TextView) findViewById(R.id.pick_up);
        this.all = (TextView) findViewById(R.id.all);
        this.atCompany = (CheckBox) findViewById(R.id.at_company);
        this.confirmed = (CheckBox) findViewById(R.id.confirmed);
        this.received = (CheckBox) findViewById(R.id.received);
        this.delivered = (CheckBox) findViewById(R.id.delivered);
        this.rejected = (CheckBox) findViewById(R.id.rejected);
        this.startDate = (Button) findViewById(R.id.start_date);
        this.endDate = (Button) findViewById(R.id.end_date);
        this.closeIcon = (ImageView) findViewById(R.id.close);
        this.apply = (Button) findViewById(R.id.apply_button);
        this.confirmed.setChecked(OrdersFragment.confirmed.booleanValue());
        this.atCompany.setChecked(OrdersFragment.at_company.booleanValue());
        this.received.setChecked(OrdersFragment.received.booleanValue());
        this.delivered.setChecked(OrdersFragment.delivered.booleanValue());
        this.rejected.setChecked(OrdersFragment.rejected.booleanValue());
        if (!OrdersFragment.startDate.equalsIgnoreCase("")) {
            this.startDate.setText(OrdersFragment.startDate);
        }
        if (!OrdersFragment.endDate.equalsIgnoreCase("")) {
            this.endDate.setText(OrdersFragment.endDate);
        }
        if (OrdersFragment.type.equalsIgnoreCase("drop_off")) {
            this.dropOff.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.dark_primary)));
            this.all.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.white)));
            this.pickUp.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.white)));
            this.dropOff.setTextColor(ColorStateList.valueOf(this.context.getResources().getColor(R.color.white)));
            this.all.setTextColor(ColorStateList.valueOf(this.context.getResources().getColor(R.color.grey)));
            this.pickUp.setTextColor(ColorStateList.valueOf(this.context.getResources().getColor(R.color.grey)));
        } else if (OrdersFragment.type.equalsIgnoreCase("pick_up")) {
            this.pickUp.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.dark_primary)));
            this.all.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.white)));
            this.dropOff.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.white)));
            this.pickUp.setTextColor(ColorStateList.valueOf(this.context.getResources().getColor(R.color.white)));
            this.all.setTextColor(ColorStateList.valueOf(this.context.getResources().getColor(R.color.grey)));
            this.dropOff.setTextColor(ColorStateList.valueOf(this.context.getResources().getColor(R.color.grey)));
        }
    }
}
