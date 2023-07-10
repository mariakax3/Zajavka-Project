CREATE TABLE medicine
(
    medicine_id SERIAL      NOT NULL,
    name        VARCHAR(32) NOT NULL,
    dosage      VARCHAR(32) NOT NULL,
    PRIMARY KEY (medicine_id)
);