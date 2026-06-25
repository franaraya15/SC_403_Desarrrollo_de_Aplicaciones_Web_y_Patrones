/*Se crea la base de datos solicitada 2 ptos*/
CREATE SCHEMA baseDatos;



/*Se crea un usuario para la base de datos 2 ptos*/
CREATE USER 'mateo31'@'localhost' IDENTIFIED BY '118170110';



/*Se asignan todos los privilegios sobre la base de datos solicitada al usuario creado 2 ptos*/
GRANT ALL PRIVILEGES ON baseDatos.* TO 'mateo31'@'localhost';



/* Se crea la tabla solicitada 2 ptos*/
CREATE TABLE baseDatos.cleta (
    id_cleta int auto_increment primary key,
    modelo varchar(15),
    precio_tope double,
    salida_cleta int,
    imagen_cleta varchar(1024)
);



/*Se insertan 4 registros en la tabla solicitada 2ptos */
INSERT INTO baseDatos.cleta (modelo, precio_tope, salida_cleta, imagen_cleta) VALUES
('Trek FX 3', 850000, 10, 'https://www.trekbikes.com/internationalspanish/es_IN_TL/bicicletas/bicis-h%C3%ADbridas/bicicletas-de-ejercicio/fx/fx-3-disc-gen-3/p/35021/'),
('Giant Escape', 620000, 8, 'http://www.cicanum.ucr.ac.cr/?b=19513901001450'),
('Sirrus 2.0', 990000, 5, 'https://www.thebikeshoponline.com/product/specialized-sirrus-2.0-372437-1.htm'),
('Quick 1', 740000, 12, 'https://www.cannondale.com/en-us/bikes/active/fitness/quick/quick-1');

