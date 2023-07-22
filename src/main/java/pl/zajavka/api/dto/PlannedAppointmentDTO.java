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

    private String dateTime;
    private String patientComment;
    private PatientDTO patient;
    private DoctorDTO doctor;
}
