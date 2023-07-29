package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.PlannedAppointmentEntity;

import java.util.List;

@Repository
public interface PlannedAppointmentJpaRepository extends JpaRepository<PlannedAppointmentEntity, Integer> {

    @Query("SELECT pa FROM PlannedAppointmentEntity pa WHERE pa.patient.patientId = :patientId")
    List<PlannedAppointmentEntity> findByPatientId(int patientId);
}
