package pl.zajavka.domain;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@With
@Value
@Builder
@ToString(of = {"doctorAvailabilityId", "date", "hour", "doctor"})
@EqualsAndHashCode(of = "doctorAvailabilityId")
public class DoctorAvailability {

    Integer doctorAvailabilityId;
    LocalDate date;
    Time hour;
    Doctor doctor;
}
