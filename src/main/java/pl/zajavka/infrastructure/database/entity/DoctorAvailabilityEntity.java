package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"doctorAvailabilityId", "month", "day", "hour", "doctor"})
@EqualsAndHashCode(of = "doctorAvailabilityId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor_availability")
public class DoctorAvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_availability_id")
    private Integer doctorAvailabilityId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "day")
    private Integer day;

    @Column(name = "hour")
    private Time hour;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;
}
