package com.example.sixdegreesofseparation;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphPathFinder {

    private boolean idEdgeFound(Graph<Actor, Movie> g, Actor actorStart, Actor actorStop) {
        if (g.containsVertex(actorStart) && g.containsVertex(actorStop)) {
            BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(g);
            if (bfsp.getPath(actorStart, actorStop) != null) {
                GraphPath<Actor, Movie> shortestPath = bfsp.getPath(actorStart, actorStop);
                if (shortestPath.getEdgeList() != null)
                {
                    System.out.println("found");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean loopWithQueue(Actor actorStart, Actor actorStop, AtomicBoolean continueLoop,
                              Queue<Actor> actorsQueue, Queue<Actor> actorsQueue2, Graph<Actor, Movie> g)
    throws CloneNotSupportedException{
        Actor dequeuedActor;
        do {
            dequeuedActor = actorsQueue.poll();
            int sizetocheck = actorsQueue.size();
            if (dequeuedActor != null) {
                List<Movie> dequeuedActorMovies = MicroserviceController.getActorsMovies(dequeuedActor.getId());

                for (Movie movie : dequeuedActorMovies) {
                    for (int j = 0; j < movie.getActorList().size(); j++) {
                        Actor actor = movie.getActorList().get(j);

                        if (!dequeuedActor.equals(actor)) {
                            actorsQueue2.add(actor);
                            g.addVertex(actor);
                            g.addEdge(dequeuedActor, actor, (Movie) (movie.clone()));
                        }
                    }
                }
            }
            if (sizetocheck%10==0) {
                if (idEdgeFound(g, actorStart, actorStop)) {
                    continueLoop.set(false);
                    return false;
                }
            }
        }
        while (dequeuedActor != null && continueLoop.get());
        return true;
    }

    public ResponseEntity<CompletePath> runPathFinding(String actorStartID, String actorStopID) throws CloneNotSupportedException {

        Graph<Actor, Movie> g = new SimpleGraph<>(Movie.class);
        Actor actorStart = MicroserviceController.getActor(actorStartID);
        Actor actorStop = MicroserviceController.getActor(actorStopID);

        Queue<Actor> actorsQueue0 = new ArrayDeque<>();
        Queue<Actor> actorsQueue1 = new ArrayDeque<>();
        Queue<Actor> actorsQueue2 = new ArrayDeque<>();
        Queue<Actor> actorsQueue3 = new ArrayDeque<>();
        Queue<Actor> actorsQueue4 = new ArrayDeque<>();
        Queue<Actor> actorsQueue5 = new ArrayDeque<>();

        AtomicBoolean continueLoop = new AtomicBoolean();
        continueLoop.set(true);

        g.addVertex(actorStart);
        actorsQueue0.add(actorStart);

        loopWithQueue(actorStart, actorStop, continueLoop, actorsQueue0, actorsQueue1, g);
        System.out.println("done1");
        if (continueLoop.get()) {
            if (loopWithQueue(actorStart, actorStop, continueLoop, actorsQueue1, actorsQueue2, g)) {
                System.out.println("done2");
                if (loopWithQueue(actorStart, actorStop, continueLoop, actorsQueue2, actorsQueue3, g)) {
                    System.out.println("done3");
                    if (loopWithQueue(actorStart, actorStop, continueLoop, actorsQueue3, actorsQueue4, g)) {
                        System.out.println("done4");
                        if (loopWithQueue(actorStart, actorStop, continueLoop, actorsQueue4, actorsQueue5, g)) {
                            System.out.println("done5");
                            if (loopWithQueue(actorStart, actorStop, continueLoop, actorsQueue5, actorsQueue1, g)) {
                                System.out.println("done6");
                            }
                        }
                    }
                }
            }
        }


        if (g.containsVertex(actorStop) ) {

            CompletePath completePath = CompletePathReturner.completePathReturn(g, actorStart, actorStop);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return new ResponseEntity<>(completePath, headers, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
