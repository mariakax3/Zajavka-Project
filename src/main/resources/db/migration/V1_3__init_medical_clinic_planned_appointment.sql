CREATE TABLE planned_appointment
(
    planned_appointment_id SERIAL                   NOT NULL,
    date_time              TIMESTAMP WITH TIME ZONE NOT NULL,
    patient_comment        VARCHAR(256)             NOT NULL,
    patient_id             INT                      NOT NULL,
    doctor_id              INT                      NOT NULL,
    PRIMARY KEY (planned_appointment_id),
    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
            REFERENCES patient (patient_id),
    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctor (doctor_id)
);