package com.vapasi.demo.entities;

import com.vapasi.demo.dto.MovieDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieName;
    private String actorName;
    private String directorName;

    public MovieEntity() {
    }

    public MovieEntity(String movieName, String actorName, String directorName) {
        this.movieName = movieName;
        this.actorName = actorName;
        this.directorName = directorName;
    }


    public MovieEntity(Integer id, String movieName, String actorName, String directorName) {
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

    public Integer setId(Integer id) {
        return this.id = id;
    }


    public String setMovieName(String movieName) {
        return this.movieName = movieName;
    }


    public String setActorName(String actorName) {
        return this.actorName = actorName;
    }


    public String setDirectorName(String directorName) {
        return this.directorName = directorName;
    }




    public static MovieEntity convertToEntity(MovieDto movieDto){
        return new MovieEntity(movieDto.getMovieName(), movieDto.getActorName(), movieDto.getDirectorName());
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", actorName='" + actorName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
