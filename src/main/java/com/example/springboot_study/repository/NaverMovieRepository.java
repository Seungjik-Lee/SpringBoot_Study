package com.example.springboot_study.repository;

import com.example.springboot_study.config.NaverApiConfig;
import com.example.springboot_study.domain.Movie;
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
    private final NaverApiConfig naverApiConfig;

    @Override
    public List<Movie> findByTitle(String title) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverApiConfig.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverApiConfig.getClientSecret());

        String url = naverApiConfig.getMovieUrl() + "?query=" + title;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseMovie.class)
                .getBody()
                .getItems()
                .stream()
                .map(m -> Movie.builder()
                        .title(m.getTitle())
                        .userRating(m.userRating)
                        .image(m.image)
                        .subtitle(m.subtitle)
                        .director(m.director)
                        .pubDate(m.pubDate)
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
            private String image;
            private String subtitle;
            private String director;
            private int pubDate;
        }
    }
}