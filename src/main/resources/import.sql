/* Insertar datos en tabla ingredientes*/
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Limon de pica', 'Malla de 1 Kg', 'Modificador', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Limon sutil', 'Malla de 1 Kg', 'Modificador', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Jarabe de goma', 'Botella 750 cc', 'Modificador', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Ron', 'Botella 750 cc', 'Base' , 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Tequila', 'Botella 750 cc', 'Base', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Pisco', 'Botella 750 cc', 'Base', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Cerveza', 'Botella 750 cc', 'Base', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Gin', 'Botella 750 cc', 'Base', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Azucar', '5 gramos', 'Modificador', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Menta', '1 Rama', 'Aditivo', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Cognac', '1 Rama', 'Aditivo', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Licor crema de cacao', '1 Rama', 'Aditivo', 20, '2023-08-01');
INSERT INTO ingredientes (nombre, descripcion, categoria ,stock, create_at) VALUES('Crema de leche', '1 Rama', 'Aditivo', 20, '2023-08-01');


/* Insertar datos en tabla medidas*/
INSERT INTO medidas (nombre, create_at) VALUES('Oz','2023-08-02');
INSERT INTO medidas (nombre, create_at) VALUES('Cucharita de bar de','2023-08-02');
INSERT INTO medidas (nombre, create_at) VALUES('Gotita','2023-08-02');
INSERT INTO medidas (nombre, create_at) VALUES('Pizca','2023-08-02');
INSERT INTO medidas (nombre, create_at) VALUES('Rama','2023-08-02');
INSERT INTO medidas (nombre, create_at) VALUES('Dash','2023-08-02');


/* Insertar datos en tabla estados*/
INSERT INTO estados (nombre, create_at) VALUES('Reservado','2023-08-02');
INSERT INTO estados (nombre, create_at) VALUES('Pagado','2023-08-02');
INSERT INTO estados (nombre, create_at) VALUES('Despachado','2023-08-02');
INSERT INTO estados (nombre, create_at) VALUES('Cancelado','2023-08-02');

/* Insertar datos en tabla usuarios*/
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Vicente', 'Zapata', 'vzapata@ciisa.com', '2023-08-03', 'ADMIN');
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Jose', 'Sosa', 'jsosa@ciisa.com', '2023-08-03', 'USER');
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Jones', 'Gomez', 'jgomez@ciisa.com', '2023-08-03', 'USER');
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Williams', 'Gonzalez', 'wgonz@ciisa.com', '2023-08-03', 'USER');
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Camila', 'Torres', 'ctorres@ciisa.com', '2023-08-03', 'USER');
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Raquel', 'Martinez', 'rmartinez@ciisa.com', '2023-08-03', 'ADMIN');
INSERT INTO usuarios (nombre, apellido, email, create_at, rol) VALUES('Cristina', 'Lopez', 'clopez@ciisa.com', '2023-08-03', 'USER');

/* Insertar datos en tabla cocteles*/
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Mojito', 'Machacar....', 5990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Pisco sour', 'Machacar....', 4990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Daiquiri', 'Machacar....', 5500, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Gin tonic', 'Machacar....', 4900, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Manhattan', 'Machacar....', 4000, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Dry martini', 'Machacar....', 4300, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Mint julep', 'Machacar....', 4990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Cynar julep', 'Machacar....', 4990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Americano', 'Machacar....', 5000, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Negroni', 'Machacar....', 4990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Spritz', 'Machacar....', 5990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Margarita', 'Machacar....', 6990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Caipirinha', 'Machacar....', 7990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Bloody mary', 'Machacar....', 8990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Alexander', 'Machacar....', 7990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Ruso blanco', 'Machacar....', 6990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Rusty nail', 'Machacar....', 8990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Hanky panky', 'Machacar....', 9990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Godfather', 'Machacar....', 9990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Espresso martini', 'Machacar....', 7990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Penicillin', 'Machacar....', 6990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Moscow mule', 'Machacar....', 5990, '2023-08-03', '');
INSERT INTO cocteles (nombre, descripcion, precio, create_at, imagen) VALUES('Tom collins', 'Machacar....', 5990, '2023-08-03', '');

/* Insertar datos en tabla cocteles_ingredientes*/
INSERT INTO cocteles_ingredientes (coctel_id, ingrediente_id, medida_id, cantidad, create_at) VALUES (15,11,1,2,'2023-08-03')
INSERT INTO cocteles_ingredientes (coctel_id, ingrediente_id, medida_id, cantidad, create_at) VALUES (15,12,1,2,'2023-08-03')
INSERT INTO cocteles_ingredientes (coctel_id, ingrediente_id, medida_id, cantidad, create_at) VALUES (15,13,1,2,'2023-08-03')
INSERT INTO cocteles_ingredientes (coctel_id, ingrediente_id, medida_id, cantidad, create_at) VALUES (14,13,1,2,'2023-08-03')

/* Insertar datos en tabla pedidos*/
INSERT INTO pedidos (usuario_id,fecha_pedido,estado_id, create_at) VALUES (5,'2023-08-03',1,'2023-08-03')

/* Insertar datos en tabla detalles_pedido*/
INSERT INTO detalles_pedido (pedido_id, coctel_id, cantidad, create_at) VALUES (1, 15,1, '2023-08-03')

