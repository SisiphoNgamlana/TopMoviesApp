package com.example.topmovies.mvp;

import androidx.lifecycle.ViewModel;

import com.example.topmovies.api.Result;

import java.util.function.BiFunction;

import rx.Observable;
import rx.functions.Func2;

public class TopMoviesActivityModel implements TopMoviesActivityMVP.Model {

    private Repository repository;

    public TopMoviesActivityModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<TopMoviesViewModel> result() {
        return Observable.zip(repository.getResultData(),
                repository.getCountryData(),
                new Func2<Result, String, TopMoviesViewModel>() {
                    @Override
                    public TopMoviesViewModel call(Result result, String countryString) {
                        return new TopMoviesViewModel(result.title, countryString);
                    }
                });
    }
}
