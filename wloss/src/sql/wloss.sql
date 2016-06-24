-- phpMyAdmin SQL Dump
-- version 4.1.13
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Июн 24 2016 г., 23:15
-- Версия сервера: 5.5.44-MariaDB-log
-- Версия PHP: 5.5.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `wloss`
--

-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `colories` int(11) DEFAULT NULL,
  `fat` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `proteins` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=152 ;

--
-- Дамп данных таблицы `product`
--

INSERT INTO `product` (`id`, `colories`, `fat`, `name`, `proteins`) VALUES
(151, 1, 3, 'test1', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `ration`
--

CREATE TABLE IF NOT EXISTS `ration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `eaten_date` date DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ration_user_id` (`user_id`),
  KEY `FK_ration_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `SEQUENCE`
--

CREATE TABLE IF NOT EXISTS `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `SEQUENCE`
--

INSERT INTO `SEQUENCE` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '200');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `hight` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `name`, `password`, `birth_date`, `hight`, `weight`, `email`) VALUES
(1, 'hanss', 'Pavel', '123', '1972-02-19', 175, 95, 'pavel.grudanov@gmail.com'),
(2, 'viktor', 'Viktor', '123', '1999-03-15', 175, 75, 'victor.grudanov@gmail.com');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `ration`
--
ALTER TABLE `ration`
  ADD CONSTRAINT `FK_ration_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_ration_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
