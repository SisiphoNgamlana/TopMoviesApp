package com.example.topmovies.mvp;

import androidx.lifecycle.ViewModel;

import rx.Observable;

public interface TopMoviesActivityMVP {

    interface View {
        void updateData(TopMoviesViewModel topMoviesViewModel);

        void showSnackBar(String message);
    }

    interface Model {
        Observable<TopMoviesViewModel> result();
    }

    interface Presenter {
        void loadData();

        void Unsubscribe();

        void setView(TopMoviesActivityMVP.View view);
    }
}
