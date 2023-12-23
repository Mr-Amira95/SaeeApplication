package com.smartedge.saee.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartedge.saee.Networking.Basic.PreferencesUtils;
import com.smartedge.saee.Networking.Models.Message;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Adapters.ChatsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes3.dex */
public class ChatFragment extends Fragment {
    RecyclerView chatRecyclerview;
    FirebaseDatabase database;
    EditText message;
    ArrayList<Message> messageArrayList;
    DatabaseReference reference;
    ImageView sendIcon;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        initials(view);
        clicks();
        return view;
    }

    private void clicks() {
        this.sendIcon.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.ChatFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatFragment.this.message.getText().toString().isEmpty()) {
                    Toast.makeText(ChatFragment.this.getContext(), ChatFragment.this.getString(R.string.please_write_a_message_to_send), Toast.LENGTH_SHORT).show();
                    return;
                }
                Date date = new Date();
                Message message1 = new Message(false, ChatFragment.this.message.getText().toString(), "Admin", PreferencesUtils.getDriverID(), "sent", Long.valueOf(date.getTime()));
                ChatFragment.this.database = FirebaseDatabase.getInstance();
                ChatFragment.this.database.getReference().child("chats").child(PreferencesUtils.getDriverID() + "-Admin").child("messages").push().setValue(message1).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.smartedge.saee.Views.Fragments.ChatFragment.1.1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task<Void> task) {
                        ChatFragment.this.message.setText("");
                    }
                });
            }
        });
    }

    private void initials(View view) {
        this.chatRecyclerview = (RecyclerView) view.findViewById(R.id.chat_recyclerview);
        this.message = (EditText) view.findViewById(R.id.message);
        this.sendIcon = (ImageView) view.findViewById(R.id.send);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        this.chatRecyclerview.setLayoutManager(linearLayoutManager);
        this.messageArrayList = new ArrayList<>();
        final ChatsAdapter chatsAdapter = new ChatsAdapter(getContext(), this.messageArrayList);
        this.chatRecyclerview.setAdapter(chatsAdapter);
        FirebaseApp.initializeApp(getContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        DatabaseReference child = firebaseDatabase.getReference().child("chats").child(PreferencesUtils.getDriverID() + "-Admin").child("messages");
        this.reference = child;
        child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.smartedge.saee.Views.Fragments.ChatFragment.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    ChatFragment.this.reference.setValue(null);
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        this.reference.addValueEventListener(new ValueEventListener() { // from class: com.smartedge.saee.Views.Fragments.ChatFragment.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot snapshot) {
                ChatFragment.this.messageArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Message message1 = (Message) dataSnapshot.getValue(Message.class);
                    ChatFragment.this.messageArrayList.add(message1);
                }
                chatsAdapter.notifyDataSetChanged();
                try {
                    ChatFragment.this.chatRecyclerview.smoothScrollToPosition(ChatFragment.this.chatRecyclerview.getAdapter().getItemCount() - 1);
                } catch (Exception e) {
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
