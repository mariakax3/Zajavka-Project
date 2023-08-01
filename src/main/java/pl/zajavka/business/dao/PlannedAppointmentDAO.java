package pl.zajavka.business.dao;

import pl.zajavka.domain.PlannedAppointment;

import java.util.List;

public interface PlannedAppointmentDAO {

    List<PlannedAppointment> findPlannedAppointmentsForDoctor(String patientId);

    List<PlannedAppointment> findPlannedAppointmentsForPatient(String patientId);

    PlannedAppointment findPlannedAppointmentById(String plannedAppointmentId);

    Integer getDoctorId(String plannedAppointmentId);
}
