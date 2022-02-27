package com.example.springboot_study.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Movie {

    private String title;
    private float userRating;
    private String image;
    private String subtitle;
    private String director;
    private int pubDate;

    public boolean isThisYearMovie() {

        int thisYear = LocalDate.now().getYear();

        return pubDate == thisYear;
    }
}
