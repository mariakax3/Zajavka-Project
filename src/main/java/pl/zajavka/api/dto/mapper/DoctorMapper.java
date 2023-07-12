package pl.zajavka.api.dto.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.DoctorDTO;
import pl.zajavka.domain.Doctor;

@Component
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO map(final Doctor doctor);
}
