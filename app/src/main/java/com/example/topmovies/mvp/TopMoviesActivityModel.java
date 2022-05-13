package com.example.topmovies.mvp;

import androidx.lifecycle.ViewModel;

import rx.Observable;

public class TopMoviesActivityModel implements TopMoviesActivityMVP.Model {

    private Repository repository;;

    @Override
    public Observable<ViewModel> result() {
        return null;
    }
}
