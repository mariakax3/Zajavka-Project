package pl.zajavka.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@With
@Value
@Builder
@ToString(of = {"completedAppointmentId", "doctorComment", "physicalExaminations", "medicines"})
@EqualsAndHashCode(of = "completedAppointmentId")
public class CompletedAppointment {

    Integer completedAppointmentId;
    String doctorComment;
    BigDecimal cost;
    PlannedAppointment plannedAppointment;
    List<PhysicalExamination> physicalExaminations;
    List<Medicine> medicines;
}
