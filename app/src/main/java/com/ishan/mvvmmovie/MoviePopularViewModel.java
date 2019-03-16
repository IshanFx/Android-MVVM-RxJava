package com.ishan.mvvmmovie;

import android.util.Log;

import com.google.gson.JsonObject;
import com.ishan.mvvmmovie.data.model.Movie;
import com.ishan.mvvmmovie.data.model.MoviePopular;
import com.ishan.mvvmmovie.data.repository.MovieRepository;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePopularViewModel extends ViewModel {
    private static final String TAG = MoviePopularViewModel.class.getSimpleName();
    MovieRepository movieRepository;
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();
    private MutableLiveData<JsonObject> reviews = new MutableLiveData<>();
    Scheduler process;
    Scheduler android;

    public MoviePopularViewModel(MovieRepository movieRepository, Scheduler process,Scheduler android){
        this.movieRepository = movieRepository;
        this.process = process;
        this.android = android;
    }

    public void findPopularMovies(){
         movieRepository.getPopularMovies()
                .subscribeOn(process)
                .observeOn(android)
                .subscribe(new Observer<MoviePopular>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(MoviePopular moviePopular) {
                        movieList.setValue(moviePopular.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void findMostPopularMovieReview(){
        movieRepository.getPopularMovies()
                .flatMap(moviePopular -> {
                    Log.d(TAG, "findMostPopularMovieReview: ");
                   return movieRepository.getMovieReviews(moviePopular.getResults().get(0).getId());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        reviews.setValue(jsonObject);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<JsonObject> mostPopularMovieReview(){
        return reviews;
    }

    public MutableLiveData<ArrayList<Movie>> searchMovie(){
        return movieList;
    }
}
