package pl.zajavka.domain;

import lombok.*;

@With
@Value
@Builder
@ToString(of = {"medicineId", "name", "dosage"})
@EqualsAndHashCode(of = "medicineId")
public class Medicine {

    Integer medicineId;
    String name;
    String dosage;
    CompletedAppointment completedAppointment;
}
