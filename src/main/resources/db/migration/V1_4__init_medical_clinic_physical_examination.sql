CREATE TABLE physical_examination
(
    physical_examination_id SERIAL      NOT NULL,
    name                    VARCHAR(32) NOT NULL,
    result                  VARCHAR(32) NOT NULL,
    PRIMARY KEY (physical_examination_id)
);