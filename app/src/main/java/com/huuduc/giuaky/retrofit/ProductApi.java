package com.huuduc.giuaky.retrofit;

import com.huuduc.giuaky.data.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {

    @GET("/product/favorite")
    Call<List<Product>> getAllByFavorite();

    @GET("/product/{type}")
    Call<List<Product>> getAllByType(@Path("type") int type);
}
