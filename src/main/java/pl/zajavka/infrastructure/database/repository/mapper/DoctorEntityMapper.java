package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Doctor;
import pl.zajavka.infrastructure.database.entity.DoctorEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {

    @Mapping(target = "appointments", ignore = true)
    Doctor mapFromEntity(DoctorEntity entity);
}
