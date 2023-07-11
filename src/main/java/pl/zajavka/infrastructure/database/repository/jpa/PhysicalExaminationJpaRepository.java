package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.PhysicalExaminationEntity;

@Repository
public interface PhysicalExaminationJpaRepository extends JpaRepository<PhysicalExaminationEntity, Integer> {
}
