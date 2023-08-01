package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedAppointmentDTO {

    private String doctorComment;
    private BigDecimal cost;
    private PlannedAppointmentDTO plannedAppointment;
    private List<PhysicalExaminationDTO> physicalExaminations;
    private List<MedicineDTO> medicines;

    public static CompletedAppointmentDTO buildDefault() {
        return CompletedAppointmentDTO.builder()
                .doctorComment("Do obserwacji")
                .cost(BigDecimal.valueOf(250))
                .plannedAppointment(PlannedAppointmentDTO.buildDefault())
                .physicalExaminations(List.of(PhysicalExaminationDTO.buildDefault()))
                .medicines(List.of(MedicineDTO.buildDefault()))
                .build();
    }
}
