package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.DoctorAvailabilityDTO;
import pl.zajavka.domain.DoctorAvailability;

@Component
@Mapper(componentModel = "spring")
public interface DoctorAvailabilityMapper {

    DoctorAvailabilityDTO map(final DoctorAvailability doctorAvailability);
}
