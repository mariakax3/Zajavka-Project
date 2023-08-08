package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.DoctorAvailabilityDAO;
import pl.zajavka.domain.DoctorAvailability;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorAvailabilityService {

    private final DoctorAvailabilityDAO doctorAvailabilityDAO;

    public List<DoctorAvailability> findByDoctorId(String doctorId) {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityDAO.findByDoctorId(doctorId);
        log.info("Available appointments for doctor with ID [{}]: [{}]",
                doctorId, doctorAvailabilities.size());
        return doctorAvailabilities;
    }
}
