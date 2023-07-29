package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"completedAppointmentId", "doctorComment", "plannedAppointment", "physicalExaminations", "medicines"})
@EqualsAndHashCode(of = "completedAppointmentId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "completed_appointment")
public class CompletedAppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "completed_appointment_id")
    private String completedAppointmentId;

    @Column(name = "doctor_comment")
    private String doctorComment;

    @Column(name = "cost")
    private BigDecimal cost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planned_appointment_id")
    private PlannedAppointmentEntity plannedAppointment;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "physical_examination_id")
    private Set<PhysicalExaminationEntity> physicalExaminations;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id")
    private Set<MedicineEntity> medicines;
}
