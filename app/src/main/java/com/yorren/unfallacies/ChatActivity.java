package com.yorren.unfallacies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yorren.unfallacies.adapter.ChatRvAdapter;
import com.yorren.unfallacies.adapter.OptionChatAdapter;
import com.yorren.unfallacies.model.Chats;
import com.yorren.unfallacies.model.Message;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {
    private TextView tvEpilog;
    private RecyclerView chatsRv, optionRv;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<Chats> chatsArrayList;
    private ArrayList<String> optionChatArrayList;
    private ChatRvAdapter chatRvAdapter;
    private OptionChatAdapter optionChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatsRv = findViewById(R.id.rvChat);
        optionRv = findViewById(R.id.rv_option);
        chatsArrayList = new ArrayList<>();
        tvEpilog = findViewById(R.id.tvEpilog);

        chatRvAdapter = new ChatRvAdapter(chatsArrayList, this);
        optionChatAdapter = new OptionChatAdapter(optionChatArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRv.setLayoutManager(manager);
        chatsRv.setAdapter(chatRvAdapter);

        String title = getIntent().getStringExtra("epilog");
        String starter = getIntent().getStringExtra("starter");

        tvEpilog.setText("Anda adalah pelaku Ad Hominem  di suatu sekolah");

        getResponse(starter);
    }

    public void getResponse(String message){
        chatsArrayList.add(new Chats(message, USER_KEY));
        chatsRv.smoothScrollToPosition(chatRvAdapter.getItemCount());
        chatRvAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=161176&key=uLBGE7GfziUqYQnm&uid=[uid]&msg="+ message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<Message> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()){
                    Message modal = response.body();
                    optionChatArrayList = new ArrayList<>();

                    String[] reply = modal.getCnt().split("-");
                    try{
                        String[] replyValue1 = reply[0].split("/");
                        if (replyValue1[1].equals("1")){
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ChatActivity.this);
                            dlgAlert.setMessage("Selamat anda berhasil");
                            dlgAlert.setTitle("UnFallacy");
                            dlgAlert.setPositiveButton("OK", (dialog, which) -> {
                                startActivity(new Intent(ChatActivity.this, DashboardActivity.class));
                            });
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }else if (replyValue1[1].equals("0")){
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ChatActivity.this);
                            dlgAlert.setMessage("Yah anda gagal");
                            dlgAlert.setTitle("UnFallacy");
                            dlgAlert.setPositiveButton("Coba lagi", (dialog, which) -> {
                                Intent intent = new Intent(ChatActivity.this, ChatActivity.class);
                                intent.putExtra("starter", "Hai pada ngobrolin apa ini gimana hasil rapotnya");
                                startActivity(intent);
                            });
                            dlgAlert.setNegativeButton("Nyerah", (dialog, which) -> {
                                startActivity(new Intent(ChatActivity.this, DashboardActivity.class));
                            });
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();

                        }
                    }catch (Exception e){

                    }

                    for (int i = 0; i < reply.length; i++) {
                        if (i==0)
                            continue;
                        String[] replyValue = reply[i].split("/");
                        optionChatArrayList.add(replyValue[0]);
                    }

                    optionChatAdapter = new OptionChatAdapter(optionChatArrayList, ChatActivity.this);
                    LinearLayoutManager manager2 = new LinearLayoutManager(ChatActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    optionRv.setLayoutManager(manager2);
                    optionRv.setAdapter(optionChatAdapter);

                    chatsArrayList.add(new Chats(modal.getCnt(), BOT_KEY));
                    chatRvAdapter.notifyDataSetChanged();
                    chatsRv.smoothScrollToPosition(chatRvAdapter.getItemCount());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable throwable) {
                chatsArrayList.add(new Chats("Please revert your question", BOT_KEY));
                Toast.makeText(ChatActivity.this, "TESTESS " + throwable.toString(), Toast.LENGTH_LONG).show();
                chatRvAdapter.notifyDataSetChanged();
            }
        });
    }
}