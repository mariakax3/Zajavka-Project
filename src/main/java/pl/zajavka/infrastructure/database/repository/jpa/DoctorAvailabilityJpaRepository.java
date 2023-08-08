package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.DoctorAvailabilityEntity;

import java.util.List;

@Repository
public interface DoctorAvailabilityJpaRepository extends JpaRepository<DoctorAvailabilityEntity, Integer> {

    @Query("SELECT da FROM DoctorAvailabilityEntity da WHERE da.doctor.doctorId = :doctorId")
    List<DoctorAvailabilityEntity> findByDoctorId(Integer doctorId);
}
