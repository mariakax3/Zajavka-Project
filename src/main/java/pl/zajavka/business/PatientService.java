package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.PatientDAO;
import pl.zajavka.domain.Patient;

@Slf4j
@Service
@AllArgsConstructor
public class PatientService {

    private final PatientDAO patientDAO;

    public Patient findPatientById(String patientId) {
        return patientDAO.findPatientById(patientId)
                .orElseThrow();
    }
}
