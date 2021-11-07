package com.tts.Heart.Rate.Monitor.service;

import com.tts.Heart.Rate.Monitor.model.EndUser;

import java.util.List;

public interface EndUserService {

    EndUser findByLastName(String LastName);
    List<EndUser> findAll();
    void save(EndUser enduser);
    EndUser saveNewEndUser(EndUser enduser);
    EndUser getLoggedInEndUser();
}
