package com.example.topmovies.mvp;

public class TopMoviesActivityPresenter implements TopMoviesActivityMVP.Presenter {

    private final TopMoviesActivityMVP.Model model;

    public TopMoviesActivityPresenter(TopMoviesActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void Unsubscribe() {

    }

    @Override
    public void setView(TopMoviesActivityMVP.View view) {

    }
}
