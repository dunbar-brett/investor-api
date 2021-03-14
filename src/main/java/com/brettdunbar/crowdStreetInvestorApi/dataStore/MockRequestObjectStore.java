package com.brettdunbar.crowdStreetInvestorApi.dataStore;

import com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject.RequestObject;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MockRequestObjectStore {

    private static final List<RequestObject> REQUEST_OBJECT_LIST = new ArrayList<>();

    static {
        REQUEST_OBJECT_LIST
            .add(new RequestObject(
                0,
                "some random text that should be json",
                "PROCESSED",
                "something details",
                new GregorianCalendar(2021, Calendar.MARCH, 10, 5, 20)
                        .getTime(),
                new Date()
            ));
        REQUEST_OBJECT_LIST
            .add(new RequestObject(
                    1,
                    "even more random text that should be json",
                    "COMPLETED",
                    "something details",
                    new GregorianCalendar(2021, Calendar.MARCH, 9, 8, 45)
                            .getTime(),
                    new Date()
            ));
        REQUEST_OBJECT_LIST
            .add(new RequestObject(
                    2,
                    "just some more random text that should be json",
                    "ERROR",
                    "something details",
                    new GregorianCalendar(2021, Calendar.MARCH, 11, 3, 03)
                            .getTime(),
                    new Date()
            ));
    }

    public List<RequestObject> getRequestObjectList() {
        return REQUEST_OBJECT_LIST;
    }
}
