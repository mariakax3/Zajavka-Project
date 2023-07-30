package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.CompletedAppointmentEntity;

import java.util.List;

@Repository
public interface CompletedAppointmentJpaRepository extends JpaRepository<CompletedAppointmentEntity, Integer> {

    @Query("SELECT ca FROM CompletedAppointmentEntity ca " +
            "WHERE ca.plannedAppointment.plannedAppointmentId IN :plannedAppointmentIds")
    List<CompletedAppointmentEntity> findByPlannedAppointmentIdIn(List<Integer> plannedAppointmentIds);
}
