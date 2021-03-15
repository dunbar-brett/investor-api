package com.brettdunbar.crowdStreetInvestorApi.dataObjects;

public class ThirdPartyServiceObject {
    public String callbackUrl;
    public String body;

    public ThirdPartyServiceObject(String callbackUrl, String body) {
        this.callbackUrl = callbackUrl;
        this.body = body;
    }
}
