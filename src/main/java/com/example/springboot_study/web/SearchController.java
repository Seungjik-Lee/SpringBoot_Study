package com.example.springboot_study.web;

import com.example.springboot_study.service.MovieDTO;
import com.example.springboot_study.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<MovieDTO> search(@RequestParam(name = "title") String title){

        return movieService.searchMovie(title);
    }

}
