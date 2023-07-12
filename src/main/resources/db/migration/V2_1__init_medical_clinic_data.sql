INSERT INTO patient (name, surname, birthdate, pesel) VALUES
('Stefan', 'Chorobliwy', TO_TIMESTAMP('1987-10-12', 'YYYY-MM-DD'), '87101298078'),
('Anna', 'Pacjentka', TO_TIMESTAMP('1967-12-12', 'YYYY-MM-DD'), '67121245341'),
('Tomasz', 'Zaziębiony', TO_TIMESTAMP('1986-01-31', 'YYYY-MM-DD'), '86013100893'),
('Monika', 'Zdrowotna', TO_TIMESTAMP('1999-09-02', 'YYYY-MM-DD'), '99090298065');

INSERT INTO doctor (name, surname, specialization, pesel) VALUES
('Adam', 'Skalpel', 'Chirurgia', '78041156534'),
('Ewa', 'Wszystkowidząca', 'Okulistyka', '88090978765'),
('Paweł', 'Kieł', 'Stomatologia', '70051455790'),
('Marcin', 'Skorpion', 'Onkologia', '76111123415');