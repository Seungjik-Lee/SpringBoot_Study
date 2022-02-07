package com.example.springboot_study.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Movie {

    private final String title;
    private final Float Rating;
}