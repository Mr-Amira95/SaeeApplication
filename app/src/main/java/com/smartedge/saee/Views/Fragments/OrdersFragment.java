package com.smartedge.saee.Views.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.Orders.OrdersResults;
import com.smartedge.saee.Networking.Models.UserInputModel;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Adapters.OrdersAdapter;
import com.smartedge.saee.Views.Dialogs.FilterDialog;

/* loaded from: classes3.dex */
public class OrdersFragment extends Fragment implements CallBack {
    ImageView filterIcon;
    ImageView noData;
    RecyclerView ordersRecyclerview;
    TextView ordersTotal;
    public static String type = "";
    public static String startDate = "";
    public static String endDate = "";
    public static Boolean requested = false;
    public static Boolean confirmed = true;
    public static Boolean received = true;
    public static Boolean at_company = true;
    public static Boolean rejected = true;
    public static Boolean delivered = true;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        initials(view);
        clicks();
        getOrders(this, getContext());
        return view;
    }

    private void clicks() {
        this.filterIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.OrdersFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FilterDialog filterDialog = new FilterDialog(OrdersFragment.this.getContext(), OrdersFragment.this);
                filterDialog.setCancelable(true);
                filterDialog.show();
            }
        });
    }

    private void initials(View view) {
        requested = false;
        confirmed = true;
        received = true;
        at_company = true;
        rejected = true;
        delivered = true;
        type = "";
        startDate = "";
        endDate = "";
        this.noData = (ImageView) view.findViewById(R.id.no_data);
        this.ordersTotal = (TextView) view.findViewById(R.id.orders_total);
        this.filterIcon = (ImageView) view.findViewById(R.id.filter);
        this.ordersRecyclerview = (RecyclerView) view.findViewById(R.id.orders_recyclerview);
        LinearLayoutManager discountsLinearLayoutManager = new LinearLayoutManager(getActivity());
        discountsLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.ordersRecyclerview.setLayoutManager(discountsLinearLayoutManager);
    }

    public static void getOrders(CallBack callBack, Context context) {
        UserInputModel userInput = new UserInputModel(requested.booleanValue(), rejected.booleanValue(), received.booleanValue(), delivered.booleanValue(), at_company.booleanValue(), confirmed.booleanValue(), startDate, endDate, type);
        MyApplication.getInstance().getHttpHelper().setCallback(callBack);
        MyApplication.getInstance().getHttpHelper().post(context, AppConstants.Orders_URL, 9, OrdersResults.class, userInput);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        OrdersResults ordersResults = (OrdersResults) result;
        if (ordersResults.getStatus()) {

            OrdersAdapter ordersAdapter = new OrdersAdapter(getContext(), ordersResults.getData().getItems(), "all");
            this.ordersRecyclerview.setAdapter(ordersAdapter);
            if (ordersResults.getData().getItems().size() > 0) {
                this.noData.setVisibility(View.GONE);
                this.ordersTotal.setText(ordersResults.getData().getItems().size() + " " + getString(R.string.orders));
            } else {
                this.noData.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), getString(R.string.no_orders_found), Toast.LENGTH_SHORT).show();
            }
        } else {
            this.noData.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), ordersResults.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int tag, Object result) {
        this.noData.setVisibility(View.VISIBLE);
        try {
            OrdersResults ordersResults = (OrdersResults) result;
            Toast.makeText(getContext(), ordersResults.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
