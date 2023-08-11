package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zajavka.infrastructure.database.entity.DoctorEntity;

import java.sql.Time;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailabilityDTO {

    private Integer doctorAvailabilityId;
    private Integer year;
    private Integer month;
    private Integer day;
    private Time hour;
    private DoctorEntity doctor;
    private boolean checked;

    @Override
    public String toString() {
        return String.format("%02d.%02d.%d r. godz. %s", day, month, year, hour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAvailabilityDTO that = (DoctorAvailabilityDTO) o;
        return Objects.equals(year, that.year) && Objects.equals(month, that.month) && Objects.equals(day, that.day) && Objects.equals(hour, that.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, hour);
    }

    public static DoctorAvailabilityDTO copyDTO(DoctorAvailabilityDTO dto) {
        DoctorAvailabilityDTO newDTO = new DoctorAvailabilityDTO();
        newDTO.setYear(dto.getYear());
        newDTO.setMonth(dto.getMonth());
        newDTO.setDay(dto.getDay());
        return newDTO;
    }

}
