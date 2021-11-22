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

import com.yorren.unfallacies.ChatActivity;
import com.yorren.unfallacies.MateriActivity;
import com.yorren.unfallacies.R;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {
    private ArrayList<String> quizArrayList;
    private Context context;

    public QuizAdapter(ArrayList<String> quizArrayList, Context context) {
        this.quizArrayList = quizArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String quiz = quizArrayList.get(position);
        String[] title = quiz.split(">");
        holder.tvTitle.setText(title[0]);
        holder.tvDesc.setText(title[1]);
        if (position == 0){
            holder.cvQuiz.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("epilog", holder.tvTitle.getText());
                intent.putExtra("starter", "Hai pada ngobrolin apa ini gimana hasil rapotnya");
                context.startActivity(intent);
            });
        }else{
            holder.layout_quiz.setBackgroundColor(Color.parseColor("#F1F1F1"));
        }
    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDesc;
        ConstraintLayout layout_quiz;
        CardView cvQuiz;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvQuiz = itemView.findViewById(R.id.cvQuiz);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            layout_quiz = itemView.findViewById(R.id.layout_quiz);
        }
    }
}
