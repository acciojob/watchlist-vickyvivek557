package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieDb = new HashMap<>();
    HashMap<String, Director> directorDb = new HashMap<>();
    HashMap<String, List<String>> movieDirectorPairDb = new HashMap<>();


    public boolean addMovie(Movie movie) {
        if(movieDb.isEmpty() || !movieDb.containsKey(movie)){
            movieDb.put(movie.getName(), movie);
            return true;
        }
        return false;
    }

    public boolean addDirector(Director director) {
        if(directorDb.isEmpty() || !directorDb.containsKey(director)){
            directorDb.put(director.getName(), director);
            return true;
        }
        return false;
    }

    public boolean addMovieDirectorPair(String movieName, String directorName) {
        if(movieDb.containsKey(movieName) && directorDb.containsKey(directorName)){
            if(movieDirectorPairDb.containsKey(directorName)){
                movieDirectorPairDb.get(directorName).add(movieName);
                return true;
            }
            List<String> movieNameList = new ArrayList<>();
            movieNameList.add(movieName);
            movieDirectorPairDb.put(directorName, movieNameList);
            return true;
        }
        return false;
    }

    public Movie getMovieByName(String movieName) {
        if(movieDb.containsKey(movieName)){
            return movieDb.get(movieName);
        }
        return new Movie();
    }

    public Director getDirectorByName(String directorName) {
        if(directorDb.containsKey(directorName)){
            return directorDb.get(directorName);
        }
        return new Director();
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        if(movieDirectorPairDb.containsKey(directorName)){
            return movieDirectorPairDb.get(directorName);
        }
        return new ArrayList<String>();
    }

    public List<String> findAllMovies() {
        if(movieDb.isEmpty()){
            return new ArrayList<String>();
        }
        return new ArrayList<>(movieDb.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        if(movieDirectorPairDb.containsKey(directorName)){
            for(String movie : movieDirectorPairDb.get(directorName)){
                movieDb.remove(movie);
            }
            movieDirectorPairDb.remove(directorName);
        }
        if(directorDb.containsKey(directorName)){
            directorDb.remove(directorName);
        }
    }

    public void deleteAllDirectors() {
        for(String directorName : movieDirectorPairDb.keySet()){
            for(String movieName : movieDirectorPairDb.get(directorName)){
                movieDb.remove(movieName);
            }
            movieDirectorPairDb.remove(directorName);
        }
        if(!directorDb.isEmpty()){
            for(String director: directorDb.keySet()){
                directorDb.remove(director);
            }
        }
        return;
    }

}
