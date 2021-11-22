package com.yorren.unfallacies.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yorren.unfallacies.R;
import com.yorren.unfallacies.Utils;
import com.yorren.unfallacies.adapter.MateriHomeAdapter;
import com.yorren.unfallacies.adapter.NewsHomeAdapter;
import com.yorren.unfallacies.adapter.QuizHomeAdapter;
import com.yorren.unfallacies.api.ApiNewsClient;
import com.yorren.unfallacies.api.ApiNewsInterface;
import com.yorren.unfallacies.model.Article;
import com.yorren.unfallacies.model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.yorren.unfallacies.fragment.NewsFragment.API_KEY;

public class HomeFragment extends Fragment {
    private TextView tvWelcome, tvMoreMateri, tvMoreQuiz, tvMoreBerita;
    private RecyclerView rvMateri, rvQuiz, rvNews;
    private ArrayList<String> materiArrayList, quizArrayList;
    private MateriHomeAdapter materiHomeAdapter;
    private QuizHomeAdapter quizHomeAdapter;
    private List<Article> articles = new ArrayList<>();
    private NewsHomeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rvMateri = v.findViewById(R.id.rvMater);
        rvQuiz = v.findViewById(R.id.rvQuiz);
        rvNews = v.findViewById(R.id.rvNews);
        tvWelcome = v.findViewById(R.id.tvWelcome);
        tvMoreBerita = v.findViewById(R.id.tvMoreBerita);
        tvMoreQuiz = v.findViewById(R.id.tvMoreQuiz);
        tvMoreMateri = v.findViewById(R.id.tvMoreMateri);

        String name = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("username", null);

        tvWelcome.setText("Selamat Datang, \n"+name);
        tvMoreMateri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MateriFragment llf = new MateriFragment();
                ft.replace(R.id.container_layout, llf);
                ft.commit();
            }
        });
        tvMoreQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                QuizFragment llf = new QuizFragment();
                ft.replace(R.id.container_layout, llf);
                ft.commit();
            }
        });
        tvMoreBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                NewsFragment llf = new NewsFragment();
                ft.replace(R.id.container_layout, llf);
                ft.commit();
            }
        });
        materiArrayList = new ArrayList<>();
        materiArrayList.add("Ad Hominem >Ad hominem adalah logical fallacy atau sesat pikir paling terkenal yang sering muncul karena kurangnya pengetahuan atau keterampilan penalaran.");
        materiArrayList.add("Argumentum Ad Populum >Jika kebanyakan orang melakukannya, itu pasti benar. Nah, ini pada dasarnya adalah argumentum ad populum, sering disebut sebagai bandwagon fallacy, keyakinan sesat pikir bahwa apa yang dikejar banyak orang adalah benar. Sayangnya, logika ini digunakan dalam beberapa perdebatan paling penting dalam sejarah manusia.");
        materiArrayList.add("Dilema Palsu >Dilema palsu adalah kesalahpahaman sesat pikir umum yang memberi seseorang pilihan terbatas ketika gak adanya pilihan lain tersedia. Dalam kasus-kasus tertentu opsi disajikan secara eksklusif, menunjukkan opsi yang gak kita inginkan, sedangkan opsi lain muncul sesuai keinginan dan terkesan rasional.");
        materiArrayList.add("Argument from Authority >Argumen dari otoritas, atau argumentum ad verecundiam, adalah jenis pemikiran yang salah  di mana seseorang menggunakan pendapat otoritas tentang sesuatu sebagai bukti untuk mendukung penalaran mereka sendiri.");

        materiHomeAdapter = new MateriHomeAdapter(materiArrayList, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvMateri.setLayoutManager(manager);
        rvMateri.setAdapter(materiHomeAdapter);

        quizArrayList = new ArrayList<>();
        quizArrayList.add("Prestasi di Sekolah >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Petani dan Nelayan >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizArrayList.add("Lorem ipsum >Jadilah pelaku ad hominem di suatu sekolah");
        quizHomeAdapter = new QuizHomeAdapter(quizArrayList, getActivity());
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());

        rvQuiz.setLayoutManager(manager2);
        rvQuiz.setAdapter(quizHomeAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvNews.setLayoutManager(layoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.setNestedScrollingEnabled(false);
        LoadJson("");

        return v;
    }

    public void LoadJson(final String keyword) {
        ApiNewsInterface apiInterface = ApiNewsClient.getApiClient().create(ApiNewsInterface.class);
        Call<News> call;

        if (keyword.length() > 0) {
            call = apiInterface.getNewsSearch(keyword, "id", "publishedAt", API_KEY);
        } else {
            call = apiInterface.getNews("logical fallacy", "en", "publishedAt", API_KEY);
        }

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }
                    articles = response.body().getArticle();
                    adapter = new NewsHomeAdapter(articles, getActivity());
                    rvNews.setAdapter(adapter);

                } else {
                    String errorCode;
                    switch (response.code()) {
                        case 404:
                            errorCode = "404 not found";
                            break;
                        case 500:
                            errorCode = "500 server broken";
                            break;
                        default:
                            errorCode = "unknown error";
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });
    }
}