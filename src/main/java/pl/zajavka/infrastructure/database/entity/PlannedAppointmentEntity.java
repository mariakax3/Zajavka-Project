package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"plannedAppointmentId", "dateTime", "patientComment"})
@EqualsAndHashCode(of = "plannedAppointmentId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planned_appointment")
public class PlannedAppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planned_appointment_id")
    private Integer plannedAppointmentId;

    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @Column(name = "patient_comment")
    private String patientComment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "plannedAppointment")
    private CompletedAppointmentEntity completedAppointment;
}
