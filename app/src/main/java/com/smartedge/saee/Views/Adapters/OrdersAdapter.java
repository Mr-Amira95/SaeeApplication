package com.smartedge.saee.Views.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.smartedge.saee.Networking.Models.Orders.Item;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Activities.MainActivity;
import com.smartedge.saee.Views.Fragments.OrderDetailsFragment;
import com.smartedge.saee.Views.Fragments.OrdersUpcomingFragment;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    Context context;
    String flag;
    List<Item> items;

    public OrdersAdapter(Context context, List<Item> items, String flag) {
        this.context = context;
        this.items = items;
        this.flag = flag;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {

        Item current = items.get(position);

        if (position == getItemCount() - 1) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            int marginInPixels = this.context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._30sdp);
            layoutParams.bottomMargin = marginInPixels;
        } else {
            ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            int marginInPixels2 = this.context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp);
            layoutParams2.bottomMargin = marginInPixels2;
        }

        if (flag.equalsIgnoreCase("all")) {
            holder.selected.setVisibility(View.INVISIBLE);

            switch (String.valueOf(current.getStatus()).toLowerCase()){
                case "rejected":
                    holder.status.setText(context.getString(R.string.rejected));
                    holder.statusCard.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.status_rejected)));
                    break;

                case "delivered":
                    holder.status.setText(context.getString(R.string.delivered));
                    holder.statusCard.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.status_delivered)));

                    break;

                case "confirmed":
                    holder.status.setText(context.getString(R.string.confirmed));
                    holder.statusCard.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.status_confirmed)));
                    break;

                case "at_company":
                    holder.status.setText(context.getString(R.string.at_company));
                    holder.statusCard.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.status_at_company)));
                    break;

                case "received":
                    holder.status.setText(context.getString(R.string.received));
                    holder.statusCard.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.dark_primary)));
                    break;

            }

        } else if (current.getIsDropOff()){
            holder.status.setVisibility(View.GONE);
            holder.statusCard.setVisibility(View.GONE);
            holder.selected.setVisibility(View.INVISIBLE);

        } else {

            holder.status.setVisibility(View.GONE);
            holder.statusCard.setVisibility(View.GONE);
            holder.selected.setVisibility(View.VISIBLE);

        }

        if (current.getIsDropOff()){
            holder.type.setText(context.getString(R.string.drop_off));
            holder.customerName.setText(String.valueOf(current.getCustomer().getCustomerName()));
            holder.location.setText(current.getCustomer().getCustomerCity() + ", "
                    + current.getCustomer().getCustomerArea() + ", "
                    + current.getCustomer().getCustomerStreet() + ", "
                    + current.getCustomer().getCustomerBuilding());

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

        } else {
            holder.type.setText(context.getString(R.string.pick_up));
            holder.customerName.setText(String.valueOf(current.getClient().getClientName()));
            holder.location.setText(current.getClient().getClientCity() + ", "
                    + current.getClient().getClientArea() + ", "
                    + current.getClient().getClientStreet() + ", "
                    + current.getClient().getClientBuilding());

            holder.deliveryPrice.setVisibility(View.GONE);
            holder.orderPrice.setVisibility(View.GONE);

        }

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new OrderDetailsFragment(String.valueOf(current.getId())));
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!current.getIsDropOff()){
                    if (holder.selected.isChecked()){
                        holder.selected.setChecked(false);
                        OrdersUpcomingFragment.selectedIDs.remove(String.valueOf(current.getId()));
                        if (OrdersUpcomingFragment.selectedIDs.isEmpty()){
                            OrdersUpcomingFragment.clientName = "";
                            OrdersUpcomingFragment.changeStatus.setVisibility(View.GONE);
                        }
                    } else {
                        if(OrdersUpcomingFragment.selectedIDs.size()==0 || OrdersUpcomingFragment.clientName.equalsIgnoreCase(current.getClient().getClientName())){
                            OrdersUpcomingFragment.clientName = current.getClient().getClientName();
                            OrdersUpcomingFragment.selectedIDs.add(String.valueOf(current.getId()));
                            holder.selected.setChecked(true);
                            OrdersUpcomingFragment.changeStatus.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(context, context.getString(R.string.this_order_is_assigned_for_another_client), Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = ((MainActivity) this.context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerName;
        TextView deliveryPrice;
        Button details;
        ConstraintLayout layout;
        TextView location;
        TextView orderPrice;
        CheckBox selected;
        TextView status;
        CardView statusCard;
        TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            this.details = (Button) itemView.findViewById(R.id.details);
            this.type = (TextView) itemView.findViewById(R.id.type);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.customerName = (TextView) itemView.findViewById(R.id.customer_name);
            this.location = (TextView) itemView.findViewById(R.id.location);
            this.deliveryPrice = (TextView) itemView.findViewById(R.id.delivery_price);
            this.orderPrice = (TextView) itemView.findViewById(R.id.order_price);
            this.statusCard = (CardView) itemView.findViewById(R.id.status_card);
            this.layout = (ConstraintLayout) itemView.findViewById(R.id.layout);
            this.selected = (CheckBox) itemView.findViewById(R.id.selected);
        }
    }
}
