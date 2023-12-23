package com.smartedge.saee.Views.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Networking.Models.Message;
import com.smartedge.saee.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {
    Context context;
    ArrayList<Message> messageArrayList;

    public ChatsAdapter(Context context, ArrayList<Message> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Message current = this.messageArrayList.get(position);
        holder.message.setText(current.getMessage());
        Date date = new Date(current.getTimestamp().longValue());
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        holder.time.setText(sdf.format(date));

        if (PreferencesUtils.getDriverID().equalsIgnoreCase(current.getSenderId())) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            params.setMargins(100, 0, 0, 0);
            params.weight = 1.0f;
            params.gravity = GravityCompat.END;
            holder.message.setLayoutParams(params);
            holder.message.setGravity(GravityCompat.END);
            holder.time.setGravity(GravityCompat.END);
            holder.message.setBackgroundTintList(ColorStateList.valueOf(this.context.getColor(R.color.dark_primary)));
            holder.message.setTextColor(this.context.getColor(R.color.white));
            return;
        } else {
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(-2, -2);
            params2.setMargins(0, 0, 100, 0);
            holder.message.setLayoutParams(params2);
            params2.weight = 1.0f;
            params2.gravity = GravityCompat.START;
            holder.message.setGravity(GravityCompat.START);
            holder.time.setGravity(GravityCompat.START);
            holder.message.setBackgroundTintList(ColorStateList.valueOf(this.context.getColor(R.color.grey)));
            holder.message.setTextColor(this.context.getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            this.message = (TextView) itemView.findViewById(R.id.message);
            this.time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
