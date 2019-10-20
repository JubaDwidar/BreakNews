package com.juba.breaknews.Api;

import com.juba.breaknews.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headLines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey

    );

    @GET("everything")
    Call<News> getNewsSearch(

            @Query("q") String query,
            @Query("country") String country,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey

            );

}
