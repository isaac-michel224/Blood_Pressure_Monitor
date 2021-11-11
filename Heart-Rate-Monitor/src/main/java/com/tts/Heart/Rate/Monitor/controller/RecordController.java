package com.tts.Heart.Rate.Monitor.controller;
import com.tts.Heart.Rate.Monitor.model.Record;

import com.tts.Heart.Rate.Monitor.repository.RecordRepository;
import com.tts.Heart.Rate.Monitor.service.EndUserService;
import com.tts.Heart.Rate.Monitor.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RecordController {

    //Create New Blood Pressure Form
//    @GetMapping("/blood-pressure-monitor/new")


    @ModelAttribute(name="records")
    public List<Record> records() {
        return (List<Record>) recordRepository.findAll();
    }

    @Autowired
    RecordService recordService;

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
    @PostMapping("/insert")
    public String BloodPressureForm(Model model) {
        model.addAttribute("blood pressure", new Record());
        return "recent BP reading";
    }


    //Save Blood Pressure values into system (on calendar)
    @PostMapping("/readings")
    public String submitBloodPressure(Model model, Record record) {
    recordRepository.save(record);
        return "save";
    }

    //Get Back Readings of Blood Pressure and what it Means


    //Request Mapping section

    @RequestMapping("/info")
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("info");
        return mv;
    }


    @RequestMapping("/all")
    public ModelAndView allRecords() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("all-records");
        mv.addObject("service", recordService);
        return mv;
    }


 @RequestMapping("/insert")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blood-pressure/insert");
        return mv;
 }

    @RequestMapping("/readings")
    public ModelAndView submit() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/readings");
        return mv;
    }


}
