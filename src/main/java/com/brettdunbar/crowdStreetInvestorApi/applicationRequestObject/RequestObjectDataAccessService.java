package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;

import com.brettdunbar.crowdStreetInvestorApi.dataStore.MockRequestObjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestObjectDataAccessService {

    // can use dependecy inject to use a DB instead of a mock store
    private final MockRequestObjectStore mockRequestObjectStore;

    @Autowired
    public RequestObjectDataAccessService(MockRequestObjectStore mockRequestObjectStore) {
        this.mockRequestObjectStore = mockRequestObjectStore;
    }

    public RequestObject getRequestObjectById(int id) {
        List<RequestObject> requestObjectList = getRequestObjects();
        RequestObject response = requestObjectList.stream()
                .filter(requestObject -> id == requestObject.requestId)
                .findAny()
                .orElse(null);
        return response;
    }

    public RequestObject addRequestObject(RequestObject requestObject) {
        mockRequestObjectStore.addRequestObject(requestObject);
        return requestObject;
    }

    public List<RequestObject> getRequestObjects() {
        return mockRequestObjectStore.getRequestObjectList();
    }
}
