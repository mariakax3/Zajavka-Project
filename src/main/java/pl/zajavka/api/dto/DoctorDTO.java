package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Integer doctorId;
    private String name;
    private String surname;
    private String specialization;
    private String pesel;

    public static DoctorDTO buildDefault() {
        return DoctorDTO.builder()
                .name("Adam")
                .surname("Skalpel")
                .specialization("Gastrologia")
                .pesel("78041156534")
                .build();
    }
}
