package com.brettdunbar.crowdStreetInvestorApi.dataObjects;

public class ResponseStatusUpdate {
    private String status;
    private String detail;
    private String body;

    public ResponseStatusUpdate(String status, String detail, String body) {
        this.status = status;
        this.detail = detail;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
