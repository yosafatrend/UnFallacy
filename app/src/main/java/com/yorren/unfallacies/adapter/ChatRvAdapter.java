package com.yorren.unfallacies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yorren.unfallacies.model.Chats;
import com.yorren.unfallacies.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ChatRvAdapter extends RecyclerView.Adapter {
    private ArrayList<Chats> chatsArrayList;
    private Context context;

    public ChatRvAdapter(ArrayList<Chats> chatsArrayList, Context context) {
        this.chatsArrayList = chatsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent, false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chats chats = chatsArrayList.get(position);
        switch (chats.getSender()){
            case "user":
                String name = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .getString("username", null);
                ((UserViewHolder)holder).userTv.setText(chats.getMessage());
                ((UserViewHolder)holder).userName.setText(name);
                break;
            case "bot":
                String[] parts = chats.getMessage().split("-");
                String[] parts1 = parts[0].split("/");
                ((BotViewHolder)holder).botMsgTv.setText(parts1[0]);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatsArrayList.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatsArrayList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userTv, userName;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTv = itemView.findViewById(R.id.tvUser);
            userName = itemView.findViewById(R.id.tvName);
        }
    }

    public static class  BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTv;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTv = itemView.findViewById(R.id.tvBot);
        }
    }
}
