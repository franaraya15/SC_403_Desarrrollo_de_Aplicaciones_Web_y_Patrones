/*
  Script de creación de tablas para TechShop — versión AIVEN.
  En Aiven (plan free) NO se puede crear base de datos ni usuarios:
  solo existe la base "defaultdb" y el usuario "avnadmin".
  Por eso este script trabaja directamente sobre defaultdb.
*/
use defaultdb;

-- --- Sección de Creación de Tablas ---

-- Tabla de categorías
create table categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(50) NOT NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_categoria),
  unique (descripcion),
  index ndx_descripcion (descripcion))
  ENGINE = InnoDB;

-- Tabla de productos
create table producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  descripcion VARCHAR(50) NOT NULL,
  detalle text,
  precio decimal(12,2) CHECK (precio >= 0),
  existencias int unsigned CHECK (existencias >= 0),
  ruta_imagen varchar(1024),
  activo boolean,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_producto),
  unique (descripcion),
  index ndx_descripcion (descripcion),
  foreign key fk_producto_categoria (id_categoria) references categoria(id_categoria))
  ENGINE = InnoDB;

-- Tabla de usuarios
CREATE TABLE usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(30) NOT NULL UNIQUE,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(75) NULL UNIQUE,
  telefono VARCHAR(25) NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`),
  CHECK (correo REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
  index ndx_username (username))
  ENGINE = InnoDB;

-- Tabla de facturas
create table factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  total decimal(12,2) check (total>0),
  estado ENUM('Activa', 'Pagada', 'Anulada') NOT NULL,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_factura),
  index ndx_id_usuario (id_usuario),
  foreign key fk_factura_usuario (id_usuario) references usuario(id_usuario))
  ENGINE = InnoDB;

-- Tabla de ventas
create table venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  precio_historico decimal(12,2) check (precio_historico>= 0),
  cantidad int unsigned check (cantidad> 0),
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_venta),
  index ndx_factura (id_factura),
  index ndx_producto (id_producto),
  UNIQUE (id_factura, id_producto),
  foreign key fk_venta_factura (id_factura) references factura(id_factura),
  foreign key fk_venta_producto (id_producto) references producto(id_producto))
  ENGINE = InnoDB;

-- Tabla de roles
create table rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  rol varchar(20) unique,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  primary key (id_rol))
  ENGINE = InnoDB;

-- Tabla de relación entre usuarios y roles
create table usuario_rol (
  id_usuario int not null,
  id_rol INT NOT NULL,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_usuario,id_rol),
  foreign key fk_usuarioRol_usuario (id_usuario) references usuario(id_usuario),
  foreign key fk_usuarioRol_rol (id_rol) references rol(id_rol))
  ENGINE = InnoDB;

-- Tabla de rutas
CREATE TABLE ruta (
    id_ruta INT AUTO_INCREMENT NOT NULL,
    ruta VARCHAR(255) NOT NULL,
    id_rol INT NULL,
    requiere_rol boolean NOT NULL DEFAULT TRUE,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    check (id_rol IS NOT NULL OR requiere_rol = FALSE),
    PRIMARY KEY (id_ruta),
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol))
    ENGINE = InnoDB;

-- Tabla de constantes de la aplicación
CREATE TABLE constante (
    id_constante INT AUTO_INCREMENT NOT NULL,
    atributo VARCHAR(25) NOT NULL,
    valor VARCHAR(150) NOT NULL,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_constante),
    UNIQUE (atributo))
    ENGINE = InnoDB;

-- --- Sección de Inserción de Datos ---
-- Inserción de usuarios
INSERT INTO usuario (username,password,nombre, apellidos, correo, telefono,ruta_imagen,activo) VALUES
('juan','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','Juan', 'Castro Mora',    'jcastro@gmail.com',    '4556-8978', 'https://img2.rtve.es/i/?w=1600&i=1677587980597.jpg',true),
('rebeca','$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi','Rebeca',  'Contreras Mora', 'acontreras@gmail.com', '5456-8789','https://media.licdn.com/dms/image/v2/C5603AQGwjJ5ht4bWXQ/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1661476259292?e=2147483647&v=beta&t=9_i5zTdqHRMSXlb9H4TuWkWeRGQXmaZLjxkBlWsg2lg',true),
('pedro','$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO','Pedro', 'Mena Loria',     'lmena@gmail.com',      '7898-8936','https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Eduardo_de_Pedro_2019.jpg/480px-Eduardo_de_Pedro_2019.jpg?20200109230854',true);

-- Inserción de categorias
INSERT INTO categoria (descripcion,ruta_imagen,activo) VALUES
('Monitores', 'https://image.benq.com/is/image/benqco/monitor-all-series-kv-m?$ResponsivePreset$&fmt=png-alpha',   true),
('Teclados',  'https://cnnespanol.cnn.com/wp-content/uploads/2022/04/teclado-mecanico.jpg',   true),
('Tarjeta Madre','https://static-geektopia.com/storage/thumbs/784x311/788/7884251b/98c0f4a5.webp',true),
('Celulares','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyVjIkJkEs1CqujaBs6G0PUxGJJ4vlCTFxsQ&s',    false);

-- Inserción de productos
INSERT INTO producto (id_categoria,descripcion,detalle,precio,existencias,ruta_imagen,activo) VALUES
(1,'Monitor AOC 19','Monitor AOC de 19 pulgadas, ideal para oficina.',23000,5,'https://c.pxhere.com/images/ec/fd/d67b367ed6467eb826842ac81d3b-1453591.jpg!d',true),
(1,'Monitor MAC','Monitor estilo Mac de alta resolución.',27000,2,'https://c.pxhere.com/photos/17/77/Art_Calendar_Cc0_Creative_Design_High_Resolution_Mac_Stock-1622403.jpg!d',true),
(1,'Monitor Flex 21','Monitor flexible de 21 pulgadas.',24000,5,'https://www.trustedreviews.com/wp-content/uploads/sites/54/2022/09/LG-OLED-Flex-7-scaled.jpg',true),
(1,'Monitor Flex 36','Monitor flexible curvo de 36 pulgadas.',27600,2,'https://www.lg.com/us/images/tvs/md08003300/gallery/D-01.jpg',true),
(2,'Teclado espanol everex','Teclado en espanol marca Everex.',45000,5,'https://http2.mlstatic.com/D_NQ_NP_984317-MLA43206062255_082020-O.webp',true),
(2,'Teclado fisico gamer','Teclado mecanico gamer con retroiluminacion.',57000,2,'https://cyberteamcr.com/wp-content/uploads/2024/02/16064_11399.webp',true),
(2,'Teclado usb compacto','Teclado USB compacto para viajes.',25000,5,'https://live.staticflickr.com/7010/26783973491_3e2043edda_b.jpg',true),
(2,'Teclado Monitor Flex','Teclado compatible con monitores Flex.',27600,2,'https://hardzone.es/app/uploads-hardzone.es/2020/10/Mejores-KVM.jpg',true),
(3,'CPU Intel 7i','Procesador Intel Core i7.',15780,5,'https://live.staticflickr.com/7391/9662276651_f4aa27d5ca_b.jpg',true),
(3,'CPU Intel Core 5i','Procesador Intel Core i5.',15000,2,'https://live.staticflickr.com/1473/24714440462_31a0fcdfba_b.jpg',true),
(3,'AMD 7500','Procesador AMD Ryzen 7500.',25400,5,'https://upload.wikimedia.org/wikipedia/commons/0/0c/AMD_Ryzen_9_3900X_-_ISO.jpg',true),
(3,'AMD 670','Procesador AMD 670.',45000,3,'https://upload.wikimedia.org/wikipedia/commons/a/a0/AMD_Duron_850_MHz_D850AUT1B.jpg',true),
(4,'Samsung S22','Telefono Samsung Galaxy S22.',285000,0,'https://www.trustedreviews.com/wp-content/uploads/sites/54/2022/08/S22-app-drawer-scaled.jpg',true),
(4,'Motorola X23','Telefono Motorola X23.',154000,0,'https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/10/motorola-2.jpg',true),
(4,'Nokia 5430','Telefono Nokia 5430 resistente.',330000,0,'https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/08/nokia-xr20-1.jpg',true),
(4,'Xiami x45','Telefono Xiaomi x45.',273000,0,'https://www.trustedreviews.com/wp-content/uploads/sites/54/2022/03/20220315_104812-1-scaled.jpg',true);

-- Inserción de facturas
INSERT INTO factura (id_usuario,fecha,total,estado) VALUES
(1,'2025-06-05',211560,'Pagada'),
(2,'2025-06-07',554340,'Pagada'),
(3,'2025-07-07',871000,'Pagada'),
(1,'2025-07-15',244140,'Pagada'),
(2,'2025-07-17',414800,'Pagada'),
(3,'2025-07-21',420000,'Pagada');

-- Inserción de ventas
INSERT INTO venta (id_factura,id_producto,precio_historico,cantidad) values
(1,5,45000,3),
(1,9,15780,2),
(1,10,15000,3),
(2,5,45000,1),
(2,14,154000,3),
(2,9,15780,3),
(3,14,154000,1),
(3,6,57000,1),
(3,15,330000,2),
(4,6,57000,2),
(4,8,27600,3),
(4,9,15780,3),
(5,8,27600,3),
(5,14,154000,2),
(5,3,24000,1),
(6,15,330000,1),
(6,12,45000,1),
(6,10,15000,3);

-- Inserción de roles
insert into rol (rol) values ('ADMIN'), ('VENDEDOR'), ('USER');

-- ASignación de roles a usuarios
insert into usuario_rol (id_usuario, id_rol) values
 (1,1), (1,2), (1,3),(2,2),(2,3),(3,3);

-- Inserción de rutas con roles específicos
INSERT INTO ruta (ruta, id_rol) VALUES
('/producto/nuevo', 1),
('/producto/guardar', 1),
('/producto/modificar/**', 1),
('/producto/eliminar/**', 1),
('/categoria/nuevo', 1),
('/categoria/guardar', 1),
('/categoria/modificar/**', 1),
('/categoria/eliminar/**', 1),
('/usuario/**', 1),
('/constante/**', 1),
('/role/**', 1),
('/usuario_role/**', 1),
('/ruta/**', 1),
('/producto/listado', 2),
('/categoria/listado', 2),
('/pruebas/**', 2),
('/reportes/**', 2),
('/paypal/**', 3),
('/facturar/carrito', 3);

-- Inserción de rutas que no requieren rol
INSERT INTO ruta (ruta,requiere_rol) VALUES
('/',false),
('/index',false),
('/errores/**',false),
('/carrito/**',false),
('/registro/**',false),
('/403',false),
('/fav/**',false),
('/js/**',false),
('/css/**',false),
('/webjars/**',false);

-- Inserción de constantes de la aplicación
INSERT INTO constante (atributo,valor) VALUES
('dominio','localhost'),
('dolar','520.75'),
('paypal.client-id','AaDNEUcELb-wQi6_MOboN0a1Ug4BnD4Z2T2-_KIoDjIb8Rif6525nvRhDu-MS-YdKQ8PJqZi7R6T6e_k'),
('paypal.client-secret','EKBpJ1oXlwfcp60KyF9ednFM4i9G_RkzgPCpDXo_NbQbaO_bICxhs_a_mnepi7524BQeK_qdNPRmLt71'),
('paypal.mode','sandbox'),
('url_paypal_cancel','http://localhost/payment/cancel'),
('url_paypal_success','http://localhost/payment/success'),
('servidor.http','http://localhost'),
('app.paypal.return-url','/paypal/order/capture'),
('app.paypal.cancel-url','/paypal/pago_cancel'),
('app.paypal.cancel-error','/paypal/pago_error'),
('app.paypal.cancel-success','/paypal/pago_success');
</content>
</invoke>
