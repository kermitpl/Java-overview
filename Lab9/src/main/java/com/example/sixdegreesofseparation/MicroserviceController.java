package com.example.sixdegreesofseparation;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MicroserviceController {

    public static Actor getActor(String id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://java.kisim.eu.org/actors/"+id, Actor.class);
    }

    public static Movie getMovie(String id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://java.kisim.eu.org/movies/"+id, Movie.class);

    }

    public static  List<Movie> getActorsMovies(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Movie [] movies2 = restTemplate.getForObject("https://java.kisim.eu.org/actors/"+id+"/movies", Movie[].class);
        List<Movie> movies = new ArrayList<>(Arrays.asList(movies2));
        int size = movies.size();
        movies.clear();
        for (int i=0; i<size; i++)
        {
            movies.add(getMovie(movies2[i].getId()));
        }
        return movies;
    }
}
