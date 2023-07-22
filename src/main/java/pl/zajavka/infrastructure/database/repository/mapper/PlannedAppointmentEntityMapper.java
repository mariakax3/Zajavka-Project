package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.PlannedAppointment;
import pl.zajavka.infrastructure.database.entity.PlannedAppointmentEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlannedAppointmentEntityMapper {

    @Mapping(target = "patient.appointments", ignore = true)
    @Mapping(target = "doctor.appointments", ignore = true)
    PlannedAppointment mapFromEntity(PlannedAppointmentEntity entity);
}
