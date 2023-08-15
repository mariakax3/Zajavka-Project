package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zajavka.infrastructure.database.entity.DoctorEntity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailabilityDTO {

    private Integer doctorAvailabilityId;
    private LocalDate date;
    private Time hour;
    private DoctorEntity doctor;
    private boolean checked;

    @Override
    public String toString() {
        return String.format("%s, godz. %s", date, hour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAvailabilityDTO that = (DoctorAvailabilityDTO) o;
        return Objects.equals(date, that.date) && Objects.equals(hour, that.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, hour);
    }

    public static DoctorAvailabilityDTO copyDTO(DoctorAvailabilityDTO dto) {
        DoctorAvailabilityDTO newDTO = new DoctorAvailabilityDTO();
        newDTO.setDate(dto.getDate());
        return newDTO;
    }

}
