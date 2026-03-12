CREATE TABLE IF NOT EXISTS clientes (
    id             BIGSERIAL PRIMARY KEY,
    nombre         VARCHAR(255) NOT NULL,
    genero         VARCHAR(50)  NOT NULL,
    edad           INTEGER      NOT NULL,
    identificacion VARCHAR(255) NOT NULL UNIQUE,
    direccion      VARCHAR(255),
    telefono       VARCHAR(50),
    cliente_id     VARCHAR(255) NOT NULL UNIQUE,
    contrasena     VARCHAR(255) NOT NULL,
    estado         BOOLEAN      NOT NULL
);

INSERT INTO clientes (id, nombre, genero, edad, identificacion, direccion, telefono, cliente_id, contrasena, estado) VALUES
(1, 'Jose Lema',          'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785', 'joselema',          '1234', true),
(2, 'Marianela Montalvo', 'Femenino',  28, '0987654321', 'Amazonas y NNUU',        '097548965', 'marianelamontalvo', '5678', true),
(3, 'Juan Osorio',        'Masculino', 25, '1122334455', '13 junio y Equinoccial', '098874587', 'juanosorio',        '1245', true);

SELECT SETVAL('clientes_id_seq', (SELECT MAX(id) FROM clientes));


CREATE TABLE IF NOT EXISTS clientes_local (
    id         BIGINT       PRIMARY KEY,
    cliente_id VARCHAR(255) NOT NULL,
    nombre     VARCHAR(255) NOT NULL,
    estado     BOOLEAN      NOT NULL
);

CREATE TABLE IF NOT EXISTS cuentas (
    id            BIGSERIAL     PRIMARY KEY,
    numero_cuenta VARCHAR(255)  NOT NULL UNIQUE,
    tipo_cuenta   VARCHAR(50)   NOT NULL,
    saldo_inicial NUMERIC(19,2) NOT NULL,
    estado        BOOLEAN       NOT NULL,
    cliente_id    BIGINT        NOT NULL
);

CREATE TABLE IF NOT EXISTS movimientos (
    id              BIGSERIAL     PRIMARY KEY,
    fecha           TIMESTAMP     NOT NULL,
    tipo_movimiento VARCHAR(50)   NOT NULL,
    valor           NUMERIC(19,2) NOT NULL,
    saldo           NUMERIC(19,2) NOT NULL,
    cuenta_id       BIGINT        NOT NULL,
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

INSERT INTO clientes_local (id, cliente_id, nombre, estado) VALUES
(1, 'joselema',          'Jose Lema',         true),
(2, 'marianelamontalvo', 'Marianela Montalvo', true),
(3, 'juanosorio',        'Juan Osorio',        true);

INSERT INTO cuentas (id, numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
(1, '478758', 'Ahorro',     1425.00, true, 1),
(2, '225487', 'Corriente',   700.00, true, 2),
(3, '495878', 'Ahorros',     150.00, true, 3),
(4, '496825', 'Ahorros',       0.00, true, 2),
(5, '585545', 'Corriente',  1000.00, true, 1);

SELECT SETVAL('cuentas_id_seq', (SELECT MAX(id) FROM cuentas));

INSERT INTO movimientos (id, cuenta_id, fecha, tipo_movimiento, valor, saldo) VALUES
(1, 1, '2026-03-11 11:14:24', 'Retiro',   -575.00, 1425.00),
(2, 2, '2026-03-11 11:17:55', 'Deposito',  600.00,  700.00),
(3, 3, '2026-03-11 11:18:44', 'Deposito',  150.00,  150.00),
(4, 4, '2026-03-11 11:19:30', 'Retiro',   -540.00,    0.00);

SELECT SETVAL('movimientos_id_seq', (SELECT MAX(id) FROM movimientos));
