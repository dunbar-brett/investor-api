package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import com.brettdunbar.crowdStreetInvestorApi.dataObjects.JsonBodyRequest;
import com.brettdunbar.crowdStreetInvestorApi.dataObjects.RequestStatusUpdate;
import com.brettdunbar.crowdStreetInvestorApi.dataObjects.ResponseStatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

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
    // Error Handling:
    // since this responds with a string going to send error
    // back if there is an issue adding to the response object
    // I would also suggest adding some error logs if i had a
    // some sort of logger service.
    @PostMapping("/request")
    public String postRequest(@RequestBody JsonBodyRequest requestBody) {
        // keeping track of id to avoid collision, would be better if this was a UUID
        mostRecentId++;

        // mock async request to exampleServiceUrl
        try {
            // build json object to send to service
            ThirdPartyServiceObject serviceObject = new ThirdPartyServiceObject(
                "/callback/" + mostRecentId,
                    requestBody.body
            );
            buildAndSendThirdPartyServiceRequest();
        } catch (ExecutionException e) {
            e.printStackTrace();
            // log response null error/warning
            return "ERROR";
        } catch (InterruptedException e) {
            e.printStackTrace();
            // log response null error/warning
            return "ERROR";
        }

        // create a requestObject entry
        RequestObject requestObject = new RequestObject(
                mostRecentId,
                requestBody.body,
                "PROCESSING",
                "",
                new Date(),
                new Date()
        );

        RequestObject response = requestObjectService.addRequestObject(requestObject);

        if (response == null) {
            // log response null error/warning
            return "ERROR";
        }

        return ("/callback/" + response.getRequestId());
    }

    // POST callback (2) -- Updates status with a callback id and a string
    @PostMapping("/callback/{id}")
    public HttpStatus postCallback(@RequestBody String status,@PathVariable int id) {
        // check if id is in list
        if (!requestObjectService.doesRequestIdExist(id)) {
            // log warning
            return HttpStatus.NOT_FOUND;
        }
        // update status with id
        requestObjectService.updateStatusById(id, status);
        // if it fails respond with Bad Request
        // since the update is void not actually handling

        return HttpStatus.NO_CONTENT;
    }

    // PUT callback (3) -- updates the status of a RequestObject with some details
    // status update template -- "{\"status\":\"STARTING\",\"detail\":\"some details about it's process\"}"
    @PutMapping("/callback/{id}")
    public HttpStatus putStatusUpdate(@RequestBody RequestStatusUpdate statusUpdate, @PathVariable int id) {
        // check if id is in list
        if (!requestObjectService.doesRequestIdExist(id)) {
            // log warning
            return HttpStatus.NOT_FOUND;
        }
        // update status and detail (in body) with id
        requestObjectService.updateStatusAndDetailById(id, statusUpdate.status, statusUpdate.detail);
        // if it fails respond with Bad Request

        return HttpStatus.NO_CONTENT;
    }

    // GET status (4) -- gets the status of a request by id
    @GetMapping("/status/{id}")
    @ResponseBody
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

    class ThirdPartyServiceObject {
        public String callbackUrl;
        public String body;

        public ThirdPartyServiceObject(String callbackUrl, String body) {
            this.callbackUrl = callbackUrl;
            this.body = body;
        }
    }

    private void buildAndSendThirdPartyServiceRequest() throws ExecutionException, InterruptedException {
        // examples from https://openjdk.java.net/groups/net/httpclient/intro.html

        // use the client to send the request
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create(exampleServiceUrl))
                .header("accept", "application/json")
                .build();
        var responseFuture = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // We can do other things here while the request is in-flight

        // This blocks until the request is complete
        var response = responseFuture.get();

        // the response:
        System.out.println(response.body().getBytes(StandardCharsets.UTF_8));
    }


    // Extra Endpoints
    // same exact request as (1) but with a raw string
    @PostMapping("/request/rawString")
    @ResponseBody
    public String postRequestRawString(@RequestBody String body) {

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

    // GET all RequestObjects
    @GetMapping
    @ResponseBody
    public List<RequestObject> getRequestObjectList() {
        return requestObjectService.getRequestObjects();
    }
}
