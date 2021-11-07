package com.tts.Heart.Rate.Monitor.repository;

import com.tts.Heart.Rate.Monitor.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByPatient(String patient);



}

 /*
    https://docs.oracle.com/javase/8/docs/api/
    https://techtalentsouth.slides.com/techtalentsouth/java-introduction-ci2020?token=2lk9xi4y#/31


    //https://github.com/LionelBeato/techtalenttwitter/tree/main/src/main/java/com/tss/techtalenttwitter/model
    // https://www.mayoclinic.org/diseases-conditions/high-blood-pressure/in-depth/blood-pressure/art-20050982
    //https://github.com/topics/blood-pressure?l=java

     */