package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.PatientDAO;
import pl.zajavka.domain.Patient;
import pl.zajavka.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.PatientEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PatientRepository implements PatientDAO {

    private final PatientJpaRepository patientJpaRepository;
    private final PatientEntityMapper patientEntityMapper;

    @Override
    public Optional<Patient> findPatientById(String patientId) {
        return patientJpaRepository.findById(Integer.parseInt(patientId))
                .map(patientEntityMapper::mapFromEntity);
    }
}
