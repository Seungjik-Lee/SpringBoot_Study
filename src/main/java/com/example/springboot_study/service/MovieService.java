package com.example.springboot_study.service;

import com.example.springboot_study.domain.Movie;
import com.example.springboot_study.domain.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDTO> searchMovie(final String title) {

        return movieRepository.findByTitle(title).stream()
                .filter(movie -> movie.getRating() != 0.0f) // 평점이 0.0f이 아닌 데이터로 필터링
                .sorted(Comparator.comparing(Movie::getRating).reversed()) // 평점이 높은 순으로 정렬
                .map(MovieDTO::of)
                .collect(Collectors.toList());
    }
}
