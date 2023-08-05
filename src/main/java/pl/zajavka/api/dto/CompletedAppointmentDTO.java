package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedAppointmentDTO {

    private String doctorComment;
    private BigDecimal cost;
    private PlannedAppointmentDTO plannedAppointment;
    private PhysicalExaminationDTO physicalExamination;
    private MedicineDTO medicine;

    public static CompletedAppointmentDTO buildDefault() {
        return CompletedAppointmentDTO.builder()
                .doctorComment("Do kontroli za rok")
                .cost(BigDecimal.valueOf(250))
                .plannedAppointment(PlannedAppointmentDTO.buildDefault())
                .physicalExamination(PhysicalExaminationDTO.buildDefault())
                .medicine(MedicineDTO.buildDefault())
                .build();
    }
}
