package com.brettdunbar.crowdStreetInvestorApi.test;

import com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject.RequestObject;
import com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject.RequestObjectService;
import com.brettdunbar.crowdStreetInvestorApi.dataObjects.JsonBodyRequest;
import com.brettdunbar.crowdStreetInvestorApi.dataObjects.ThirdPartyServiceObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
public class TestingController {
    private final RequestObjectService requestObjectService;
    private int mockIdStart = 999;

    @Autowired
    public TestingController(RequestObjectService requestObjectService) {
        this.requestObjectService = requestObjectService;
    }

    // implement a test that takes a var for delay(in seconds) for processing status
    // max is 5 minutes
//    @Scheduled(fixedDelay = )
    @GetMapping("/hello/{timeOutSeconds}")
    public Callable<String> greeting(@PathVariable int timeOutSeconds) {
        if (timeOutSeconds > 300) {
            return null;
        }
        int milliseconds = timeOutSeconds * 1000;
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(milliseconds); //this will cause a timeout
                System.out.println("hello in :" + timeOutSeconds + " second(s)");
                return "foobar";
            }
        };
    }

    // same exact call as (1) Get Request but it calls the delayed endpoint to
    // call (2) Post Callback after delayed time
    @PostMapping("/request")
    public String postRequest(@RequestBody JsonBodyRequest requestBody) {
        // keeping track of id to avoid collision, would be better if this was a UUID
        mockIdStart++;

        // mock async request to exampleServiceUrl
        try {
            // build json object to send to service
            ThirdPartyServiceObject serviceObject = new ThirdPartyServiceObject(
                    "/callback/" + mockIdStart,
                    requestBody.body
            );
            buildAndSendThirdPartyServiceRequest(serviceObject);
        } catch (ExecutionException e) {
            e.printStackTrace();
            // log response null error/warning
            return "ERROR";
        } catch (InterruptedException e) {
            e.printStackTrace();
            // log response null error/warning
            return "ERROR";
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a requestObject entry
        RequestObject requestObject = new RequestObject(
                mockIdStart,
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


    @GetMapping("/mockDelay/{timeOutSeconds}")
    public Callable<String> mockThirdPartyServiceDelayCall(@PathVariable int timeOutSeconds) {
        if (timeOutSeconds > 300) {
            return null;
        }
        int milliseconds = timeOutSeconds * 1000;
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(milliseconds); //this will cause a timeout
                System.out.println("hello in :" + timeOutSeconds + " second(s)");
                return "foobar";
            }
        };
    }

    private void buildAndSendThirdPartyServiceRequest(ThirdPartyServiceObject serviceObject)
            throws ExecutionException, InterruptedException, IOException {
        // examples from https://openjdk.java.net/groups/net/httpclient/intro.html

        // use the client to send the request
        // create a client
        var url = "http://localhost:8080/request/";
        var client = HttpClient.newHttpClient();
        var objectMapper = new ObjectMapper();
        var requestBody = objectMapper
                .writeValueAsString(serviceObject);

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create(url))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        var response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // the response:
        System.out.println(response.body().getBytes(StandardCharsets.UTF_8));
    }
}
