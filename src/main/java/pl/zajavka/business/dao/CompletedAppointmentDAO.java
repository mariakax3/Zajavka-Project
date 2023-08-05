package pl.zajavka.business.dao;

import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.domain.CompletedAppointment;

import java.util.List;

public interface CompletedAppointmentDAO {

    List<CompletedAppointment> findCompletedAppointments(List<Integer> plannedAppointmentIds);

    void saveDTO(CompletedAppointmentDTO dto);
}
