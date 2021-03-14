package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import com.brettdunbar.crowdStreetInvestorApi.dataObjects.ResponseStatusUpdate;
import com.brettdunbar.crowdStreetInvestorApi.dataObjects.RequestStatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class RequestObjectController {

    private final RequestObjectService requestObjectService;
    private final String exampleServiceUrl = "http://example.com/request";

    @Autowired
    public RequestObjectController(RequestObjectService requestObjectService) {
        this.requestObjectService = requestObjectService;
    }

    // POST request (1)
    // body template -- "{\"body\":\"random testing string for post\"}"
    @PostMapping("/request")
    public String postRequest(@RequestBody String body) {

        Random rand = new Random();
        int callbackId = rand.nextInt(1000);
        return ("/callback/" + callbackId);
    }

    // POST callback (2) -- Updates status with a callback id and a string
    @PostMapping("/callback/{id}")
    public HttpStatus postCallback(@RequestBody String status,@PathVariable int id) {
        System.out.println("Path var: " + id);
        System.out.println("body var: " + status);

        return HttpStatus.NO_CONTENT;
    }

    // PUT callback (3) -- updates the status of a RequestObject with some details
    // status update template -- "{\"status\":\"STARTING\",\"detail\":\"some details about it's process\"}"
    @PutMapping("/callback/{id}")
    public HttpStatus putStatusUpdate(@RequestBody RequestStatusUpdate statusUpdate, @PathVariable int id) {
        System.out.println("Path var: " + id);
        return HttpStatus.NO_CONTENT;
    }

    // GET status -- gets the status of a request by id
    @GetMapping("/status/{id}")
    ResponseStatusUpdate getRequestStatus(@PathVariable int id) {
        // todo update this
        return null;
    }



    // GET all RequestObjects
    @GetMapping
    public List<RequestObject> getRequestObjectList() {
        return requestObjectService.getRequestObjects();
    }
}
