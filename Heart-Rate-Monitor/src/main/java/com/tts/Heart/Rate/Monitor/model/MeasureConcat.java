package com.tts.Heart.Rate.Monitor.model;

//Concatenate two values for blood pressure
public class MeasureConcat {

        public static void main() {
            int systolic = 130;
            int diastolic =90;
//            String systolic_string = Integer.parse(systolic);
            String systolic_string  = String.valueOf(systolic);
            String diastolic_string = String.valueOf(diastolic);
            String bloodpressure = systolic_string.concat("/").concat(diastolic_string);
            System.out.println(bloodpressure);

        }
    }

