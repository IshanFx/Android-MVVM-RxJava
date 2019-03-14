package com.ishan.mvvmmovie.ui;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ishan.mvvmmovie.MoviePopularViewModel;
import com.ishan.mvvmmovie.R;
import com.ishan.mvvmmovie.data.model.Movie;
import com.ishan.mvvmmovie.data.model.MoviePopular;
import com.ishan.mvvmmovie.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    MoviePopularViewModel moviePopularViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviePopularViewModel = new MoviePopularViewModel(new MovieRepository());
        moviePopularViewModel.searchMovie().subscribe(new Observer<ArrayList<Movie>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<Movie> movies) {
                Log.d(TAG, "onNext: "+movies.get(0).getTitle());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void CheckMovie(View view) {
        moviePopularViewModel.findPopularMovies();
    }
}
