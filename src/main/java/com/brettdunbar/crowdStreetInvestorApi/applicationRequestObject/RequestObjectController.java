package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestObjectController {

    private final RequestObjectService requestObjectService;

    @Autowired
    public RequestObjectController(RequestObjectService requestObjectService) {
        this.requestObjectService = requestObjectService;
    }

    @GetMapping
    public List<RequestObject> getRequestObjectList() {
        return requestObjectService.getRequestObjects();
    }
}
