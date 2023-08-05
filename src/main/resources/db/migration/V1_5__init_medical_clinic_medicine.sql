CREATE TABLE medicine
(
    medicine_id SERIAL      NOT NULL,
    name        VARCHAR(128) NOT NULL,
    dosage      VARCHAR(256) NOT NULL,
    PRIMARY KEY (medicine_id)
);