package com.brettdunbar.crowdStreetInvestorApi.dataObjects;

public class RequestStatusUpdate {
    private String status;
    private String detail;

    public RequestStatusUpdate(String status, String detail, String body) {
        this.status = status;
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
