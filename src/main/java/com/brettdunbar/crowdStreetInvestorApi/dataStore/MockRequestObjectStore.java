package com.brettdunbar.crowdStreetInvestorApi.dataStore;

import com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject.RequestObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class MockRequestObjectStore {

    private static final List<RequestObject> REQUEST_OBJECT_LIST = new ArrayList<>();

    static {
        REQUEST_OBJECT_LIST
            .add(new RequestObject(
                UUID.randomUUID(),
                "some random text that should be json",
                "PROCESSED",
                "something details",
                new Date(),
                new Date()
            ));
        REQUEST_OBJECT_LIST
            .add(new RequestObject(
                    UUID.randomUUID(),
                    "some random text that should be json",
                    "PROCESSED",
                    "something details",
                    new Date(),
                    new Date()
            ));
        REQUEST_OBJECT_LIST
            .add(new RequestObject(
                    UUID.randomUUID(),
                    "some random text that should be json",
                    "PROCESSED",
                    "something details",
                    new Date(),
                    new Date()
            ));
    }

    public List<RequestObject> getRequestObjectList() {
        return REQUEST_OBJECT_LIST;
    }
}
