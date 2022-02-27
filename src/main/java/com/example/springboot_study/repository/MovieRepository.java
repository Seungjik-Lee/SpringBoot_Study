package com.example.springboot_study.repository;

import com.example.springboot_study.domain.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> findByTitle(String title);

}