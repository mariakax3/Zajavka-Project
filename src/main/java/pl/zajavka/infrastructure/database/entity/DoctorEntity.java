package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"doctorId", "name", "surname", "specialization", "pesel"})
@EqualsAndHashCode(of = "doctorId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<PlannedAppointmentEntity> appointments;
}
