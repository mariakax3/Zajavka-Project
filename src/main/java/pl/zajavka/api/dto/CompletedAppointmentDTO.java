package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zajavka.domain.PlannedAppointment;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedAppointmentDTO {

    String doctorComment;
    BigDecimal cost;
    PlannedAppointment plannedAppointment;
    List<PhysicalExaminationDTO> physicalExaminations;
    List<MedicineDTO> medicines;
}
