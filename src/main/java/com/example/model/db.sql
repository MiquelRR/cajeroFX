UPDATE Cuenta SET saldo = 1 WHERE NIF ='2';drop database if exists CajeroNOVA;
create database CajeroNOVA;
use CajeroNOVA;
CREATE TABLE Cliente (
    NIF VARCHAR(12),
    clave VARCHAR(15),
    nombre VARCHAR(30),
    apellidos VARCHAR(100),
    movil VARCHAR(15),
    CONSTRAINT Cliente_PK PRIMARY KEY (NIF)
);
INSERT INTO Cliente (NIF, clave, nombre, apellidos, movil) VALUES ('12345678Z', 'primero', 'Luis', 'García García', '600111222');
INSERT INTO Cliente (NIF, clave, nombre, apellidos, movil) VALUES ('12345679S', 'segundo', 'Ana', 'García Marín', '600111333');
INSERT INTO Cliente (NIF, clave, nombre, apellidos, movil) VALUES ('12345680Q', 'tercero', 'Paula', 'Sánchez Blas', '600111444');
INSERT INTO Cliente (NIF, clave, nombre, apellidos, movil) VALUES ('12345681V', 'cuarto', 'Carlos', 'Mas Mas', '600111555');

CREATE TABLE Cuenta (
    num_cta VARCHAR(20),
    NIF VARCHAR(12),
    saldo DECIMAL(20,2),
    CONSTRAINT Cuenta_PK PRIMARY KEY (num_cta),
    FOREIGN KEY (NIF) REFERENCES Cliente (NIF)
);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0100000101', '12345678Z', 3456.78);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0202020202', '12345678Z', 1258.20);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0300000003', '12345678Z', 65.00);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0400000011', '12345679S', 254.88);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0505050505', '12345680Q', 90.25);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0600000006', '12345679S', 2159.99);
INSERT INTO Cuenta (num_cta, NIF, saldo) VALUES ('0700000013', '12345681V', 100.00);


CREATE TABLE Factura (
    num_fra INT,
    NIF VARCHAR(12),
    num_habitacion INT,
    importe DECIMAL(8,2),
    CONSTRAINT Factura_PK PRIMARY KEY (num_fra),
    FOREIGN KEY (NIF) REFERENCES Cliente (NIF)
);

INSERT INTO Factura (num_fra, NIF, num_habitacion, importe) VALUES (1567,'12345678Z', 666, 145.00);
INSERT INTO Factura (num_fra, NIF, num_habitacion, importe) VALUES (1568,'12345681V', 303, 99.99);
INSERT INTO Factura (num_fra, NIF, num_habitacion, importe) VALUES (1569,'12345679S', 112, 300.00);
INSERT INTO Factura (num_fra, NIF, num_habitacion, importe) VALUES (1570,'12345679S', 114, 250.00);