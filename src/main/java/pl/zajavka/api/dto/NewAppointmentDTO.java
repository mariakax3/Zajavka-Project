package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewAppointmentDTO {

    private String dateTime;
    private String patientComment;
    private String doctorPesel;

    public static NewAppointmentDTO buildDefault() {
        return NewAppointmentDTO.builder()
                .dateTime("2023-08-30 13:45")
                .patientComment("Nawracające bóle brzucha")
                .doctorPesel("78041156534")
                .build();
    }
}
