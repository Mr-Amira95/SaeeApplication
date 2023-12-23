package com.smartedge.saee.Views.Fragments;

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
import com.smartedge.saee.Networking.Models.Transactions.Transactions;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Adapters.TransactionsAdapter;

import java.util.HashMap;

/* loaded from: classes3.dex */
public class TransactionsFragment extends Fragment implements CallBack {
    TextView balance;
    ImageView noData;
    RecyclerView transactionsRecyclerview;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        initials(view);
        return view;
    }

    private void initials(View view) {
        this.noData = (ImageView) view.findViewById(R.id.no_data);
        this.balance = (TextView) view.findViewById(R.id.balance);
        this.transactionsRecyclerview = (RecyclerView) view.findViewById(R.id.transactions_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.transactionsRecyclerview.setLayoutManager(linearLayoutManager);
        getTransactions();
    }

    private void getTransactions() {
        HashMap<String, Object> params = new HashMap<>();
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().get(getContext(), AppConstants.Transaction_URL, 7, Transactions.class, params);
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        Transactions transactions = (Transactions) result;
        if (transactions.getStatus().booleanValue()) {
            this.balance.setText(String.valueOf(transactions.getData().getBalance()) + getString(R.string.currency));
            for (int i = 0; i < transactions.getData().getOrders().size(); i++) {
                if (transactions.getData().getOrders().get(i).getDeliveryPrice() == null && transactions.getData().getOrders().get(i).getOrderPrice().equalsIgnoreCase("0.00")) {
                    transactions.getData().getOrders().remove(i);
                }
            }
            TransactionsAdapter transactionsAdapter = new TransactionsAdapter(getContext(), transactions.getData().getOrders());
            this.transactionsRecyclerview.setAdapter(transactionsAdapter);
            if (transactions.getData().getOrders().size() > 0) {
                this.noData.setVisibility(View.GONE);
                return;
            } else {
                this.noData.setVisibility(View.VISIBLE);
                return;
            }
        }
        this.noData.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), transactions.getMeassage(), Toast.LENGTH_SHORT).show();
    }

    @Override // com.smartedge.saee.Networking.Network.CallBack
    public void onFailure(int tag, Object result) {
        try {
            Transactions transactions = (Transactions) result;
            Toast.makeText(getContext(), transactions.getMeassage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}
