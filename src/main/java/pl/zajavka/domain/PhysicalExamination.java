package pl.zajavka.domain;

import lombok.*;

@With
@Value
@Builder
@ToString(of = {"physicalExaminationId", "name", "result"})
@EqualsAndHashCode(of = "physicalExaminationId")
public class PhysicalExamination {

    Integer physicalExaminationId;
    String name;
    String result;
    CompletedAppointment completedAppointment;
}
