package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.PatientDTO;
import pl.zajavka.domain.Patient;

@Component
@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO map(final Patient patient);
}
