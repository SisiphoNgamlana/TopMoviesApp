package com.example.topmovies.mvp;

import com.example.topmovies.api.Result;

import rx.Observable;

public interface Repository {

    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();
}
