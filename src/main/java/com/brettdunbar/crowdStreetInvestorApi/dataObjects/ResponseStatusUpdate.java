package com.brettdunbar.crowdStreetInvestorApi.dataObjects;

public class ResponseStatusUpdate {
    private String Status;
    private String Detail;

    public ResponseStatusUpdate(String status, String detail) {
        Status = status;
        Detail = detail;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
