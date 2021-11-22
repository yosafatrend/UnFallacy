package com.yorren.unfallacies.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yorren.unfallacies.MateriActivity;
import com.yorren.unfallacies.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MateriHomeAdapter extends RecyclerView.Adapter<MateriHomeAdapter.MyViewHolder> {
    private ArrayList<String> materiArrayList;
    private Context context;

    public MateriHomeAdapter(ArrayList<String> materiArrayList, Context context) {
        this.materiArrayList = materiArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MateriHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materi_home, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriHomeAdapter.MyViewHolder holder, int position) {
        String materi = materiArrayList.get(position);
        String[] title = materi.split(">");
        holder.tvMateri.setText(title[0]);
        if (position == 0){
            holder.cvMateri.setOnClickListener(v -> {
                context.startActivity(new Intent(context, MateriActivity.class));
            });
        }else{
            holder.layout_materi.setBackgroundColor(Color.parseColor("#8AA7D3"));
        }
    }

    @Override
    public int getItemCount() {
        return materiArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMateri;
        ConstraintLayout layout_materi;
        CardView cvMateri;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvMateri = itemView.findViewById(R.id.cvMateri);
            tvMateri = itemView.findViewById(R.id.tvMateri);
            layout_materi = itemView.findViewById(R.id.layout_materi);
        }
    }
}
