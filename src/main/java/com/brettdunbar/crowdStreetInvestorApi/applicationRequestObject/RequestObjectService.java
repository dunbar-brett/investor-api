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

    public RequestObject addRequestObject(RequestObject requestObject) {
        // check if id already exists
        List<RequestObject> requestObjectList = getRequestObjects();

        if (requestObjectList.contains(requestObject)) {
            return null;
        } else {
            requestObjectDataAccessService.addRequestObject(requestObject);
        }

        return requestObject;
    }


    public void updateStatusById(int id, String status) {
        requestObjectDataAccessService.updateRequestObjectStatus(id, status);
    }

    public void updateStatusAndDetailById(int id, String status, String detail) {
        requestObjectDataAccessService.updateRequestObjectStatusAndDetail(id, status, detail);
    }

    public boolean doesRequestIdExist(int id) {
        return getRequestObjectById(id) != null;
    }

    public RequestObject getRequestObjectById(int id) {
        return requestObjectDataAccessService.getRequestObjectById(id);
    }

    public List<RequestObject> getRequestObjects() {
       return requestObjectDataAccessService.getRequestObjects();
    }

}