package com.harsh.sample.service;

import com.harsh.sample.api.response.PincodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PincodeService {

    private static final String url = "https://api.postalpincode.in/pincode/CITY";

    @Autowired
    private RestTemplate restTemplate;

    public PincodeResponse getPostalInfo(String city){

        String finalApi = url.replace("CITY", city);

        ResponseEntity<PincodeResponse> res = restTemplate.exchange(finalApi, HttpMethod.GET , null, PincodeResponse.class );

        PincodeResponse body = res.getBody();
        return body;

    }

}
