package com.example.sixdegreesofseparation;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;

import java.util.List;

public class CompletePathReturner {

    public static CompletePath completePathReturn(Graph<Actor, Movie> g, Actor actorStart, Actor actorStop){
        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(g);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(actorStart, actorStop);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> actors = shortestPath.getVertexList();

        CompletePath completePath= new CompletePath();
        for (int i = 0; i < actors.size(); ++i) {
            if (i == actors.size() - 1) {
                completePath.getPath().add(new SinglePathVariable(actors.get(i).getName(), "-"));
                System.out.print(actors.get(i));
            } else {
                completePath.getPath().add(new SinglePathVariable(actors.get(i).getName(), edges.get(i).getTitle()));
                System.out.print(actors.get(i) + " -> " + edges.get(i).toString() + " -> ");
            }
        }
        return completePath;
    }
}
