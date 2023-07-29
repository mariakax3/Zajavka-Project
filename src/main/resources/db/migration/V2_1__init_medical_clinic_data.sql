INSERT INTO patient (name, surname, birthdate, pesel)
VALUES ('Stefan', 'Chorobliwy', TO_TIMESTAMP('1987-10-12', 'YYYY-MM-DD'), '87101298078'),
       ('Anna', 'Pacjentka', TO_TIMESTAMP('1967-12-12', 'YYYY-MM-DD'), '67121245341'),
       ('Tomasz', 'Zaziębiony', TO_TIMESTAMP('1986-01-31', 'YYYY-MM-DD'), '86013100893'),
       ('Monika', 'Zdrowotna', TO_TIMESTAMP('1999-09-02', 'YYYY-MM-DD'), '99090298065');

INSERT INTO doctor (name, surname, specialization, pesel)
VALUES ('Adam', 'Skalpel', 'Chirurgia', '78041156534'),
       ('Ewa', 'Wszystkowidząca', 'Okulistyka', '88090978765'),
       ('Paweł', 'Kieł', 'Stomatologia', '70051455790'),
       ('Marcin', 'Skorpion', 'Onkologia', '76111123415');

INSERT INTO planned_appointment (date_time, patient_comment, patient_id, doctor_id)
VALUES ('2023-07-16 12:15:00', 'Silne bóle głowy', 4, 4),
       ('2023-09-10 10:30:00', 'Widzę, że nic nie widzę', 1, 2);

INSERT INTO physical_examination (name, result)
VALUES ('Tomografia komputerowa', 'OK');

INSERT INTO medicine (name, dosage)
VALUES ('Ketonal', '1 tabletka 1 na dobę');

INSERT INTO completed_appointment (doctor_comment, cost, planned_appointment_id, physical_examination_id, medicine_id)
VALUES ('Do obserwacji', 250, 1, 1, 1);
