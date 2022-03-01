-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 01-03-2022 a las 23:40:26
-- Versión del servidor: 10.5.8-MariaDB
-- Versión de PHP: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `analisis_semillas`
--
CREATE DATABASE IF NOT EXISTS `analisis_semillas` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `analisis_semillas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `analisis`
--

CREATE TABLE `analisis` (
  `cod_analisis` int(11) NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `analisis`
--

INSERT INTO `analisis` (`cod_analisis`, `precio`, `descripcion`) VALUES
(27, '1300.00', 'TETRAZOLIO'),
(28, '950.00', 'Peso/Mil'),
(29, '1100.00', 'Poder Germinativo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cuit` varchar(23) NOT NULL,
  `razon_social` varchar(45) NOT NULL,
  `telefono` varchar(23) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cuit`, `razon_social`, `telefono`, `email`) VALUES
('23-39781222-3', 'Lucas ', '03468656164', 'lucas12@gmail.,com'),
('23-40781222-3', 'Tabacman SRL', '0341545351', 'ricardotabacman@gmail.com'),
('23-40781552-3', 'BitCow', '03468553377', 'carlos@gmail.com'),
('23-40781552-9', 'Perez S.A', '0341515566', 'juanperez@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liquidacion`
--

CREATE TABLE `liquidacion` (
  `cod_liquidacion` int(11) NOT NULL,
  `cod_user` int(11) NOT NULL,
  `fecha_liquidacion` datetime NOT NULL,
  `total` decimal(8,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `liquidacion`
--

INSERT INTO `liquidacion` (`cod_liquidacion`, `cod_user`, `fecha_liquidacion`, `total`) VALUES
(1, 1, '2022-03-01 00:00:00', '3017.500');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `cod_pedido` int(11) NOT NULL,
  `cuit` varchar(23) NOT NULL,
  `cod_semilla` int(11) NOT NULL,
  `cod_liquidacion` int(11) DEFAULT NULL,
  `fecha_pedido` datetime DEFAULT NULL,
  `descuento` decimal(8,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`cod_pedido`, `cuit`, `cod_semilla`, `cod_liquidacion`, `fecha_pedido`, `descuento`) VALUES
(19, '23-40781222-3', 9, 1, '2022-02-09 00:00:00', '15.000'),
(21, '23-40781552-9', 7, NULL, '2022-03-01 00:00:00', '5.000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_analisis`
--

CREATE TABLE `pedido_analisis` (
  `cod_pedido_Analisis` int(11) NOT NULL,
  `cod_pedido` int(11) NOT NULL,
  `cod_analisis` int(11) NOT NULL,
  `estado` varchar(23) DEFAULT NULL,
  `observaciones` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido_analisis`
--

INSERT INTO `pedido_analisis` (`cod_pedido_Analisis`, `cod_pedido`, `cod_analisis`, `estado`, `observaciones`) VALUES
(56, 19, 27, 'Completado', 'Resultado: 97%'),
(57, 19, 27, 'Completado', 'Resultado: 89%'),
(58, 19, 28, 'Completado', '160Gr/Mil'),
(60, 21, 28, 'Realizado', '-');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semilla`
--

CREATE TABLE `semilla` (
  `cod_semilla` int(11) NOT NULL,
  `raza` varchar(45) NOT NULL,
  `especie` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `semilla`
--

INSERT INTO `semilla` (`cod_semilla`, `raza`, `especie`) VALUES
(5, '4612', 'Soja'),
(6, '3D2Y', 'Trigo'),
(7, 'BAYER', 'Trigo'),
(8, '4X4', 'Maíz'),
(9, 'SRM3988', 'Girasol'),
(10, '3312 MMAIZ', 'Maiz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `cod_user` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `tipo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cod_user`, `username`, `password`, `tipo`, `nombre`, `apellido`) VALUES
(1, 'German', '123456', 0, 'German', 'Pacheco'),
(2, 'Angel', 'soy1genio', 1, 'Folguera', 'Angel');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `analisis`
--
ALTER TABLE `analisis`
  ADD PRIMARY KEY (`cod_analisis`),
  ADD UNIQUE KEY `cod_analisis_UNIQUE` (`cod_analisis`),
  ADD UNIQUE KEY `descripcion_UNIQUE` (`descripcion`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cuit`),
  ADD UNIQUE KEY `cuit_UNIQUE` (`cuit`);

--
-- Indices de la tabla `liquidacion`
--
ALTER TABLE `liquidacion`
  ADD PRIMARY KEY (`cod_liquidacion`),
  ADD KEY `fk_usuario_idx` (`cod_user`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`cod_pedido`),
  ADD UNIQUE KEY `cod_pedido_UNIQUE` (`cod_pedido`),
  ADD KEY `fk_semilla_idx` (`cod_semilla`),
  ADD KEY `fk_cliente_idx` (`cuit`),
  ADD KEY `fk_liquidacion_idx` (`cod_liquidacion`);

--
-- Indices de la tabla `pedido_analisis`
--
ALTER TABLE `pedido_analisis`
  ADD PRIMARY KEY (`cod_pedido_Analisis`),
  ADD UNIQUE KEY `cod_pedido_Analisis_UNIQUE` (`cod_pedido_Analisis`),
  ADD KEY `fk_analisis_idx` (`cod_analisis`),
  ADD KEY `fk_pedido_idx` (`cod_pedido`);

--
-- Indices de la tabla `semilla`
--
ALTER TABLE `semilla`
  ADD PRIMARY KEY (`cod_semilla`),
  ADD UNIQUE KEY `cod_semilla_UNIQUE` (`cod_semilla`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod_user`),
  ADD UNIQUE KEY `cod_user_UNIQUE` (`cod_user`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `analisis`
--
ALTER TABLE `analisis`
  MODIFY `cod_analisis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `liquidacion`
--
ALTER TABLE `liquidacion`
  MODIFY `cod_liquidacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `cod_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `pedido_analisis`
--
ALTER TABLE `pedido_analisis`
  MODIFY `cod_pedido_Analisis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `semilla`
--
ALTER TABLE `semilla`
  MODIFY `cod_semilla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `liquidacion`
--
ALTER TABLE `liquidacion`
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`cod_user`) REFERENCES `usuario` (`cod_user`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_cliente` FOREIGN KEY (`cuit`) REFERENCES `cliente` (`cuit`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_liquidacion` FOREIGN KEY (`cod_liquidacion`) REFERENCES `liquidacion` (`cod_liquidacion`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_semilla` FOREIGN KEY (`cod_semilla`) REFERENCES `semilla` (`cod_semilla`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido_analisis`
--
ALTER TABLE `pedido_analisis`
  ADD CONSTRAINT `fk_analisis` FOREIGN KEY (`cod_analisis`) REFERENCES `analisis` (`cod_analisis`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pedido` FOREIGN KEY (`cod_pedido`) REFERENCES `pedido` (`cod_pedido`) ON UPDATE CASCADE;
--
-- Base de datos: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
