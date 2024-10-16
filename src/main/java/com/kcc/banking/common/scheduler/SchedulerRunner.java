package com.kcc.banking.common.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class SchedulerRunner {

    @Scheduled(fixedDelay = 3000)
    public void testScheduler() {
        log.info("Starting test scheduler");
    }
}
