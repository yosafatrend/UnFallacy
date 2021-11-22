package com.yorren.unfallacies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yorren.unfallacies.ChatActivity;
import com.yorren.unfallacies.R;

import java.util.ArrayList;

public class OptionChatAdapter extends RecyclerView.Adapter<OptionChatAdapter.MyViewHolder> {
    private ArrayList<String> optionArrayList;
    private Context context;

    public OptionChatAdapter(ArrayList<String> optionArrayList, Context context) {
        this.optionArrayList = optionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_option_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String chatsModal = optionArrayList.get(position);
       // String[] parts = chatsModal.getMessage().split("-");
        holder.tvUser.setText(chatsModal);
        holder.cvMsg.setOnClickListener(v -> {
            if (context instanceof ChatActivity) {
                ((ChatActivity)context).getResponse(holder.tvUser.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser;
        CardView cvMsg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            cvMsg = itemView.findViewById(R.id.cvMsg);
        }
    }

}
