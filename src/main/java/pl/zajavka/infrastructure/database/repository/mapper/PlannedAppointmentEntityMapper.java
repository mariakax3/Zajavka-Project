package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.domain.PlannedAppointment;
import pl.zajavka.infrastructure.database.entity.PlannedAppointmentEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlannedAppointmentEntityMapper {

    @Mapping(target = "patient.appointments", ignore = true)
    @Mapping(target = "doctor.appointments", ignore = true)
    PlannedAppointment mapFromEntity(PlannedAppointmentEntity entity);

    @Mapping(target = "patient.appointments", ignore = true)
    @Mapping(target = "doctor.appointments", ignore = true)
    @Mapping(target = "dateTime", qualifiedByName = "mapDateTime")
    PlannedAppointmentEntity mapToEntity(PlannedAppointmentDTO dto);

    @Named("mapDateTime")
    default OffsetDateTime mapDateTime(String value) {
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(value, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Warsaw"));
        return zonedDateTime.toOffsetDateTime();
    }
}
