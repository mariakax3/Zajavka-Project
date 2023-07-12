package pl.zajavka.domain;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@ToString(of = {"patientId", "name", "surname", "birthdate", "pesel"})
@EqualsAndHashCode(of = "pesel")
public class Patient {

    Integer patientId;
    String name;
    String surname;
    OffsetDateTime birthdate;
    String pesel;
    Set<PlannedAppointment> appointments;
}
