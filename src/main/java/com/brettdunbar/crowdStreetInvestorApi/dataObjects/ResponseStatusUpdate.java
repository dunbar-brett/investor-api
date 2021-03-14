package com.brettdunbar.crowdStreetInvestorApi.dataObjects;

public class ResponseStatusUpdate {
    public String status;
    public String detail;
    public String body;

    public ResponseStatusUpdate(String status, String detail, String body) {
        this.status = status;
        this.detail = detail;
        this.body = body;
    }
}
