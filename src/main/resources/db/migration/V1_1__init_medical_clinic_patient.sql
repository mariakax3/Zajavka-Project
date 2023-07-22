CREATE TABLE patient
(
    patient_id SERIAL      NOT NULL,
    name       VARCHAR(32) NOT NULL,
    surname    VARCHAR(32) NOT NULL,
    birthdate  DATE        NOT NULL,
    pesel      VARCHAR(32) NOT NULL,
    PRIMARY KEY (patient_id),
    UNIQUE (pesel)
);