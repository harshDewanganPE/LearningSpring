package com.harsh.sample.api.response;


 import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
 import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1

 import java.util.ArrayList;


public class PincodeResponse {

    @JsonProperty("Message")
    public String message;
    @JsonProperty("Status")
    public String status;
    @JsonProperty("PostOffice")
    public ArrayList<PostOffice> postOffice;

    public class PostOffice{
        @JsonProperty("Name")
        public String name;
        @JsonProperty("Description")
        public Object description;
        @JsonProperty("BranchType")
        public String branchType;
        @JsonProperty("DeliveryStatus")
        public String deliveryStatus;
        @JsonProperty("Circle")
        public String circle;
        @JsonProperty("District")
        public String district;
        @JsonProperty("Division")
        public String division;
        @JsonProperty("Region")
        public String region;
        @JsonProperty("Block")
        public String block;
        @JsonProperty("State")
        public String state;
        @JsonProperty("Country")
        public String country;
        @JsonProperty("Pincode")
        public String pincode;
    }

}

