package com.tts.Heart.Rate.Monitor.repository;
import com.tts.Heart.Rate.Monitor.model.Record;
import com.tts.Heart.Rate.Monitor.model.EndUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long>{

    List<Record> findAllByOrderByRecordedAtDesc();
//    List<Record> findAllByEnduserByRecordedAtDesc(EndUser enduser);
//    List<Record> findAllByEnduserInOrderByRecordedAtDesc(List<EndUser> endusers);
   Record findByRecordedAt(Date recordedAt);
}
