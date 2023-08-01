package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Integer patientId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String pesel;

    public static PatientDTO buildDefault() {
        return PatientDTO.builder()
                .name("Stefan")
                .surname("Chorobliwy")
                .birthdate(LocalDate.of(1987, 10, 12))
                .pesel("87101298078")
                .build();
    }
}
