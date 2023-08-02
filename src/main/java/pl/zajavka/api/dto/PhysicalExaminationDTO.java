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
                .name("Komputerowe badanie wzroku")
                .result("Pogorszenie o 0.25 D na obu oczach")
                .build();
    }
}
