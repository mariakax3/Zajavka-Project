package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.DoctorAvailabilityDAO;
import pl.zajavka.domain.DoctorAvailability;
import pl.zajavka.infrastructure.database.repository.jpa.DoctorAvailabilityJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.DoctorAvailabilityEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorAvailabilityRepository implements DoctorAvailabilityDAO {

    private final DoctorAvailabilityJpaRepository doctorAvailabilityJpaRepository;
    private final DoctorAvailabilityEntityMapper doctorAvailabilityEntityMapper;

    @Override
    public List<DoctorAvailability> findByDoctorId(String doctorId) {
        return doctorAvailabilityJpaRepository.findByDoctorId(Integer.parseInt(doctorId))
                .stream()
                .map(doctorAvailabilityEntityMapper::map)
                .toList();
    }
}
