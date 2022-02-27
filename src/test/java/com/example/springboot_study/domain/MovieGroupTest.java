package com.example.springboot_study.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieGroupTest {

    @Test
    @DisplayName("평점이 높은 순서로 정렬이 되는지")
    void sortByUserRating_orderByDesc() {

        //given
        float expectedUserRanking = 9.9f;

        //when
        List<Movie> actualList = movieGroup.sortByUserRating();

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());
    }

    @Test
    @DisplayName("평점이 0인 데이터는 제외하는지")
    void sortByUserRating_excludeZero() {

        //given
        int expectedMovieSize = 4;

        //when
        List<Movie> actualList = movieGroup.sortByUserRating();

        //then
        assertEquals(expectedMovieSize, actualList.size());
    }

    private MovieGroup movieGroup = new MovieGroup(
            Arrays.asList(
                    Movie.builder().title("스파이더맨").userRating(9.0f).build(),
                    Movie.builder().title("아이언맨").userRating(9.9f).build(),
                    Movie.builder().title("앤트맨").userRating(8.5f).build(),
                    Movie.builder().title("어벤져스").userRating(0.0f).build(),
                    Movie.builder().title("닥터스트레인지").userRating(7.5f).build(),
                    Movie.builder().title("헐크").userRating(0.0f).build()
            )
    );
}