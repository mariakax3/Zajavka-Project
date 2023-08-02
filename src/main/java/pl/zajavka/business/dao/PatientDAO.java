package pl.zajavka.business.dao;

import pl.zajavka.domain.Patient;

import java.util.Optional;

public interface PatientDAO {

    Optional<Patient> findPatientById(String patientId);
}
