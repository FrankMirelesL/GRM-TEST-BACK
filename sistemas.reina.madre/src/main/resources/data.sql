-- Pacientes de prueba
INSERT INTO Paciente (nombre, apellido, fecha_nacimiento, telefono, direccion)
VALUES 
('Juan', 'Pérez', '642751200000', '555-1234', 'Calle 123'),
('Ana', 'López', '642751200000', '555-5678', 'Av. Principal');
('Francisco', 'Mireles', '642751200000', '555-5678', 'Av. Circuito Bronce');

-- Médicos de prueba
INSERT INTO Medico (nombre, apellido, especialidad, telefono)
VALUES 
('Carlos', 'Martínez', 'Cardiología', '555-9876'),
('María', 'González', 'Pediatría', '555-6543');
('Cristian', 'Ramirez', 'Urologo', '555-6543');

-- Tipos de cita
INSERT INTO TipoCita (descripcion) VALUES 
('Consulta General'), 
('Revisión'),
('Servicio'),
('Tratamiento');
