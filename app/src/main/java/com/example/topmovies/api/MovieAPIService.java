package com.example.topmovies.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface MovieAPIService {
    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);
}
