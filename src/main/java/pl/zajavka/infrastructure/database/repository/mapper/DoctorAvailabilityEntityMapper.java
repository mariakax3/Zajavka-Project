package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.DoctorAvailability;
import pl.zajavka.infrastructure.database.entity.DoctorAvailabilityEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorAvailabilityEntityMapper {

    DoctorAvailability map(DoctorAvailabilityEntity entity);
}
