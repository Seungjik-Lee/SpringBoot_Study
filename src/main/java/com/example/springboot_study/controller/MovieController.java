package com.example.springboot_study.controller;

import com.example.springboot_study.repository.MovieDTO;
import com.example.springboot_study.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/api/search/movies")
    public List<MovieDTO> search(@RequestParam(name = "title") String title){

        return movieService.searchMovie(title);
    }

}
