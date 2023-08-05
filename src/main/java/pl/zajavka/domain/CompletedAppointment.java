package pl.zajavka.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString(of = {"completedAppointmentId", "doctorComment", "physicalExamination", "medicine"})
@EqualsAndHashCode(of = "completedAppointmentId")
public class CompletedAppointment {

    Integer completedAppointmentId;
    String doctorComment;
    BigDecimal cost;
    PlannedAppointment plannedAppointment;
    PhysicalExamination physicalExamination;
    Medicine medicine;
}
