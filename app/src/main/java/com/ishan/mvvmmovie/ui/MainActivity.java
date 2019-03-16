package com.ishan.mvvmmovie.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.ishan.mvvmmovie.MoviePopularViewModel;
import com.ishan.mvvmmovie.R;
import com.ishan.mvvmmovie.data.repository.MovieRepository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    private static final String TAG = MainActivity.class.getSimpleName();

    MoviePopularViewModel moviePopularViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviePopularViewModel = new MoviePopularViewModel(new MovieRepository(), Schedulers.io(), AndroidSchedulers.mainThread());
        moviePopularViewModel.searchMovie().observe(this, movies -> {
            Log.d(TAG, "Movie List:" + movies.size());
        });
        moviePopularViewModel.mostPopularMovieReview().observe(this,jsonObject -> {
            Log.d(TAG, "Movie List:" + new Gson().toJson(jsonObject));
        });
    }

    public void CheckMovie(View view) {
        moviePopularViewModel.findPopularMovies();
        moviePopularViewModel.findMostPopularMovieReview();
    }
}
