package com.tts.Heart.Rate.Monitor.controller;

import com.tts.Heart.Rate.Monitor.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordControllerTest {

    @Autowired
    RecordService recordService ;

    @Test
    public void test() {
        System.out.println(recordService.checkIfCurrentDate(1).getRecordedAt());
    }

}