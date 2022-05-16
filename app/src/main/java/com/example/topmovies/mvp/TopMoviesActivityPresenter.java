package com.example.topmovies.mvp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TopMoviesActivityPresenter implements TopMoviesActivityMVP.Presenter {

    private final TopMoviesActivityMVP.Model model;
    private TopMoviesActivityMVP.View topMoviesView;
    private Disposable subscription;

    public TopMoviesActivityPresenter(TopMoviesActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        subscription = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TopMoviesViewModel>() {
                    @Override
                    public void onNext(TopMoviesViewModel topMoviesViewModel) {
                        if (topMoviesView != null) {
                            topMoviesView.updateData(topMoviesViewModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (topMoviesView != null) {
                            topMoviesView.showSnackBar("Error getting movies");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void Unsubscribe() {
        if (subscription != null) {
            subscription.dispose();
        }
    }

    @Override
    public void setView(TopMoviesActivityMVP.View view) {
        this.topMoviesView = view;
    }
}
