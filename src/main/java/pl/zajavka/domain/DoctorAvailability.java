package pl.zajavka.domain;

import lombok.*;

import java.sql.Time;

@With
@Value
@Builder
@ToString(of = {"doctorAvailabilityId", "month", "day", "hour", "doctor"})
@EqualsAndHashCode(of = "doctorAvailabilityId")
public class DoctorAvailability {

    Integer doctorAvailabilityId;
    Integer year;
    Integer month;
    Integer day;
    Time hour;
    Doctor doctor;
}
