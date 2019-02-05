package com.example.sixdegreesofseparation;

import java.util.ArrayList;
import java.util.List;

public class CompletePath {
    private List<SinglePathVariable> path;

    public CompletePath() {
        path =new ArrayList<>();
    }

    public CompletePath(List<SinglePathVariable> path) {
        this.path = path;
    }

    public List<SinglePathVariable> getPath() {
        return path;
    }

    public void setPath(List<SinglePathVariable> path) {
        this.path = path;
    }
}
