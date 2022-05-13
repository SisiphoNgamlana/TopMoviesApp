package com.example.topmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.topmovies.mvp.TopMoviesActivityMVP;
import com.example.topmovies.mvp.TopMoviesActivityPresenter;
import com.example.topmovies.mvp.TopMoviesViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View {

    private final String TAG = TopMoviesActivity.class.getName();

    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerView;

    @BindView(R.id.container_movies_list)
    LinearLayout moviesListContainer;

    @Inject
    TopMoviesActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();

    }

    @Override
    public void updateData(TopMoviesViewModel viewModel) {


    }

    @Override
    public void showSnackBar(String message) {

    }
}