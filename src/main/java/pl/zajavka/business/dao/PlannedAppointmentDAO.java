package pl.zajavka.business.dao;

import pl.zajavka.domain.PlannedAppointment;

import java.util.List;

public interface PlannedAppointmentDAO {

    List<PlannedAppointment> findPlannedAppointments();

    List<PlannedAppointment> findPlannedAppointments(String patientId);
}
