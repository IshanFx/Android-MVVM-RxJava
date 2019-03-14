package com.ishan.mvvmmovie.data.rest;

import com.google.gson.JsonObject;
import com.ishan.mvvmmovie.data.model.MoviePopular;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MovieService {

    //get movie list
    @GET("/3/movie/popular")
    Observable<MoviePopular> getPopularMovies(@QueryMap HashMap<String,Object> params);
}
