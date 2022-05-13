package com.example.topmovies.mvp;

import com.example.topmovies.api.Movie;
import com.example.topmovies.api.MovieAPIService;
import com.example.topmovies.api.MoviesInfoService;
import com.example.topmovies.api.Result;
import com.example.topmovies.api.TopRated;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class TopMoviesRepository implements Repository {

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    private final MovieAPIService movieAPIService;
    private final MoviesInfoService moviesInfoService;
    private List<String> countries;
    private List<Result> results;
    private long timestamp;

    public TopMoviesRepository(MovieAPIService movieAPIService, MoviesInfoService moviesInfoService) {
        this.movieAPIService = movieAPIService;
        this.moviesInfoService = moviesInfoService;
        this.timestamp = System.currentTimeMillis();
        this.countries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public boolean isRepoValid(){
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {
        if (isRepoValid()) {
            return Observable.from(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {
        Observable<TopRated> topRatedObservable = movieAPIService.getTopRatedMovies(1)
                .concatWith(movieAPIService.getTopRatedMovies(2))
                .concatWith(movieAPIService.getTopRatedMovies(3));

        return topRatedObservable.concatMap(new Func1<TopRated, Observable<Result>>() {
            @Override
            public Observable<Result> call(TopRated topRated) {
                return Observable.from(topRated.results);
            }

//            @Override
//            public Observable<Result> apply(TopRated topRated) {
//                return Observable.from(topRated.results);
//            }
        }).doOnNext(new Action1<Result>() {
            @Override
            public void call(Result result) {
                results.add(result);

            }
//            @Override
//            public void accept(Result result) {
//                results.add(result);
//            }
        });
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        if(isRepoValid()){
            return Observable.from(countries);
        } else {timestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {
        return getResultsFromNetwork().concatMap(new Func1<Result, Observable<Movie>>() {
            @Override
            public Observable<Movie> call(Result result) {
                return moviesInfoService.getCountry(result.title);
            }
        }).concatMap(new Func1<Movie, Observable<String>>() {
            @Override
            public Observable<String> call(Movie movie) {
                return Observable.just(movie.getCountry());
            }
        }).doOnNext(new Action1<String>() {
            @Override
            public void call(String string) {
                countries.add(string);
            }
        });
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}
