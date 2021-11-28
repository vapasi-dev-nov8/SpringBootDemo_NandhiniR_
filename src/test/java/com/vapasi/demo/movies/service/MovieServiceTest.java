package com.vapasi.demo.movies.service;

import com.vapasi.demo.Repository.MovieRepositoryWithoutDB;
//import com.vapasi.demo.Services.MovieService;
import com.vapasi.demo.Services.MovieService;
import com.vapasi.demo.dto.MovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MovieService.class)
@RunWith(MockitoJUnitRunner.class)
class MovieServiceTest {

    @InjectMocks
    MovieService moviesService;

    @MockBean
    MovieRepositoryWithoutDB repo;

    @BeforeEach
    public void setUp() {this.moviesService.movieRepositoryDummy = this.repo;}

    @Test
    void shouldReturnAllMovies()
    {
        List<MovieDto> allMovieDtos = new ArrayList<>();
        allMovieDtos.add(new MovieDto(101, "pirates of caribbean", "Johny Depp", "Gore Verbinsky"));
        allMovieDtos.add(new MovieDto(102, "The Fight club", "Brad pitt", "David Fincher"));
        allMovieDtos.add(new MovieDto(103, "Interstellar", "Mathew McConaughey", "Christopher Nolan"));
        allMovieDtos.add(new MovieDto(104, "pulp fiction", "Samuel L Johnson", "Quintine torentino"));

        when(repo.getMovies()).thenReturn(allMovieDtos);

        List<MovieDto> returnedList = moviesService.getMovies();
        verify(repo).getMovies();
        assertEquals(allMovieDtos, returnedList);
    }

    @Test
    void shouldAddAMovie()
    {
        MovieDto movieDto1 = mock(MovieDto.class);
        MovieDto movieDto2 = mock(MovieDto.class);

        when(repo.saveMovie(movieDto1)).thenReturn(movieDto2);

        MovieDto returnedMovieDto = moviesService.saveMovie(movieDto1);
        verify(repo).saveMovie(movieDto1);
        assertEquals(returnedMovieDto, movieDto2);

    }

}


