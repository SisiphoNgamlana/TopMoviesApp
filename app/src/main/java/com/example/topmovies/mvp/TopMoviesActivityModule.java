package com.example.topmovies.mvp;

import com.example.topmovies.api.MovieAPIService;
import com.example.topmovies.api.MoviesInfoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TopMoviesActivityModule {

    @Provides
    public TopMoviesActivityMVP.Presenter providesPresenter(TopMoviesActivityMVP.Model model) {
        return new TopMoviesActivityPresenter(model);
    }

    @Provides
    public TopMoviesActivityMVP.Model providesModel(Repository repository) {
        return new TopMoviesActivityModel(repository);

    }

    @Singleton
    @Provides
    public Repository providesRepository(MovieAPIService movieAPIService, MoviesInfoService moviesInfoService) {
        return new TopMoviesRepository(movieAPIService, moviesInfoService);
    }
}
