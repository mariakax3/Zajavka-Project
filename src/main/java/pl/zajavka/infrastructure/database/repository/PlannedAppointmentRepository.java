package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.business.dao.PlannedAppointmentDAO;
import pl.zajavka.domain.PlannedAppointment;
import pl.zajavka.infrastructure.database.entity.PlannedAppointmentEntity;
import pl.zajavka.infrastructure.database.repository.jpa.PlannedAppointmentJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.PlannedAppointmentEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class PlannedAppointmentRepository implements PlannedAppointmentDAO {

    private final PlannedAppointmentJpaRepository plannedAppointmentJpaRepository;
    private final PlannedAppointmentEntityMapper plannedAppointmentEntityMapper;

    @Override
    public List<PlannedAppointment> findPlannedAppointmentsForDoctor(String doctorId) {
        return plannedAppointmentJpaRepository.findByDoctorId(Integer.parseInt(doctorId))
                .stream()
                .map(plannedAppointmentEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<PlannedAppointment> findPlannedAppointmentsForPatient(String patientId) {
        return plannedAppointmentJpaRepository.findByPatientId(Integer.parseInt(patientId))
                .stream()
                .map(plannedAppointmentEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public PlannedAppointment findPlannedAppointmentById(String plannedAppointmentId) {
        PlannedAppointmentEntity plannedAppointmentEntity = plannedAppointmentJpaRepository.findById(Integer.parseInt(plannedAppointmentId))
                .orElseThrow();
        return plannedAppointmentEntityMapper.mapFromEntity(plannedAppointmentEntity);
    }

    @Override
    public void saveDTO(PlannedAppointmentDTO dto) {
        plannedAppointmentJpaRepository.save(plannedAppointmentEntityMapper.mapToEntity(dto));
    }

    @Override
    public void cancelAppointment(String plannedAppointmentId) {
        plannedAppointmentJpaRepository.deleteById(Integer.parseInt(plannedAppointmentId));
    }
}
