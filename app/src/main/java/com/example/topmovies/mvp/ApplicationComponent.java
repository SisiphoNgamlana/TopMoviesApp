package com.example.topmovies.mvp;

import android.app.Application;

import com.example.topmovies.TopMoviesActivity;
import com.example.topmovies.api.MoviesAPIModule;
import com.example.topmovies.api.MoviesInfoServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MoviesAPIModule.class, MoviesInfoServiceModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity topMoviesActivity);

    class App extends Application {

        private ApplicationComponent applicationComponent;

        @Override
        public void onCreate() {
            super.onCreate();
            applicationComponent = DaggerApplicationComponent.builder()
                    .moviesAPIModule(new MoviesAPIModule())
                    .moviesInfoServiceModule(new MoviesInfoServiceModule())
                    .build();
        }

        public ApplicationComponent getApplicationComponent() {
            return applicationComponent;
        }
    }
}
