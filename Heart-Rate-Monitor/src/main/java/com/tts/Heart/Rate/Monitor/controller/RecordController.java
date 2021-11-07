package com.tts.Heart.Rate.Monitor.controller;
import com.tts.Heart.Rate.Monitor.model.Record;

import com.tts.Heart.Rate.Monitor.repository.RecordRepository;
import com.tts.Heart.Rate.Monitor.service.EndUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecordController {

    //Create New Blood Pressure Form
//    @GetMapping("/blood-pressure-monitor/new")

    private EndUserService enduserService;
private RecordRepository recordRepository;
    public RecordController(EndUserService enduserService, RecordRepository recordRepository) {
        this.enduserService = enduserService;
        this.recordRepository = recordRepository;
    }

//Sends to Blood Pressure Info Page
    @GetMapping("/info")
    public String BloodPressureInfo() {
        return "info";
    }

    @GetMapping("/all")
    public String getAllRecords(Model model) {
        model.addAttribute("records", recordRepository.findAll());
        return "all-records";
   }


// Insert Blood Pressure values from test
    @PostMapping("/blood-pressure-monitor/insert")
    public String BloodPressureForm(Model model) {
        model.addAttribute("blood pressure", new Record());
        return "recent BP reading";
    }


    //Save Blood Pressure values into system (on calender)
    @PostMapping("/blood-pressure-monitor/readings")
    public String submitBloodPressure(Model model, Record record) {
    recordRepository.save(record);
        return "save";
    }

    //Get Back Readings of Blood Pressure and what it Means

}
