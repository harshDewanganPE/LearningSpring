package com.harsh.sample.service;

import com.harsh.sample.api.response.PincodeResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PincodeService {

//    private static final String url = "https://api.postalpincode.in/pincode/CITY";
    @Value("$(URL)")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public String body = "{\n   \"name\": \"Apple MacBook Pro 16\",\n   \"data\": {\n      \"year\": 2084,\n      \"price\": 1849.99,\n      \"CPU model\": \"Intel Core i9\",\n      \"Hard disk size\": \"1 TB\"\n   }\n}";

    HttpEntity<String> httpEntity = new HttpEntity<>(body);

    public List<PincodeResponse> getPostalInfo(String city){
        String finalApi = url.replace("CITY", city);
        ResponseEntity<PincodeResponse[]> res = restTemplate.exchange(finalApi,   HttpMethod.GET , null, PincodeResponse[].class );
        return res.getBody() != null ? Arrays.asList(res.getBody()) : Collections.emptyList();
    }

}
