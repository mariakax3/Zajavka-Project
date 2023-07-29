package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.domain.CompletedAppointment;

@Component
@Mapper(componentModel = "spring")
public interface CompletedAppointmentMapper {
    CompletedAppointmentDTO map(CompletedAppointment completedAppointment);
}
