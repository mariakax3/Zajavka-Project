package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalExaminationDTO {

    private Integer physicalExaminationId;
    private String name;
    private String result;
    private CompletedAppointmentDTO completedAppointment;

    public static PhysicalExaminationDTO buildDefault() {
        return PhysicalExaminationDTO.builder()
                .name("USG żołądka")
                .result("Brak zmian")
                .build();
    }
}
