package pl.zajavka.domain;

import lombok.*;
import pl.zajavka.infrastructure.database.entity.PlannedAppointmentEntity;

import java.util.Set;

@With
@Value
@Builder
@ToString(of = {"doctorId", "name", "surname", "specialization", "pesel"})
@EqualsAndHashCode(of = "pesel")
public class Doctor {

    Integer doctorId;
    String name;
    String surname;
    String specialization;
    String pesel;
    Set<PlannedAppointment> appointments;
}
