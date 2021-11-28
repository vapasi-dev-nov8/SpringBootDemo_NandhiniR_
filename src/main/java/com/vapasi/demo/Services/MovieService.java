package com.vapasi.demo.Services;

import com.vapasi.demo.Repository.MovieRepository;
import com.vapasi.demo.dto.MovieDto;
import com.vapasi.demo.entities.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    public MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {this.movieRepository = movieRepository;}

    public List<MovieDto> getAllMovies() {

        List<MovieDto> movies = new ArrayList<>();
        for(MovieEntity movie : movieRepository.findAll()) {
            movies.add(MovieDto.getDtoFromEntity(movie));
        }
        return movies;
    }

    public MovieDto saveMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.convertToEntity(movieDto);
        MovieEntity newMovieEntity = movieRepository.save(movieEntity);
        return MovieDto.getDtoFromEntity(newMovieEntity);
    }


    public Optional<MovieDto> getMovieById(Integer id)
    {
         Optional<MovieEntity> movieEntity = movieRepository.findById(id);
         return movieEntity.map(MovieDto::getDtoFromEntity);
    }

    public MovieDto updateMovieDetails(Integer id, MovieDto movieDto)
    {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        MovieEntity movieToBeUpdated;

        if(movieEntity.isPresent())
        {
            movieToBeUpdated = movieEntity.get();
            movieToBeUpdated.setActorName(movieDto.getActorName());
            movieToBeUpdated.setDirectorName(movieDto.getDirectorName());
            movieToBeUpdated.setMovieName( movieDto.getMovieName());
        }
        else{
            movieToBeUpdated = new MovieEntity(movieDto.getMovieName(),movieDto.getActorName(), movieDto.getDirectorName());
        }
        MovieEntity updateMovieEntity =  movieRepository.save(movieToBeUpdated);
        return  MovieDto.getDtoFromEntity(updateMovieEntity);
    }

    public List<MovieDto> getMoviesByListOfActors(List<String> actorNames)
    {
        Iterable<MovieEntity> movies = movieRepository.findAllByActorNameIn(actorNames);
        List<MovieDto> returnedMovies = new ArrayList<>();
        for(MovieEntity movie: movies)
        {
           returnedMovies.add(MovieDto.getDtoFromEntity(movie));
        }
        return returnedMovies;
    }

    public List<MovieDto> getMoviesByActor(String actor)
    {
        List<MovieEntity> movies = movieRepository.findByActorName(actor);
        List<MovieDto> returnedMovies = new ArrayList<>();
        for(MovieEntity movie: movies)
        {
            returnedMovies.add(MovieDto.getDtoFromEntity(movie));
        }
        return returnedMovies;
    }

}
