package com.huuduc.giuaky.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.251.5.170:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return this.retrofit;
    }
}
