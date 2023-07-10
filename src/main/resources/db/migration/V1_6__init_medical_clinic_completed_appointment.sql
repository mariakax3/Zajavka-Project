CREATE TABLE completed_appointment
(
    completed_appointment_id SERIAL       NOT NULL,
    doctor_comment           VARCHAR(256) NOT NULL,
    cost                     INT          NOT NULL,
    planned_appointment_id   INT,
    physical_examination_id  INT,
    medicine_id              INT,
    PRIMARY KEY (completed_appointment_id),
    CONSTRAINT fk_appointment_appointment
        FOREIGN KEY (planned_appointment_id)
            REFERENCES planned_appointment (planned_appointment_id),
    CONSTRAINT fk_appointment_examination
        FOREIGN KEY (physical_examination_id)
            REFERENCES physical_examination (physical_examination_id),
    CONSTRAINT fk_appointment_medicine
        FOREIGN KEY (medicine_id)
            REFERENCES medicine (medicine_id)
);