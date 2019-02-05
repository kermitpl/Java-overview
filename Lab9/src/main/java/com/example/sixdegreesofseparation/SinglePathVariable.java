package com.example.sixdegreesofseparation;

public class SinglePathVariable {
    private String actorName;
    private String movieName;

    public SinglePathVariable() {
    }

    public SinglePathVariable(String actorName, String movieName) {
        this.actorName = actorName;
        this.movieName = movieName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
