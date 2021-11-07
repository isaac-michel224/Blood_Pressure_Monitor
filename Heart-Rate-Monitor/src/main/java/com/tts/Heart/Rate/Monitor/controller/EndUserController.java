package com.tts.Heart.Rate.Monitor.controller;

import com.tts.Heart.Rate.Monitor.model.EndUser;
import com.tts.Heart.Rate.Monitor.service.EndUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EndUserController {

    private EndUserService enduserService;



    public EndUserController(EndUserService enduserService) {
        this.enduserService = enduserService;


    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }





    @GetMapping("/endusers/{username}")
    public String getEndUser(@PathVariable String LastName, Model model) {
        EndUser enduser = enduserService.findByLastName(LastName);
        EndUser patients = enduserService.findByLastName(LastName);

        EndUser loggedinEndUser = enduserService.getLoggedInEndUser();

        boolean isSelfPage = loggedinEndUser.getLastName().equals(LastName);


        return "UserProfile";
    }

    @GetMapping("/users")
    public String getEndUsers(@RequestParam(value="filter", required = false) String filter, Model model) {
        List<EndUser> endusers = new ArrayList<>();

        EndUser loggedinEndUser = enduserService.getLoggedInEndUser();

        return "LastNames";
    }
    }

    // https://www.w3schools.in/java-tutorial/decision-making/else-if/
// https://docs.oracle.com/javase/8/docs/
// https://github.com/topics/blood-pressure?l=java




