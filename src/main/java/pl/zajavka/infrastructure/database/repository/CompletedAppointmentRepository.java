package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.CompletedAppointmentDAO;
import pl.zajavka.domain.CompletedAppointment;
import pl.zajavka.infrastructure.database.repository.jpa.CompletedAppointmentJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.CompletedAppointmentEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class CompletedAppointmentRepository implements CompletedAppointmentDAO {

    private final CompletedAppointmentJpaRepository completedAppointmentJpaRepository;
    private final CompletedAppointmentEntityMapper completedAppointmentEntityMapper;

    @Override
    public List<CompletedAppointment> findCompletedAppointments(List<Integer> plannedAppointmentIds) {
        return completedAppointmentJpaRepository.findByPlannedAppointmentIdIn(plannedAppointmentIds)
                .stream()
                .map(completedAppointmentEntityMapper::mapFromEntity)
                .toList();
    }
}
