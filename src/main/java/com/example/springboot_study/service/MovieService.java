package com.example.springboot_study.service;

import com.example.springboot_study.domain.Movie;
import com.example.springboot_study.domain.MovieGroup;
import com.example.springboot_study.exception.EmptyDataException;
import com.example.springboot_study.exception.ResponseCode;
import com.example.springboot_study.repository.MovieDTO;
import com.example.springboot_study.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDTO> searchMovie(final String title) {

        List<Movie> movieList = movieRepository.findByTitle(title);
        MovieGroup movieGroup = new MovieGroup(movieList);
        List<Movie> movieGroupList = movieGroup.sortByUserRating();

        if(movieGroupList.isEmpty()) {
            throw new EmptyDataException(ResponseCode.NOT_FOUND);
        }

        return movieList.stream()
                .map(m -> MovieDTO.builder()
                        .title(m.getTitle())
                        .userRating(m.getUserRating())
                        .image(m.getImage())
                        .subtitle(m.getSubtitle())
                        .director(m.getDirector())
                        .pubDate(m.getPubDate())
                        .build())
                .collect(Collectors.toList());
    }
}
