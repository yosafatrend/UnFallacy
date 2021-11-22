package com.yorren.unfallacies.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yorren.unfallacies.R;
import com.yorren.unfallacies.adapter.MateriAdapter;

import java.util.ArrayList;

public class MateriFragment extends Fragment {
    private RecyclerView rvMateri;
    private ArrayList<String> materiArrayList;
    private MateriAdapter materiAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_materi, container, false);

        rvMateri = v.findViewById(R.id.rvMateri);
        materiArrayList = new ArrayList<>();

        materiArrayList.add("Ad Hominem >Ad hominem adalah logical fallacy atau sesat pikir paling terkenal yang sering muncul karena kurangnya pengetahuan atau keterampilan penalaran.");
        materiArrayList.add("Argumentum Ad Populum >Jika kebanyakan orang melakukannya, itu pasti benar. Nah, ini pada dasarnya adalah argumentum ad populum, sering disebut sebagai bandwagon fallacy, keyakinan sesat pikir bahwa apa yang dikejar banyak orang adalah benar. Sayangnya, logika ini digunakan dalam beberapa perdebatan paling penting dalam sejarah manusia.");
        materiArrayList.add("Dilema Palsu >Dilema palsu adalah kesalahpahaman sesat pikir umum yang memberi seseorang pilihan terbatas ketika gak adanya pilihan lain tersedia. Dalam kasus-kasus tertentu opsi disajikan secara eksklusif, menunjukkan opsi yang gak kita inginkan, sedangkan opsi lain muncul sesuai keinginan dan terkesan rasional.");
        materiArrayList.add("Argument from Authority >Argumen dari otoritas, atau argumentum ad verecundiam, adalah jenis pemikiran yang salah  di mana seseorang menggunakan pendapat otoritas tentang sesuatu sebagai bukti untuk mendukung penalaran mereka sendiri.");

        materiAdapter = new MateriAdapter(materiArrayList, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        rvMateri.setLayoutManager(manager);
        rvMateri.setAdapter(materiAdapter);
        return v;
    }
}