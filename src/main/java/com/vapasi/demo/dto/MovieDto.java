package com.vapasi.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vapasi.demo.entities.MovieEntity;

import java.util.Objects;

public class MovieDto {

    private  Integer id =0;
    private final String movieName;
    private final String actorName;
    private final String directorName;

    @JsonCreator
    public MovieDto(String movieName, String actorName, String directorName){
        this(null, movieName, actorName, directorName);
    }

    public MovieDto(Integer id, String movieName, String actorName, String directorName) {
        this.id = id;
        this.movieName = movieName;
        this.actorName = actorName;
        this.directorName = directorName;
    }

    public Integer getId() {
        return id;
    }


    public String getMovieName() {
        return movieName;
    }


    public String getActorName() {
        return actorName;
    }


    public String getDirectorName() {
        return directorName;
    }

    public static MovieDto getDtoFromEntity(MovieEntity movieEntity)
    {
        return new MovieDto(movieEntity.getId(),
                        movieEntity.getMovieName(),
                        movieEntity.getActorName(),
                        movieEntity.getDirectorName());
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", actorName='" + actorName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDto)) return false;
        MovieDto movies = (MovieDto) o;
        return Objects.equals(movieName, movies.movieName) && Objects.equals(actorName, movies.actorName) && Objects.equals(directorName, movies.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, actorName, directorName);
    }

}
