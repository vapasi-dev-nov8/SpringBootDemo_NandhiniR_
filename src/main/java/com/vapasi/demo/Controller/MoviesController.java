package com.vapasi.demo.Controller;


import com.vapasi.demo.Services.MovieService;
import com.vapasi.demo.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MoviesController {
    MovieService movieService;

    @Autowired
    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer id) {
       Optional<MovieDto> movieDto = movieService.getMovieById(id);
       if(movieDto.isPresent()) {
           return ResponseEntity.ok().body(movieDto.get());
       }
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/actor")
    public  ResponseEntity<List<MovieDto>> getMoviesByActor(@RequestParam String actorName) {
        List<MovieDto> moviesByActor = movieService.getMoviesByActor(actorName);
        if(moviesByActor.size() !=0)
        {
            return ResponseEntity.ok().body(moviesByActor);
        }
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/actors")
    public  ResponseEntity<List<MovieDto>> getMoviesByListOfActors(@RequestParam List<String> actorName) {
        List<MovieDto> moviesByActors = movieService.getMoviesByListOfActors(actorName);
        if(moviesByActors.size() !=0) {
            return ResponseEntity.ok().body(moviesByActors);
        }
        return  ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto newlyAddedMovieDto = movieService.saveMovie(movieDto);
        return ResponseEntity.status(201).body(newlyAddedMovieDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateDetailsOfAMovieById(@PathVariable Integer id, @RequestBody MovieDto movieDto) {
        MovieDto updatedMovie =  movieService.updateMovieDetails(id, movieDto);
        if(movieDto != null)
        {
            return ResponseEntity.ok().body(updatedMovie);
        }
        return ResponseEntity.notFound().build();
    }
}
