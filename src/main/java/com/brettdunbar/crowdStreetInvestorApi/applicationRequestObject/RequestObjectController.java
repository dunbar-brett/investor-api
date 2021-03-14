package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import com.brettdunbar.crowdStreetInvestorApi.dataObjects.ResponseStatusUpdate;
import com.brettdunbar.crowdStreetInvestorApi.dataObjects.RequestStatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class RequestObjectController {

    private final RequestObjectService requestObjectService;
    private final String exampleServiceUrl = "http://example.com/request";
    private static int mostRecentId = 2; // this is just for simplicity for testing and avoiding collision

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

        // create an entry
        mostRecentId++;
        RequestObject requestObject = new RequestObject(
            mostRecentId,
            body,
            "PROCESSING",
            "",
            new Date(),
            new Date()
        );

        RequestObject response = requestObjectService.addRequestObject(requestObject);

        if (response == null) {
            // since this responds with a string going to send error
            // back if there is an issue adding to the response object
            // I would also suggest adding some error logs if i had a
            // some sort of logger.
            return "ERROR";
        }

        return ("/callback/" + response.getRequestId());
    }

    // POST callback (2) -- Updates status with a callback id and a string
    //
    @PostMapping("/callback/{id}")
    public HttpStatus postCallback(@RequestBody String status,@PathVariable int id) {
        System.out.println("Path var: " + id);
        System.out.println("body var: " + status);

        // update status with id

        // if it fails respond with Bad Request

        return HttpStatus.NO_CONTENT;
    }

    // PUT callback (3) -- updates the status of a RequestObject with some details
    // status update template -- "{\"status\":\"STARTING\",\"detail\":\"some details about it's process\"}"
    @PutMapping("/callback/{id}")
    public HttpStatus putStatusUpdate(@RequestBody RequestStatusUpdate statusUpdate, @PathVariable int id) {
        System.out.println("Path var: " + id);
        System.out.println(statusUpdate);

        // update status and detail (in body) with id

        // if it fails respond with Bad Request

        return HttpStatus.NO_CONTENT;
    }

    // GET status (4) -- gets the status of a request by id
    @GetMapping("/status/{id}")
    public ResponseStatusUpdate getRequestStatus(@PathVariable int id) {

        // get response update with id
        RequestObject requestObject = requestObjectService.getRequestObjectById(id);
        // return null if doesn't exist
        if (requestObject == null) {
            // Add a warning logger if not found
            return null;
        }

        ResponseStatusUpdate response = new ResponseStatusUpdate(
            requestObject.getStatus(),
            requestObject.getDetail(),
            requestObject.getBody()
        );
        return response;
    }



    // GET all RequestObjects
    @GetMapping
    public List<RequestObject> getRequestObjectList() {
        return requestObjectService.getRequestObjects();
    }
}
