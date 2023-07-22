package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"patientId", "name", "surname", "birthdate", "pesel"})
@EqualsAndHashCode(of = "patientId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    private Set<PlannedAppointmentEntity> appointments;
}
