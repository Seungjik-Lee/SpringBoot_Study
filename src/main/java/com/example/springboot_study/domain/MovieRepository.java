package com.example.springboot_study.domain;

import java.util.List;

public interface MovieRepository {

    List<Movie> findByTitle(String title);

}