package com.smartedge.saee.Views.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.OrderDetails.Item;
import com.smartedge.saee.Networking.Models.OrderDetails.OrderDetails;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Activities.MainActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.HashMap;

import pub.devrel.easypermissions.EasyPermissions;

public class OrderDetailsFragment extends Fragment implements CallBack {
    RoundedImageView attachment;
    TextView clientSignature;
    TextView customerSignature;
    TextView deliveryPrice;
    TextView deliveryPriceTitle;
    TextView dropOff;
    String id;
    TextView idTxt;
    TextView location;
    Button locationBtn;
    TextView name;
    TextView notes;
    TextView notesTitle;
    OrderDetails orderDetails;
    TextView orderPrice;
    TextView orderPriceTitle;
    TextView phone;
    TextView pickUp;
    TextView refValue;
    TextView rejected;
    TextView rejectedTitle;
    PowerSpinnerView status;
    ImageView whatsapp;

    public OrderDetailsFragment(String id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);
        EasyPermissions.requestPermissions(this, getString(R.string.Please_Accept_Permission), 233, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA");
        initials(view);
        clicks();
        return view;
    }

    private void clicks() {

        this.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (orderDetails.getData().getItem().getStatus()) {
                    case "at_company":
                    case "confirmed":
                        OrderDetailsFragment.this.setFragment(new ChangeStatusFragment(orderDetails));
                        break;
                    default:
                        Toast.makeText(OrderDetailsFragment.this.getContext(), OrderDetailsFragment.this.getString(R.string.you_cant_change_the_status_of_this_order_contact_supervisor), Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        this.idTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(OrderDetailsFragment.this.getContext()).load(AppConstants.MEDIA_URL + OrderDetailsFragment.this.orderDetails.getData().getItem().getAttachment().getIdImage()).into(OrderDetailsFragment.this.attachment);
                OrderDetailsFragment.this.idTxt.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.dark_primary)));
                OrderDetailsFragment.this.clientSignature.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.customerSignature.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.idTxt.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.clientSignature.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.grey)));
                OrderDetailsFragment.this.customerSignature.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.grey)));
            }
        });
        this.customerSignature.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.OrderDetailsFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Glide.with(OrderDetailsFragment.this.getContext()).load(AppConstants.MEDIA_URL + OrderDetailsFragment.this.orderDetails.getData().getItem().getAttachment().getCustomerSignature()).into(OrderDetailsFragment.this.attachment);
                OrderDetailsFragment.this.customerSignature.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.dark_primary)));
                OrderDetailsFragment.this.idTxt.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.clientSignature.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.customerSignature.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.clientSignature.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.grey)));
                OrderDetailsFragment.this.idTxt.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.grey)));
            }
        });
        this.clientSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(OrderDetailsFragment.this.getContext()).load(AppConstants.MEDIA_URL + OrderDetailsFragment.this.orderDetails.getData().getItem().getAttachment().getClientSignature()).into(OrderDetailsFragment.this.attachment);
                OrderDetailsFragment.this.clientSignature.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.dark_primary)));
                OrderDetailsFragment.this.idTxt.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.customerSignature.setBackgroundTintList(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.clientSignature.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.white)));
                OrderDetailsFragment.this.customerSignature.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.grey)));
                OrderDetailsFragment.this.idTxt.setTextColor(ColorStateList.valueOf(OrderDetailsFragment.this.getResources().getColor(R.color.grey)));
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OrderDetailsFragment.this.orderDetails.getData().getItem().getIsDropOff().booleanValue()) {
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://wa.me/" + OrderDetailsFragment.this.orderDetails.getData().getItem().getCustomer().getCustomerNumber()));
                    OrderDetailsFragment.this.startActivity(i);
                } else {
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://wa.me/" + OrderDetailsFragment.this.orderDetails.getData().getItem().getClient().getClientNumber()));
                    OrderDetailsFragment.this.startActivity(i);
                }
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.OrderDetailsFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    private void initials(View view) {
        this.refValue = view.findViewById(R.id.ref_value);
        this.idTxt = view.findViewById(R.id.id);
        this.clientSignature = view.findViewById(R.id.signature);
        this.customerSignature = view.findViewById(R.id.customer_signature);
        this.status = view.findViewById(R.id.status_spinner);
        this.name = view.findViewById(R.id.name_value);
        this.phone = view.findViewById(R.id.phone_value);
        this.location = view.findViewById(R.id.location_value);
        this.pickUp = view.findViewById(R.id.pick_up);
        this.dropOff = view.findViewById(R.id.drop_off);
        this.deliveryPrice = view.findViewById(R.id.delivery_price_value);
        this.deliveryPriceTitle = view.findViewById(R.id.delivery_price_title);
        this.orderPrice = view.findViewById(R.id.item_price_value);
        this.orderPriceTitle = view.findViewById(R.id.item_price_title);
        this.rejected = view.findViewById(R.id.rejected_value);
        this.rejectedTitle = view.findViewById(R.id.rejected_title);
        this.notes = view.findViewById(R.id.notes_value);
        this.notesTitle = view.findViewById(R.id.notes_title);
        this.whatsapp = view.findViewById(R.id.whatsapp);
        this.attachment = view.findViewById(R.id.attachment_image);
        this.locationBtn = view.findViewById(R.id.get_location_button);
        getOrderDetails();
    }

    private void setOrderDetails(Item item) {

        this.refValue.setText(item.getRefCode());

        switch (item.getStatus()) {
            case "at_company":
                this.status.setHint(getString(R.string.at_company));
                break;
            case "received":
                this.status.setHint(getString(R.string.received));
                break;
            case "confirmed":
                this.status.setHint(getString(R.string.confirmed));
                break;
            case "delivered":
                this.status.setHint(getString(R.string.delivered));
                break;
            case "rejected":
                this.status.setHint(getString(R.string.rejected));
                break;

        }

        if (item.getRejectedReason() != null) {
            this.rejectedTitle.setVisibility(View.VISIBLE);
            this.rejected.setVisibility(View.VISIBLE);
            this.rejected.setText(item.getRejectedReason());
        } else {
            this.rejectedTitle.setVisibility(View.GONE);
            this.rejected.setVisibility(View.GONE);
        }

        if (item.getNote() != null) {
            this.notes.setText(item.getNote());
            this.notesTitle.setVisibility(View.VISIBLE);
            this.notes.setVisibility(View.VISIBLE);
        } else {
            this.notes.setVisibility(View.GONE);
            this.notesTitle.setVisibility(View.GONE);
        }

        if (item.getAttachment().getIdImage() != null) {
            Glide.with(getContext()).load(AppConstants.MEDIA_URL + this.orderDetails.getData().getItem().getAttachment().getIdImage()).into(this.attachment);
            this.idTxt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_primary)));
            this.clientSignature.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.customerSignature.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.idTxt.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.clientSignature.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
            this.customerSignature.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
        } else {
            this.idTxt.setVisibility(View.GONE);
        }

        if (item.getAttachment().getClientSignature() != null) {
            Glide.with(getContext()).load(AppConstants.MEDIA_URL + this.orderDetails.getData().getItem().getAttachment().getClientSignature()).into(this.attachment);
            this.clientSignature.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_primary)));
            this.idTxt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.customerSignature.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.clientSignature.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.customerSignature.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
            this.idTxt.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
        } else {
            this.clientSignature.setVisibility(View.GONE);
        }
        if (item.getAttachment().getCustomerSignature() != null) {
            Glide.with(getContext()).load(AppConstants.MEDIA_URL + this.orderDetails.getData().getItem().getAttachment().getCustomerSignature()).into(this.attachment);
            this.customerSignature.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_primary)));
            this.idTxt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.clientSignature.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.customerSignature.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.clientSignature.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
        } else {
            this.customerSignature.setVisibility(View.GONE);
        }

        if (item.getAttachment().getIdImage() != null || item.getAttachment().getClientSignature() != null || item.getAttachment().getCustomerSignature() != null) {
            this.attachment.setVisibility(View.VISIBLE);
        } else {
            this.attachment.setVisibility(View.GONE);
        }

        if (item.getIsDropOff()) {
            this.name.setText(item.getCustomer().getCustomerName());
            this.phone.setText(item.getCustomer().getCustomerNumber());
            this.location.setText(item.getCustomer().getCustomerCity() + ", " + item.getCustomer().getCustomerArea() + ", " + item.getCustomer().getCustomerStreet() + ", " + item.getCustomer().getCustomerBuilding());
            this.dropOff.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_primary)));
            this.pickUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.dropOff.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.pickUp.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));

            if (item.getDeliveryPrice() != null) {
                this.deliveryPrice.setText(item.getDeliveryPrice() + "JDs");
                this.deliveryPriceTitle.setVisibility(View.VISIBLE);
                this.deliveryPrice.setVisibility(View.VISIBLE);
            } else {
                this.deliveryPrice.setVisibility(View.GONE);
                this.deliveryPriceTitle.setVisibility(View.GONE);
            }

            if (!item.getOrderPrice().equalsIgnoreCase("0.00")) {
                this.orderPrice.setText(item.getOrderPrice() + "JDs");
                this.orderPriceTitle.setVisibility(View.VISIBLE);
                this.orderPrice.setVisibility(View.VISIBLE);
            } else {
                this.orderPrice.setVisibility(View.GONE);
                this.orderPriceTitle.setVisibility(View.GONE);
            }

        } else {

            this.orderPrice.setVisibility(View.GONE);
            this.orderPriceTitle.setVisibility(View.GONE);
            this.deliveryPrice.setVisibility(View.GONE);
            this.deliveryPriceTitle.setVisibility(View.GONE);

            this.name.setText(item.getClient().getClientName());
            this.phone.setText(item.getClient().getClientNumber());
            this.location.setText(item.getClient().getClientCity() + ", " + item.getClient().getClientArea() + ", " + item.getClient().getClientStreet() + ", " + item.getClient().getClientBuilding());
            this.pickUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_primary)));
            this.dropOff.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.pickUp.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            this.dropOff.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));

        }

    }

    private void getOrderDetails() {
        HashMap<String, Object> params = new HashMap<>();
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().get(getContext(), AppConstants.Orders_Details_URL + this.id, 10, OrderDetails.class, params);
    }

    @Override
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        OrderDetails orderDetails = (OrderDetails) result;
        this.orderDetails = orderDetails;
        if (orderDetails.getStatus()) {
            setOrderDetails(this.orderDetails.getData().getItem());
        } else {
            Toast.makeText(getContext(), this.orderDetails.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int tag, Object result) {
        try {
            this.orderDetails = (OrderDetails) result;
            Toast.makeText(getContext(), this.orderDetails.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = ((MainActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
