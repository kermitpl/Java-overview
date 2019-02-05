package com.example.sixdegreesofseparation;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Cloneable{
    private String id;
    private String title;
    private List<Actor> actors;

    public Movie() {
        actors = new LinkedList<>();
    }

    public Movie(String id, String title) {
        this.id = id;
        this.title = title;
        this.actors = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Actor> getActorList() {
        return actors;
    }

    public void setActors(List<Actor> actorList) {
        this.actors= actorList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

