/*
  Script de creacion de la base de datos para la Practica Individual #1
  Dominio: Catalogo de suculentas
  Crea: base de datos "practica", tabla "suculenta",
        usuario "usuario_practica" y datos de prueba.
*/

-- Limpieza previa (entorno de desarrollo)
DROP DATABASE IF EXISTS practica;
DROP USER IF EXISTS 'usuario_practica'@'%';

-- Creacion de la base de datos
CREATE DATABASE practica
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

-- Creacion del usuario solicitado y asignacion de permisos
CREATE USER 'usuario_practica'@'%' IDENTIFIED BY 'la_Clave';
GRANT SELECT, INSERT, UPDATE, DELETE ON practica.* TO 'usuario_practica'@'%';
FLUSH PRIVILEGES;

USE practica;

-- Tabla principal del dominio
CREATE TABLE suculenta (
  id_suculenta      INT NOT NULL AUTO_INCREMENT,
  nombre_comun      VARCHAR(60) NOT NULL,
  nombre_cientifico VARCHAR(80),
  familia           VARCHAR(50),
  color_principal   VARCHAR(30),
  altura_cm         INT,
  precio_estimado   DECIMAL(10,2) CHECK (precio_estimado >= 0),
  nivel_riego       VARCHAR(20),
  ruta_imagen       VARCHAR(1024),
  activo            BOOLEAN DEFAULT TRUE,
  PRIMARY KEY (id_suculenta),
  INDEX ndx_nombre_comun (nombre_comun)
) ENGINE = InnoDB;

-- Datos de prueba (imagenes referenciadas desde la nube)
INSERT INTO suculenta
  (nombre_comun, nombre_cientifico, familia, color_principal, altura_cm, precio_estimado, nivel_riego, ruta_imagen, activo)
VALUES
  ('Planta de jade', 'Crassula ovata', 'Crassulaceae', 'Verde', 60, 4500.00, 'Bajo',
   'https://upload.wikimedia.org/wikipedia/commons/3/3a/Crassula_ovata_kz02.jpg', true),
  ('Rosa de piedra', 'Echeveria elegans', 'Crassulaceae', 'Verde azulado', 12, 3200.00, 'Bajo',
   'https://upload.wikimedia.org/wikipedia/commons/2/2e/Echeveria_elegans_1.jpg', true),
  ('Aloe vera', 'Aloe barbadensis', 'Asphodelaceae', 'Verde', 50, 5000.00, 'Medio',
   'https://upload.wikimedia.org/wikipedia/commons/4/47/Aloe_vera_flower_inset.png', true),
  ('Lengua de suegra', 'Sansevieria trifasciata', 'Asparagaceae', 'Verde con amarillo', 80, 6500.00, 'Bajo',
   'https://upload.wikimedia.org/wikipedia/commons/b/b8/Sansevieria_trifasciata_2.jpg', true),
  ('Cactus erizo', 'Echinopsis oxygona', 'Cactaceae', 'Verde', 25, 3800.00, 'Bajo',
   'https://upload.wikimedia.org/wikipedia/commons/6/6c/Echinopsis_oxygona_flower.jpg', true),
  ('Collar de perlas', 'Senecio rowleyanus', 'Asteraceae', 'Verde claro', 8, 4200.00, 'Bajo',
   'https://upload.wikimedia.org/wikipedia/commons/9/95/Senecio_rowleyanus_1.jpg', false);
