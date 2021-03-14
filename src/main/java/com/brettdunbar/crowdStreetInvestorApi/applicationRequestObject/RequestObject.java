package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;
import java.util.Date;
import java.util.Objects;

// This would be renamed to something more descriptive
public class RequestObject {
    public int requestId;
    public String body;
    public String status; // TODO: status should likely be an enum
    public String detail;
    public Date createdTimeStamp;
    public Date lastUpdatedTimeStamp;

    public RequestObject(int requestId,
                         String body,
                         String status,
                         String detail,
                         Date createdTimeStamp,
                         Date lastUpdatedTimeStamp) {
        this.requestId = requestId;
        this.body = body;
        this.status = status;
        this.detail = detail;
        this.createdTimeStamp = createdTimeStamp;
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }

    // not entirely sure if this is right.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestObject that = (RequestObject) o;
        return requestId == that.requestId && body.equals(that.body) && status.equals(that.status) && detail.equals(that.detail) && createdTimeStamp.equals(that.createdTimeStamp) && lastUpdatedTimeStamp.equals(that.lastUpdatedTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, body, status, detail, createdTimeStamp, lastUpdatedTimeStamp);
    }
}
