package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {

    private Integer medicineId;
    private String name;
    private String dosage;
    private CompletedAppointmentDTO completedAppointment;

    public static MedicineDTO buildDefault() {
        return MedicineDTO.builder()
                .name("Pantopraz 20 mg")
                .dosage("1 tabletka na czczo")
                .build();
    }
}
