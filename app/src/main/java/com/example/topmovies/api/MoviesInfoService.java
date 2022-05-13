package com.example.topmovies.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MoviesInfoService {

    @GET("/")
    Observable<Movie> getCountry(@Query("t") String title);
}
