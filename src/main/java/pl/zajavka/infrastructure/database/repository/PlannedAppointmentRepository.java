package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.PlannedAppointmentDAO;
import pl.zajavka.domain.PlannedAppointment;
import pl.zajavka.infrastructure.database.repository.jpa.PlannedAppointmentJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.PlannedAppointmentEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class PlannedAppointmentRepository implements PlannedAppointmentDAO {

    private final PlannedAppointmentJpaRepository plannedAppointmentJpaRepository;
    private final PlannedAppointmentEntityMapper plannedAppointmentEntityMapper;

    @Override
    public List<PlannedAppointment> findPlannedAppointments() {
        return plannedAppointmentJpaRepository.findAll().stream()
                .map(plannedAppointmentEntityMapper::mapFromEntity)
                .toList();
    }
}
