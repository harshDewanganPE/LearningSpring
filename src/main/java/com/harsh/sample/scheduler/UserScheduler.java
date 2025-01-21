package com.harsh.sample.scheduler;

import com.harsh.sample.api.response.PincodeResponse;
import com.harsh.sample.service.PincodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    PincodeService pincodeService;

    @Scheduled( cron = "0 0 9 * * SUN")
    public void scheduledMeth(){

        List<PincodeResponse> res = pincodeService.getPostalInfo("492010");

        log.info(res.toString());

    }



}
