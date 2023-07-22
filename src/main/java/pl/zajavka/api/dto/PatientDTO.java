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

    private String name;
    private String surname;
    private LocalDate birthdate;
    private String pesel;
}
