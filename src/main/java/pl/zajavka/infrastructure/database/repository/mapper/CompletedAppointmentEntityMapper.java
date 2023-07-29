package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.CompletedAppointment;
import pl.zajavka.infrastructure.database.entity.CompletedAppointmentEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompletedAppointmentEntityMapper {

    @Mapping(target = "patient.appointments", ignore = true)
    @Mapping(target = "doctor.appointments", ignore = true)
    CompletedAppointment mapFromEntity(CompletedAppointmentEntity entity);
}
