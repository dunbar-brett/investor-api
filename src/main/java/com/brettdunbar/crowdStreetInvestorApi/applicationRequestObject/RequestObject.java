package com.brettdunbar.crowdStreetInvestorApi.applicationRequestObject;
import java.util.Date;

// This would be renamed to something more descriptive
public class RequestObject {
    private int requestId;
    private String body;
    private String status; // TODO: status should likely be an enum
    private String detail;
    private Date createdTimeStamp;
    private Date lastUpdatedTimeStamp;

    public RequestObject(int requestId, // this is an int for testng simplicity, should be something like UUID
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // TODO: Maybe implement an equals method

}
