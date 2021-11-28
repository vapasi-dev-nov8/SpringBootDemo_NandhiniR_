package com.vapasi.demo.Repository;

import com.vapasi.demo.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    //@Query("Select * from movie_entity where actor_name in :actors")
    //public List<MovieEntity> findMoviesByListOfActorName(@Param("actors") List<String> actorsList);

    List<MovieEntity> findByActorName(String actorName);

    Iterable<MovieEntity> findAllByActorNameIn(List<String> actorName);



}
