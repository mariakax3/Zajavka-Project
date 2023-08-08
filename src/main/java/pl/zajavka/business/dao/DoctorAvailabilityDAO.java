package pl.zajavka.business.dao;

import pl.zajavka.domain.DoctorAvailability;

import java.util.List;

public interface DoctorAvailabilityDAO {

    List<DoctorAvailability> findByDoctorId(String doctorId);
}
