package com.example.topmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.topmovies.mvp.ApplicationComponent;
import com.example.topmovies.mvp.TopMoviesActivityMVP;
import com.example.topmovies.mvp.TopMoviesActivityPresenter;
import com.example.topmovies.mvp.TopMoviesViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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
    TopMoviesActivityMVP.Presenter presenter;

    private List<TopMoviesViewModel> resultList = new ArrayList<>();
    ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        ((ApplicationComponent.App) getApplication()).getApplicationComponent().inject(this);

        ButterKnife.bind(this);

        listAdapter = new ListAdapter(resultList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(TopMoviesViewModel viewModel) {
        resultList.add(viewModel);
        listAdapter.notifyItemInserted(resultList.size() - 1);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(moviesListContainer, message, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.Unsubscribe();
        resultList.clear();
        listAdapter.notifyDataSetChanged();
    }
}