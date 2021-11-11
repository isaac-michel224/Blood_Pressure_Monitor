package com.tts.Heart.Rate.Monitor.service;

import com.tts.Heart.Rate.Monitor.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;


    //insert date
    //check to see if date is in database
    //if it is, return instance of record



    public Record checkIfCurrentDate(int dayNumber) {

        Record record = new Record();
        record.setRecordedAt(new Date(1970, Calendar.FEBRUARY, 1));

        List<Record> holdRecords = new ArrayList<>();

        recordRepository.findAll()
                .forEach(holdRecords::add);

        holdRecords.forEach(record1 -> System.out.println(record1.getRecordedAt()));

        return holdRecords.stream()
                .filter(r -> r.getRecordedAt().getDate() == dayNumber)
                .findAny()
                .orElse(record);


    }
}
