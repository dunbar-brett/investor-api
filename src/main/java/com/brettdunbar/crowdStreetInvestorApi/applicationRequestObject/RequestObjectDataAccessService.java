package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import com.brettdunbar.crowdStreetInvestorApi.dataStore.MockRequestObjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// instead this should normally be repository for a Database
@Repository
public class RequestObjectDataAccessService {

    // can use dependency injection to use a DB instead of a mock store
    private final MockRequestObjectStore mockRequestObjectStore;

    @Autowired
    public RequestObjectDataAccessService(MockRequestObjectStore mockRequestObjectStore) {
        this.mockRequestObjectStore = mockRequestObjectStore;
    }

    public RequestObject getRequestObjectById(int id) {
        List<RequestObject> requestObjectList = getRequestObjects();
        RequestObject response = requestObjectList.stream()
                .filter(requestObject -> id == requestObject.getRequestId())
                .findAny()
                .orElse(null);
        return response;
    }

    // this should realistically return a boolean or something in case there was an issue
    public void updateRequestObjectStatus(int id, String status) {
        // this would realistically involve more DB work with SQL
        RequestObject requestObject = getRequestObjectById(id);
        requestObject.setStatus(status);
        requestObject.setUpdatedTimeStamp(new Date());

        // more error handling around this with loggers
    }

    // this should realistically return a boolean or something in case there was an issue
    public void updateRequestObjectStatusAndDetail(int id, String status, String detail) {
        // this would realistically be a single SQL script to update both
        RequestObject requestObject = getRequestObjectById(id);
        requestObject.setStatus(status);
        requestObject.setDetail(detail);
        requestObject.setUpdatedTimeStamp(new Date());


        // more error handling around this with loggers
    }

    public RequestObject addRequestObject(RequestObject requestObject) {
        mockRequestObjectStore.addRequestObject(requestObject);
        return requestObject;
    }

    public List<RequestObject> getRequestObjects() {
        return mockRequestObjectStore.getRequestObjectList();
    }
}
