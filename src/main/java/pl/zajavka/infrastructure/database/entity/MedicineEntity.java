package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"medicineId", "name", "dosage"})
@EqualsAndHashCode(of = "medicineId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicine")
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Integer medicineId;

    @Column(name = "name")
    private String name;

    @Column(name = "dosage")
    private String dosage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    private CompletedAppointmentEntity completedAppointment;
}
