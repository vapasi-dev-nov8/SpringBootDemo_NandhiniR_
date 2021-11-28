package com.vapasi.demo.Repository;

import com.vapasi.demo.dto.MovieDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MovieRepositoryWithoutDB {




    List<MovieDto> moviesList = new ArrayList<>(Arrays.asList(new MovieDto(1, "movie1", "A", "Aa"),
            new MovieDto(2, "movie2", "B", "Bb"),
            new MovieDto(3, "movie3", "C", "Cc")));

    public List<MovieDto> getMovies()
    {
        return moviesList;
    }

    public MovieDto saveMovie(MovieDto movieDto)
    {
        Integer id = moviesList.get(moviesList.size()-1).getId() +1;
        MovieDto newlyAddedMovieDto = new MovieDto(id, movieDto.getMovieName(), movieDto.getActorName(), movieDto.getDirectorName());
        moviesList.add(newlyAddedMovieDto);
        return newlyAddedMovieDto;
    }






}
