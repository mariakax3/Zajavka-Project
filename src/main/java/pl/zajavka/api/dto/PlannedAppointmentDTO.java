package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlannedAppointmentDTO {

    private Integer plannedAppointmentId;
    private String dateTime;
    private String patientComment;
    private PatientDTO patient;
    private DoctorDTO doctor;

    public static PlannedAppointmentDTO buildDefault() {
        return PlannedAppointmentDTO.builder()
                .dateTime("2023-07-07 13:45")
                .patientComment("Nawracające bóle brzucha")
                .patient(PatientDTO.buildDefault())
                .doctor(DoctorDTO.buildDefault())
                .build();
    }
}
