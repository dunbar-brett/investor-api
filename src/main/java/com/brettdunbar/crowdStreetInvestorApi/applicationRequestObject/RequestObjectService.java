package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// business logic happens here
@Service
public class RequestObjectService {

    private final RequestObjectDataAccessService requestObjectDataAccessService;

    @Autowired
    public RequestObjectService(RequestObjectDataAccessService requestObjectDataAccessService) {
        this.requestObjectDataAccessService = requestObjectDataAccessService;
    }

    List<RequestObject> getRequestObjects() {
       return requestObjectDataAccessService.getRequestObjects();
    }

}