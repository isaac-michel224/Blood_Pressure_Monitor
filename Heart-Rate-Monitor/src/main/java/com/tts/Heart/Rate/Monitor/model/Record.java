package com.tts.Heart.Rate.Monitor.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Record {

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;
//Concarenate values for blood pressure
    @NumberFormat
    @Length(max=3)
    private int systolic;

    @NumberFormat
    @Length(max=3)
    private int diastolic;

    @CreationTimestamp
    private Date recordedAt;

    @ManyToOne
    @JoinColumn(name="enduser_id")
    private EndUser enduser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id")
    private Patient patient;

//    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "blood pressure reading",
//    joinColumns = @JoinColumn(name = "systolic"),
//    inverseJoinColumns = @JoinColumn(name = "diastolic"))


//private List<Record> readings;


  //  Concatenate Systolic and Diastolic values
//    public static class MeasureConcat{
//        public static void main(byte[] args) {
//            int systolic = 130;
//            int diastolic =90;
////            String systolic_string = Integer.parse(systolic);
//            String systolic_string  = String.valueOf(systolic);
//            String diastolic_string = String.valueOf(diastolic);
//            String bloodpressure = systolic_string.concat(diastolic_string);
//            System.out.println(bloodpressure);
//
//        }
//    }


    //Presenting results once user enters in blood pressure values
    public void results() {
//        this.systolic = systolic;
//        this.diastolic = diastolic;
//        this.recordedAt = RecordedAt;

        if (systolic <= 120) {
            System.out.println("TOO NORMAL!");
        } else if (systolic > 120) {
            System.out.println("Warning!!! ");
        } else{
            System.out.println("Please contact your medical provider for more info. ");
        }
        if(diastolic < 80) {
            System.out.println("Normal!!!");
        } else if ( diastolic >= 90) {
            System.out.println("Stage 1 Hypertension");
        }  else {
            System.out.println("Please contact your medical provider for more info. ");
        }
    }


    //List Past Blood Pressure entries



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return systolic == record.systolic && diastolic == record.diastolic && recordedAt.equals(record.recordedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systolic, diastolic, recordedAt);
    }



}

