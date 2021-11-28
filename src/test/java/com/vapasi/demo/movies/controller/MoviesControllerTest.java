package com.vapasi.demo.movies.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vapasi.demo.Controller.MoviesController;
import com.vapasi.demo.Services.MovieService;
import com.vapasi.demo.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest (MoviesController.class)
public class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void shouldReturnOkIfMoviesReturned() throws Exception
    {
        mockMvc.perform(get("/api/v1/movies/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(movieService).getMovies();
    }

    @Test
    void shouldReturnBadRequestWhenWrongUrlIsHit() throws Exception
    {
        mockMvc.perform(get("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(movieService, times(0)).getMovies();
    }


   /* @Test
    void shouldReturnCreatedWhenNewMovieSaved() throws Exception
    {
        MovieDto movieDto = new MovieDto(null, "MovieDto Name", "Actor Name", "Director Name");
        MovieDto savedMovieDto = new MovieDto(1, "MovieDto Name", "Actor Name", "Director Name");


        when(movieService.saveMovie(movieDto)).thenReturn(savedMovieDto);

        mockMvc.perform(post("/api/v1/movies/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movieDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(savedMovieDto)));
        verify(movieService).saveMovie(movieDto);
    }*/




    private String asJsonString(MovieDto movieDto) {
        try {
            return new ObjectMapper().writeValueAsString(movieDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
