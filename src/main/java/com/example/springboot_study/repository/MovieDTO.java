package com.example.springboot_study.repository;

import com.example.springboot_study.domain.Movie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDTO {

    private String title;
    private float userRating;
    private String image;
    private String subtitle;
    private String director;
    private int pubDate;

    public static MovieDTO of(Movie mv) {
        return MovieDTO.builder()
                .title(mv.getTitle())
                .userRating(mv.getUserRating())
                .image(mv.getImage())
                .subtitle(mv.getSubtitle())
                .director(mv.getDirector())
                .pubDate(mv.getPubDate())
                .build();
    }
}
