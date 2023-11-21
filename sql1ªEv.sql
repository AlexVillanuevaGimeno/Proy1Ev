CREATE TABLE Usuario (
    Id_Usuario INT PRIMARY KEY,
    Nombre_Usuario VARCHAR(50),
    Apellido_1 VARCHAR(50),
    Apellido_2 VARCHAR(50),
    Email VARCHAR(100),
    Telefono VARCHAR(20),
    Username VARCHAR(50),
    Password VARCHAR(50)
);

CREATE TABLE Producto (
    Id_Producto INT PRIMARY KEY,
    Nombre_Producto VARCHAR(100),
    Precio_Producto DECIMAL(10, 2),
    Marca_Producto VARCHAR(50),
    Fecha_Subida_Producto DATE,
    Descripcion_Producto TEXT,
    Imagen_Producto VARCHAR(255),
    Id_Usuario INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Favorito (
    Id_Favorito INT PRIMARY KEY,
    Id_Producto INT,
    Id_Usuario INT,
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Categoria (
    Id_Categoria INT PRIMARY KEY,
    Nombre_Categoria VARCHAR(50)
);

CREATE TABLE Producto_Categoria (
    Id_Producto_Categoria INT PRIMARY KEY,
    Id_Producto INT,
    Id_Categoria INT,
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Categoria) REFERENCES Categoria(Id_Categoria)
);

CREATE TABLE Direccion_Cliente (
    Id_Direccion_Cliente INT PRIMARY KEY,
    Id_Usuario INT,
    Pais VARCHAR(50),
    Provincia VARCHAR(50),
    Ciudad VARCHAR(50),
    CP VARCHAR(10),
    Portal INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Venta (
    Id_Venta INT PRIMARY KEY,
    Fecha_Venta DATE,
    Hora_Venta TIME,
    Id_Usuario INT,
    Id_Producto INT,
    Id_Direccion_Cliente INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Direccion_Cliente) REFERENCES Direccion_Cliente(Id_Direccion_Cliente)
);

CREATE TABLE Valoracion (
    Id_Valoracion INT PRIMARY KEY,
    Id_Usuario INT,
    Estrellas INT,
    Resena TEXT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

--INSERTS TABLAS

-- INSERTS para la tabla Usuario
INSERT INTO Usuario (Id_Usuario, Nombre_Usuario, Apellido_1, Apellido_2, Email, Telefono, Username, Password)
VALUES (1, 'Juan', 'Perez', 'Gomez', 'juan@example.com', '123456789', 'juanito', 'contrasena123'),
       (2, 'Maria', 'Lopez', 'Garcia', 'maria@example.com', '987654321', 'marita', 'password456'),
       (3, 'Pedro', 'Rodriguez', 'Martinez', 'pedro@example.com', '456789012', 'pedrito', 'secret789');

-- INSERTS para la tabla Producto
INSERT INTO Producto (Id_Producto, Nombre_Producto, Precio_Producto, Marca_Producto, Fecha_Subida_Producto, Descripcion_Producto, Imagen_Producto, Id_Usuario)
VALUES (1, 'Laptop', 1200.00, 'Dell', '2023-01-15', 'Potente laptop para trabajo y entretenimiento.', 'laptop.jpg', 1),
       (2, 'Teléfono', 500.00, 'Samsung', '2023-02-20', 'Smartphone de última generación.', 'telefono.jpg', 2),
       (3, 'Cámara', 300.00, 'Canon', '2023-03-10', 'Cámara digital de alta resolución.', 'camara.jpg', 3);

-- INSERTS para la tabla Favorito
INSERT INTO Favorito (Id_Favorito, Id_Producto, Id_Usuario)
VALUES (1, 1, 2),
       (2, 3, 1),
       (3, 2, 3);

-- INSERTS para la tabla Categoria
INSERT INTO Categoria (Id_Categoria, Nombre_Categoria)
VALUES (1, 'Electrónicos'),
       (2, 'Hogar'),
       (3, 'Moda');

-- INSERTS para la tabla Producto_Categoria
INSERT INTO Producto_Categoria (Id_Producto_Categoria, Id_Producto, Id_Categoria)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);

-- INSERTS para la tabla Direccion_Cliente
INSERT INTO Direccion_Cliente (Id_Direccion_Cliente, Id_Usuario, Pais, Provincia, Ciudad, CP, Portal)
VALUES (1, 1, 'España', 'Madrid', 'Madrid', '28001', 12),
       (2, 2, 'México', 'Ciudad de México', 'Ciudad de México', '12345', 34),
       (3, 3, 'Argentina', 'Buenos Aires', 'Buenos Aires', 'C1234ABC', 56);

-- INSERTS para la tabla Venta
INSERT INTO Venta (Id_Venta, Fecha_Venta, Hora_Venta, Id_Usuario, Id_Producto, Id_Direccion_Cliente)
VALUES (1, '2023-04-05', '15:30:00', 1, 1, 1),
       (2, '2023-05-10', '12:45:00', 2, 2, 2),
       (3, '2023-06-20', '18:00:00', 3, 3, 3);

-- INSERTS para la tabla Valoracion
INSERT INTO Valoracion (Id_Valoracion, Id_Usuario, Estrellas, Resena)
VALUES (1, 1, 4, 'Buena experiencia de compra'),
       (2, 2, 5, 'Producto de alta calidad'),
       (3, 3, 3, 'Entrega rápida pero producto defectuoso');
