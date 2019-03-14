package com.ishan.mvvmmovie;

import android.util.Log;

import com.ishan.mvvmmovie.data.model.Movie;
import com.ishan.mvvmmovie.data.model.MoviePopular;
import com.ishan.mvvmmovie.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MoviePopularViewModel {
    private static final String TAG = MoviePopularViewModel.class.getSimpleName();
    MovieRepository movieRepository;
    public PublishSubject<ArrayList<Movie>> movieList;
    //public Observable<Boolean> hideTitle = Observable.cre;

    public MoviePopularViewModel(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        movieList = PublishSubject.create();
    }

    /*public void findPopularMovies(){
        movieRepository.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviePopular>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviePopular moviePopular) {
                        movieList.onNext(moviePopular.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/

    public void findPopularMovies(){
         movieRepository.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviePopular>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviePopular moviePopular) {
                        movieList.onNext(moviePopular.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public PublishSubject<ArrayList<Movie>> searchMovie(){
        return movieList;
    }
}
