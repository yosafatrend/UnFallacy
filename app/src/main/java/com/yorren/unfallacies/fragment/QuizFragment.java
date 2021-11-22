package com.yorren.unfallacies.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yorren.unfallacies.R;
import com.yorren.unfallacies.adapter.MateriAdapter;
import com.yorren.unfallacies.adapter.QuizAdapter;

import java.util.ArrayList;

public class QuizFragment extends Fragment {
    private RecyclerView rvQuiz;
    private ArrayList<String> quizArrayList;
    private QuizAdapter quizAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        rvQuiz = v.findViewById(R.id.rvQuiz);
        quizArrayList = new ArrayList<>();

        quizArrayList.add("Prestasi di Sekolah >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Petani dan Nelayan >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");

        quizAdapter = new QuizAdapter(quizArrayList, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        rvQuiz.setLayoutManager(manager);
        rvQuiz.setAdapter(quizAdapter);
        return v;
    }
}