package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.domain.CompletedAppointment;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring")
public interface CompletedAppointmentMapper {

    @Mapping(source = "plannedAppointment.dateTime", target = "plannedAppointment.dateTime", qualifiedByName = "mapDateTime")
    CompletedAppointmentDTO map(CompletedAppointment completedAppointment);

    @Named("mapDateTime")
    default String mapDateTime(OffsetDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTimeFormatter.format(dateTime);
    }
}
