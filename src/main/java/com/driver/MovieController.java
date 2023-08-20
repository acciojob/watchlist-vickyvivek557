package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        boolean addMovie = movieService.addMovie(movie);
        if(addMovie){
            return new ResponseEntity<>("Movie added successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Movie already present in list.", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        boolean addDirector = movieService.addDirector(director);
        if(addDirector) return new ResponseEntity<>("Director added successfully.", HttpStatus.OK);
        return new ResponseEntity<>("This Director is already present in list.", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        boolean add = movieService.addMovieDirectorPair(movieName, directorName);
        if(add) return new ResponseEntity<>("MovieDirectorPair added successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Can't add this pair. Ither movie or director does not exist.", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        Movie movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>>  getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> directorsMoviesList = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(directorsMoviesList, HttpStatus.OK);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> allMoviesList = movieService.findAllMovies();
        return new ResponseEntity<>(allMoviesList, HttpStatus.OK);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity("All movies of director "+ directorName + " deleted successfully.", HttpStatus.OK);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Directors and associated movies has been deleted.", HttpStatus.OK);
    }
}
