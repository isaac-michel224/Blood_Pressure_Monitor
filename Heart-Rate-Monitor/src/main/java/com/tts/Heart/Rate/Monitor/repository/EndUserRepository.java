package com.tts.Heart.Rate.Monitor.repository;

import com.tts.Heart.Rate.Monitor.model.EndUser;
import org.springframework.data.repository.CrudRepository;

public interface EndUserRepository extends CrudRepository<EndUser, Long> {

    EndUser findByLastName(String lastName);
}
