package com.tts.Heart.Rate.Monitor.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Long id;
    private String patient;
    private Integer age;

//    @OneToOne(cascade= CascadeType.ALL)
//    @JoinColumn(name = "enduser_id", referencedColumnName="id")
    private String enduser;

    public String address;
    @Length(max = 1000, message = "Tell us about your medical history")
    public String healthBio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id.equals(patient.id) && patient.equals(patient.patient) && age.equals(patient.age) && enduser.equals(patient.enduser) && address.equals(patient.address) && healthBio.equals(patient.healthBio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, age, enduser, address, healthBio);
    }



}

//    @Override
//    public String toString() {
//        return "MovieRental{" +
//                "id=" + id +
//                ", Patient'" + Patient + '\'' +
//                ", Age='" + Age + '\'' +
//                ", enduser='" + enduser + '\'' +
//                ", Address='" + Address + '\'' +
//                ", HealthBio=" + HealthBio + '\'' +
//                '}';
//    }