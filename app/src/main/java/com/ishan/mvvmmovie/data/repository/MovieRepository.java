package com.ishan.mvvmmovie.data.repository;

import com.google.gson.JsonObject;
import com.ishan.mvvmmovie.data.model.MoviePopular;
import com.ishan.mvvmmovie.data.rest.MovieService;
import com.ishan.mvvmmovie.data.rest.NetworkConfig;
import com.ishan.mvvmmovie.util.Config;

import java.util.HashMap;

import io.reactivex.Observable;

public class MovieRepository {
    MovieService movieService;

    public MovieRepository() {
        movieService = new NetworkConfig().apiService();
    }

    public Observable<MoviePopular> getPopularMovies(){
        HashMap<String,Object> paramMap = new HashMap();
        paramMap.put("api_key", Config.API_KEY);
        paramMap.put("language","en-US");
        paramMap.put("page","1");
        return movieService.getPopularMovies(paramMap);
    }

    public Observable<JsonObject> getMovieReviews(String movieId){
        HashMap<String,Object> paramMap = new HashMap();
        paramMap.put("api_key", Config.API_KEY);
        paramMap.put("language","en-US");
        paramMap.put("page","1");
        return movieService.getMovieReviews(movieId,paramMap);
    }
}
