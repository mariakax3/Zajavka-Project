package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.PhysicalExamination;
import pl.zajavka.infrastructure.database.entity.PhysicalExaminationEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicalExaminationEntityMapper {

    @Mapping(target = "completedAppointment", ignore = true)
    PhysicalExamination mapFromEntity(PhysicalExaminationEntity entity);
}
