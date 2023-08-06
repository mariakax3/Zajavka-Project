package pl.zajavka.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.CompletedAppointmentMapper;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.PlannedAppointmentService;

import java.util.List;

@Component
@AllArgsConstructor
public class ControllerUtils {

    private final CompletedAppointmentService completedAppointmentService;
    private final CompletedAppointmentMapper completedAppointmentMapper;
    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;

    public List<PlannedAppointmentDTO> getPlannedAppointmentsForPatient(String patientId) {
        List<PlannedAppointmentDTO> completedAppointmentIds =
                completedAppointmentService.findCompletedAppointmentsByPatientId(patientId).stream()
                        .map(completedAppointmentMapper::map)
                        .map(CompletedAppointmentDTO::getPlannedAppointment)
                        .toList();

        return plannedAppointmentService.findPlannedAppointmentsForPatient(patientId).stream()
                        .map(plannedAppointmentMapper::map)
                        .filter(appointment -> !completedAppointmentIds.contains(appointment))
                        .toList();
    }
}
