package com.huuduc.giuaky.retrofit;

import com.huuduc.giuaky.data.User.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("/user/{cartId}")
    Call<User> getByCartId(@Path("cartId") long cartId);
}
