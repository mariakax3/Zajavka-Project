package pl.zajavka.business.dao;

import pl.zajavka.domain.Doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> findDoctors();
}
