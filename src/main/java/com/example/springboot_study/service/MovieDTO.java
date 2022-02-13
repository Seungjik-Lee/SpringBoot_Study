package com.example.springboot_study.service;

import com.example.springboot_study.domain.Movie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDTO {

    private final String title;
    private final Float Rating;

    public static MovieDTO of(Movie mv) {
        return MovieDTO.builder()
                .title(mv.getTitle())
                .Rating(mv.getRating())
                .build();
    }
}
