package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.PlannedAppointmentDAO;
import pl.zajavka.domain.PlannedAppointment;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PlannedAppointmentService {

    private final PlannedAppointmentDAO plannedAppointmentDAO;
    public List<PlannedAppointment> findPlannedAppointments() {
        List<PlannedAppointment> plannedAppointments =
                plannedAppointmentDAO.findPlannedAppointments();
        log.info("Planned appointments: [{}]", plannedAppointments.size());
        return plannedAppointments;
    }
}
