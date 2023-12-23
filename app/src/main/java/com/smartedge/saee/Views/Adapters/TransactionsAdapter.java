package com.smartedge.saee.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartedge.saee.Networking.Models.Transactions.Order;
import com.smartedge.saee.R;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {
    Context context;
    List<Order> transactions;

    public TransactionsAdapter(Context context, List<Order> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Order current = this.transactions.get(position);
        holder.referenceCode.setText(current.getRefCode());
        holder.dateTime.setText(current.getDropDate());

        if (current.getDeliveryPrice() != null) {
            holder.deliveryPrice.setText(this.context.getString(R.string.delivery_price) + current.getDeliveryPrice() + this.context.getString(R.string.currency));
        } else {
            holder.deliveryPrice.setVisibility(View.GONE);
        }

        if (!current.getOrderPrice().equalsIgnoreCase("0.00")) {
            holder.orderPrice.setText(this.context.getString(R.string.order_price) + current.getOrderPrice() + this.context.getString(R.string.currency));
        } else {
            holder.orderPrice.setVisibility(View.GONE);
        }

        if (position == getItemCount() - 1) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            int marginInPixels = this.context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._30sdp);
            layoutParams.bottomMargin = marginInPixels;
            return;
        }

        ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        int marginInPixels2 = this.context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp);
        layoutParams2.bottomMargin = marginInPixels2;
    }

    @Override
    public int getItemCount() {
        return this.transactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTime;
        TextView deliveryPrice;
        TextView orderPrice;
        TextView referenceCode;

        public ViewHolder(View itemView) {
            super(itemView);
            this.referenceCode = (TextView) itemView.findViewById(R.id.reference_code);
            this.dateTime = (TextView) itemView.findViewById(R.id.date_time);
            this.deliveryPrice = (TextView) itemView.findViewById(R.id.delivery_price);
            this.orderPrice = (TextView) itemView.findViewById(R.id.order_price);
        }
    }
}
