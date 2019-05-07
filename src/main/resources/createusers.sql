SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `bank-app` DEFAULT CHARACTER SET utf8;
USE `bank-app` ;

CREATE TABLE IF NOT EXISTS `bank-app`.`user`(
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
 `created_time` DATETIME,
 `password` VARCHAR(32) NOT NULL,
 `updated_time` DATETIME,
 `username` VARCHAR(16) NOT NULL,
 PRIMARY KEY (`id`));

INSERT INTO `bank-app`.`user` (`id`, `created_time`, `password`, `updated_time`, `username`) VALUES ('1', '2019-04-27', 'bogdanel', '2019-04-27', 'bogdan');
INSERT INTO `bank-app`.`user` (`id`, `created_time`, `password`, `updated_time`, `username`) VALUES ('2', '2019-04-27', 'Alina1', '2019-04-27', 'iosif');
INSERT INTO `bank-app`.`user` (`id`, `created_time`, `password`, `updated_time`, `username`) VALUES ('3', '2019-04-29', 'testPas', '2019-04-29', 'Ioan1');
INSERT INTO `bank-app`.`user` (`id`, `created_time`, `password`, `updated_time`, `username`) VALUES ('4', '2019-05-01', 'myPass', '2019-05-01', 'alexP92');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;