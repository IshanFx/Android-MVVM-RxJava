package com.ishan.mvvmmovie;

import com.ishan.mvvmmovie.data.model.Movie;
import com.ishan.mvvmmovie.data.model.MoviePopular;
import com.ishan.mvvmmovie.data.repository.MovieRepository;
import com.ishan.mvvmmovie.data.rest.MovieService;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    MovieService mockApiService;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void find_movie(){
        MoviePopular moviePopular = new MoviePopular();
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);

        moviePopular.setPage(1);
        moviePopular.setTotalPages(1);
        moviePopular.setTotalResult(1);
        moviePopular.setResults(movies);

        Observable<MoviePopular> mockObservable = Observable.just(moviePopular);

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        MoviePopularViewModel moviePopularViewModel = new MoviePopularViewModel(movieRepository);
        doReturn(mockObservable).when(moviePopularViewModel).findPopularMovies();

        TestSubscriber<Movie> subscriber = new TestSubscriber<>();
        //moviePopularViewModel.movieList.subscribe(subscriber);

    }
}