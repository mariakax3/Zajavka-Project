CREATE TABLE doctor_availability
(
    doctor_availability_id SERIAL NOT NULL,
    year                   INT    NOT NULL,
    month                  INT    NOT NULL,
    day                    INT    NOT NULL,
    hour                   TIME   NOT NULL,
    doctor_id              INT    NOT NULL,
    PRIMARY KEY (doctor_availability_id),
    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctor (doctor_id)
);