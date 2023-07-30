package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.CompletedAppointmentDAO;
import pl.zajavka.business.dao.PlannedAppointmentDAO;
import pl.zajavka.domain.CompletedAppointment;
import pl.zajavka.domain.PlannedAppointment;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CompletedAppointmentService {

    private final CompletedAppointmentDAO completedAppointmentDAO;
    private final PlannedAppointmentDAO plannedAppointmentDAO;

    public List<CompletedAppointment> findCompletedAppointmentsByPatientId(String patientId) {
        List<Integer> plannedAppointmentIds =
                plannedAppointmentDAO.findPlannedAppointmentsForPatient(patientId).stream()
                        .map(PlannedAppointment::getPlannedAppointmentId)
                        .toList();

        List<CompletedAppointment> completedAppointments =
                completedAppointmentDAO.findCompletedAppointments(plannedAppointmentIds);

        log.info("Completed appointments: [{}]", completedAppointments.size());
        return completedAppointments;
    }

    public List<CompletedAppointment> findCompletedAppointmentsByDoctorId(String doctorId) {
        List<Integer> plannedAppointmentIds =
                plannedAppointmentDAO.findPlannedAppointmentsForDoctor(doctorId).stream()
                        .map(PlannedAppointment::getPlannedAppointmentId)
                        .toList();

        List<CompletedAppointment> completedAppointments =
                completedAppointmentDAO.findCompletedAppointments(plannedAppointmentIds);

        log.info("Completed appointments: [{}]", completedAppointments.size());
        return completedAppointments;
    }
}
