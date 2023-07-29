package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString(of = {"physicalExaminationId", "name", "result"})
@EqualsAndHashCode(of = "physicalExaminationId")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "physical_examination")
public class PhysicalExaminationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "physical_examination_id")
    private Integer physicalExaminationId;

    @Column(name = "name")
    private String name;

    @Column(name = "result")
    private String result;
}
