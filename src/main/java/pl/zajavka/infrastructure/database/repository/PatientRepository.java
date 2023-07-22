package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.PatientDAO;
import pl.zajavka.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.PatientEntityMapper;

@Repository
@AllArgsConstructor
public class PatientRepository implements PatientDAO {

    private final PatientJpaRepository patientJpaRepository;
    private final PatientEntityMapper patientEntityMapper;
}
