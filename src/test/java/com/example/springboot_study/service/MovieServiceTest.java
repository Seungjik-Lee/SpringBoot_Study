package com.example.springboot_study.service;

import com.example.springboot_study.domain.Movie;
import com.example.springboot_study.exception.EmptyDataException;
import com.example.springboot_study.repository.MovieDTO;
import com.example.springboot_study.repository.NaverMovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals(expectedRanking, actualList.stream().findFirst().get().getUserRating());
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

    @Test
    @DisplayName("데이터가 없는 에러가 발생했을시 예외처리가 발생하는지")
    void Exception_check_Empty_Data() {
        //given
        Mockito.when(naverMovieRepository.findByTitle(any())).thenReturn(Collections.emptyList());
        movieService = new MovieService(naverMovieRepository);

        //when
//        List<MovieDTO> actualList = movieService.searchMovie("쿼리");

        //then
        assertThrows(EmptyDataException.class, () -> movieService.searchMovie("테스트"));
    }

    private List<Movie> getStubMovieList() {

        return Arrays.asList(
                Movie.builder().title("<b>해리포터1</b> 제목").userRating(9.36f).build(),
                Movie.builder().title("<b>해리포터2</b> 제목").userRating(8.74f).build(),
                Movie.builder().title("<b>해리포터3</b> 제목").userRating(0.0f).build(),
                Movie.builder().title("<b>해리포터4</b> 제목").userRating(7.5f).build()
        );
    }
}