package com.example.springboot_study.infrastructure;

import com.example.springboot_study.config.NaverProperties;
import com.example.springboot_study.domain.Movie;
import com.example.springboot_study.domain.MovieRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NaverMovieRepository implements MovieRepository {

    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    @Override
    public List<Movie> findByTitle(String title) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getMovieUrl() + "?query=" + title;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseMovie.class)
                .getBody()
                .getItems()
                .stream()
                .map(m -> Movie.builder()
                        .title(m.getTitle())
                        .Rating(m.userRating)
                        .build())
                .collect(Collectors.toList());
    }

    @Data
    static class ResponseMovie implements Serializable {

        private List<Item> items;

        @Data
        public static class Item {
            String title;
            Float userRating;
        }
    }
}