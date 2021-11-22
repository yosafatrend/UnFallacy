package com.yorren.unfallacies;

import com.yorren.unfallacies.model.Message;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<Message> getMessage(@Url String url);
}
