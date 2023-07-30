package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Medicine;
import pl.zajavka.infrastructure.database.entity.MedicineEntity;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineEntityMapper {

    @Mapping(target = "completedAppointment", ignore = true)
    Medicine mapFromEntity(MedicineEntity entity);
}
