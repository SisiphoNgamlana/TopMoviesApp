package com.example.topmovies.mvp;

import androidx.lifecycle.ViewModel;

import rx.Observable;

public interface TopMoviesActivityMVP {

    interface View {
        void updateData(ViewModel viewModel);

        void showSnackBar(String message);
    }

    interface Model {
        Observable<ViewModel> result();
    }

    interface presenter {
        void loadData();

        void Unsubscribe();

        void setView(TopMoviesActivityMVP.View view);
    }
}
