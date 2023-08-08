package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zajavka.infrastructure.database.entity.DoctorEntity;

import java.sql.Time;

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

    @Override
    public String toString() {
        return String.format("%02d.%02d.%d r. godz. %s", day, month, year, hour);
    }
}
