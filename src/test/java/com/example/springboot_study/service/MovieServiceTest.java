package com.example.springboot_study.service;

import com.example.springboot_study.domain.Movie;
import com.example.springboot_study.infrastructure.NaverMovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private NaverMovieRepository naverMovieRepository;

    @Test
    @DisplayName("평점 순")
    void In_order_of_ratings() {
        //given
        float expectedRanking = 9.36f;
        Mockito.when(naverMovieRepository.findByTitle(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(naverMovieRepository);

        //when
        List<MovieDTO> actualList = movieService.searchMovie("쿼리");

        //then
        assertEquals(expectedRanking, actualList.stream().findFirst().get().getRating());
    }

    @Test
    @DisplayName("평점이 0인 데이터는 제외")
    void Excluding_zero_points() {
        //given
        int expectedMovieSize = 3;
        Mockito.when(naverMovieRepository.findByTitle(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(naverMovieRepository);

        //when
        List<MovieDTO> actualList = movieService.searchMovie("쿼리");

        //then
        assertEquals(expectedMovieSize, actualList.size());
    }

    private List<Movie> getStubMovieList() {

        return Arrays.asList(
                Movie.builder().title("<b>해리포터1</b> 제목").Rating(9.36f).build(),
                Movie.builder().title("<b>해리포터2</b> 제목").Rating(8.74f).build(),
                Movie.builder().title("<b>해리포터3</b> 제목").Rating(0.0f).build(),
                Movie.builder().title("<b>해리포터4</b> 제목").Rating(7.5f).build()
        );
    }
}