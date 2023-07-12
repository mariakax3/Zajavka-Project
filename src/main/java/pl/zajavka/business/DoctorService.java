package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.DoctorDAO;
import pl.zajavka.domain.Doctor;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorDAO doctorDAO;

    public List<Doctor> findDoctors() {
        List<Doctor> availableDoctors = doctorDAO.findDoctors();
        log.info("Available doctors: [{}]", availableDoctors.size());
        return availableDoctors;
    }
}
