package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.DoctorDAO;
import pl.zajavka.domain.Doctor;
import pl.zajavka.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.DoctorEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorRepository implements DoctorDAO {

    private final DoctorJpaRepository doctorJpaRepository;
    private final DoctorEntityMapper doctorEntityMapper;

    @Override
    public List<Doctor> findDoctors() {
        return doctorJpaRepository.findAll().stream()
                .map(doctorEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Doctor findByPesel(String pesel) {
        DoctorEntity doctorEntity = doctorJpaRepository.findByPesel(pesel);
        return doctorEntityMapper.mapFromEntity(doctorEntity);
    }
}
