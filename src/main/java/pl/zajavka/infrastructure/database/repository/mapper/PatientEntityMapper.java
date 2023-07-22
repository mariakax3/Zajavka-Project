package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Patient;
import pl.zajavka.infrastructure.database.entity.PatientEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientEntityMapper {

    @Mapping(target = "appointments", ignore = true)
    Patient mapFromEntity(PatientEntity entity);
}
