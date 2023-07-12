package pl.zajavka.domain;


import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@ToString(of = {"plannedAppointmentId", "dateTime", "patientComment"})
@EqualsAndHashCode(of = "plannedAppointmentId")
public class PlannedAppointment {

    Integer plannedAppointmentId;
    OffsetDateTime dateTime;
    String patientComment;
    Patient patient;
    Doctor doctor;

}
