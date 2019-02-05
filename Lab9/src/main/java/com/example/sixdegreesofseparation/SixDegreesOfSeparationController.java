package com.example.sixdegreesofseparation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class SixDegreesOfSeparationController {

    @RequestMapping("/")
    public String running() {
        return "App is running.";
    }

    @RequestMapping("/findPath")
    public ResponseEntity<CompletePath> graphDemo(@RequestParam("actorStartID") String actorStartID, @RequestParam("actorStopID") String actorStopID) throws CloneNotSupportedException {
        GraphPathFinder g = new GraphPathFinder();
        try{
            MicroserviceController.getActor(actorStartID);
            MicroserviceController.getActor(actorStopID);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return g.runPathFinding(actorStartID, actorStopID);
    }

    @ExceptionHandler
    void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
