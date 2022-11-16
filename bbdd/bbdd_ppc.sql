-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema g5-ppc_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `g5-ppc_db` ;

-- -----------------------------------------------------
-- Schema g5-ppc_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `g5-ppc_db` DEFAULT CHARACTER SET utf8 ;
USE `g5-ppc_db` ;

-- -----------------------------------------------------
-- Table `g5-ppc_db`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g5-ppc_db`.`admins` (
  `id_admin` INT NOT NULL AUTO_INCREMENT,
  `name_admin` VARCHAR(45) NULL,
  `ap_admin` VARCHAR(45) NULL,
  `pass_admin` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_admin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `g5-ppc_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g5-ppc_db`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name_user` VARCHAR(45) NULL,
  `ap_user` VARCHAR(45) NULL,
  `pass_user` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `g5-ppc_db`.`lab`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g5-ppc_db`.`lab` (
  `id_lab` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_lab`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `g5-ppc_db`.`computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g5-ppc_db`.`computer` (
  `id_pc` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NULL,
  `id_lab` INT NULL,
  `fecha_mod` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `obs` VARCHAR(45) NULL,
  PRIMARY KEY (`id_pc`),
  CONSTRAINT `laboratorio`
    FOREIGN KEY (`id_lab`)
    REFERENCES `g5-ppc_db`.`lab` (`id_lab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `laboratorio_idx` ON `g5-ppc_db`.`computer` (`id_lab` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `g5-ppc_db`.`admins`
-- -----------------------------------------------------
START TRANSACTION;
USE `g5-ppc_db`;
INSERT INTO `g5-ppc_db`.`admins` (`id_admin`, `name_admin`, `ap_admin`, `pass_admin`) VALUES (1, 'Arturo', 'Velazques', '1');
INSERT INTO `g5-ppc_db`.`admins` (`id_admin`, `name_admin`, `ap_admin`, `pass_admin`) VALUES (2, 'Frank', 'Prado', '2');
INSERT INTO `g5-ppc_db`.`admins` (`id_admin`, `name_admin`, `ap_admin`, `pass_admin`) VALUES (3, 'Daniela', 'Pierola', '3');
INSERT INTO `g5-ppc_db`.`admins` (`id_admin`, `name_admin`, `ap_admin`, `pass_admin`) VALUES (4, 'Flora', 'Mana', '4');

COMMIT;


-- -----------------------------------------------------
-- Data for table `g5-ppc_db`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `g5-ppc_db`;
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (1, 'Alma ', 'Granados', '1');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (2, 'Herminia ', 'Sobrino', '2');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (3, 'Pascual ', 'Cuenca', '3');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (4, 'Faustino ', 'Cordoba', '4');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (5, 'Mirian ', 'Oviedo', '5');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (6, 'Eusebio ', 'Rebollo', '6');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (7, 'Aurora ', 'Arcos', '7');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (8, 'Diego ', 'Chaves', '8');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (9, 'Federico ', 'Herreros', '9');
INSERT INTO `g5-ppc_db`.`users` (`id_user`, `name_user`, `ap_user`, `pass_user`) VALUES (10, 'Josefa ', 'Calle', '10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `g5-ppc_db`.`lab`
-- -----------------------------------------------------
START TRANSACTION;
USE `g5-ppc_db`;
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (1);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (2);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (3);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (4);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (5);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (6);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (7);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (8);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (9);
INSERT INTO `g5-ppc_db`.`lab` (`id_lab`) VALUES (10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `g5-ppc_db`.`computer`
-- -----------------------------------------------------
START TRANSACTION;
USE `g5-ppc_db`;
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (1, 'operativo', 2, DEFAULT, NULL);
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (2, 'defectuosa', 1, DEFAULT, 'HDD estropeado');
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (3, 'operativo', 2, DEFAULT, NULL);
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (4, 'operativo', 1, DEFAULT, NULL);
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (5, 'defectuosa', 3, DEFAULT, 'Error en memoria RAM');
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (6, 'operativo', 3, DEFAULT, NULL);
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (7, 'mantenimiento', 4, DEFAULT, 'Cambiando pila');
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (8, 'operativo', 5, DEFAULT, NULL);
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (9, 'operativo', 2, DEFAULT, NULL);
INSERT INTO `g5-ppc_db`.`computer` (`id_pc`, `estado`, `id_lab`, `fecha_mod`, `obs`) VALUES (10, 'operativo', 6, DEFAULT, NULL);

COMMIT;

