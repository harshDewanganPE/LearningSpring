package com.harsh.sample.api.response;

 import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
 import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
 import lombok.Getter;
 import lombok.Setter;

 import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PincodeResponse {

    @JsonProperty("Message")
    private String message;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("PostOffice")
    private List<PostOffice> postOffice;


    @Getter
    @Setter
    public static class PostOffice{
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Description")
        private Object description;
        @JsonProperty("BranchType")
        private String branchType;
        @JsonProperty("DeliveryStatus")
        private String deliveryStatus;
        @JsonProperty("Circle")
        private String circle;
        @JsonProperty("District")
        private String district;
        @JsonProperty("Division")
        private String division;
        @JsonProperty("Region")
        private String region;
        @JsonProperty("Block")
        private String block;
        @JsonProperty("State")
        private String state;
        @JsonProperty("Country")
        private String country;
        @JsonProperty("Pincode")
        private String pincode;
    }

}

