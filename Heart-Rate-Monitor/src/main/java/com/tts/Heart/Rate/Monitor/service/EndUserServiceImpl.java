package com.tts.Heart.Rate.Monitor.service;

import com.tts.Heart.Rate.Monitor.model.EndUser;
import com.tts.Heart.Rate.Monitor.model.Patient;
import com.tts.Heart.Rate.Monitor.repository.EndUserRepository;
import com.tts.Heart.Rate.Monitor.repository.PatientRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class EndUserServiceImpl implements EndUserService {

    private EndUserRepository endUserRepository;
    private PatientRepository patientRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    public EndUserServiceImpl(EndUserRepository endUserRepository,
                              PatientRepository patientRepository,
                              PasswordEncoder bCryptPasswordEncoder) {
        this.endUserRepository = endUserRepository;
        this.patientRepository = patientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }


    @Override
    public EndUser findByLastName(String LastName) {
        return endUserRepository.findByLastName(LastName);
    }

    @Override
    public List<EndUser> findAll() {
        return (List<EndUser>) endUserRepository.findAll();
    }


    @Override
    public void save(EndUser enduser) {
        endUserRepository.save(enduser);
    }


    @Override
    public EndUser saveNewEndUser(EndUser enduser) {
        enduser.setPassword(bCryptPasswordEncoder.encode(enduser.getPassword()));
//        enduser.setActive(1);
        Patient enduserPatient = patientRepository.findByPatient("ENDUSER");
//        enduser.setPatient(enduserPatient);
        return endUserRepository.save(enduser);
    }

    @Override
    public EndUser getLoggedInEndUser() {

        String loggedinUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return findByLastName(loggedinUsername);
    }
}
