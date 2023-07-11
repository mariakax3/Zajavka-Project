package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.PatientEntity;

@Repository
public interface PatientJpaRepository extends JpaRepository<PatientEntity, Integer> {
}
