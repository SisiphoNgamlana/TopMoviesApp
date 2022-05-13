package com.example.topmovies.mvp;

import androidx.lifecycle.ViewModel;

import com.example.topmovies.api.Result;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class TopMoviesActivityModel implements TopMoviesActivityMVP.Model {

    private final Repository repository;

    public TopMoviesActivityModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<TopMoviesViewModel> result() {
        return Observable.zip(repository.getResultData(),
                repository.getCountryData(),
                new BiFunction<Result, String, TopMoviesViewModel>() {
                    @Override
                    public TopMoviesViewModel apply(Result result, String countryString) throws Exception {
                        return new TopMoviesViewModel(result.title, countryString);
                    }
                });
    }
}
