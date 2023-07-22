package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.domain.PlannedAppointment;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring")
public interface PlannedAppointmentMapper {

    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "mapDateTime")
    PlannedAppointmentDTO map(PlannedAppointment plannedAppointment);

    @Named("mapDateTime")
    default String mapDateTime(OffsetDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTimeFormatter.format(dateTime);
    }
}
