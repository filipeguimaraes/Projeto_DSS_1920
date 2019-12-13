-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MEDIACENTER
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MEDIACENTER
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MEDIACENTER` DEFAULT CHARACTER SET utf8 ;
USE `MEDIACENTER` ;

-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Biblioteca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Biblioteca` (
  `cod` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE INDEX `cod_UNIQUE` (`cod` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Utilizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Utilizador` (
  `email` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Biblioteca_cod` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Utilizador_Biblioteca1_idx` (`Biblioteca_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_Biblioteca1`
    FOREIGN KEY (`Biblioteca_cod`)
    REFERENCES `MEDIACENTER`.`Biblioteca` (`cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Colecao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Colecao` (
  `codColecao` VARCHAR(45) NOT NULL,
  `Biblioteca_cod` VARCHAR(45) NOT NULL,
  `nomeColecao` VARCHAR(45) NULL,
  PRIMARY KEY (`codColecao`),
  UNIQUE INDEX `codColecao_UNIQUE` (`codColecao` ASC) VISIBLE,
  INDEX `fk_Colecao_Biblioteca1_idx` (`Biblioteca_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Colecao_Biblioteca1`
    FOREIGN KEY (`Biblioteca_cod`)
    REFERENCES `MEDIACENTER`.`Biblioteca` (`cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Media` (
  `nomeMedia` VARCHAR(45) NOT NULL,
  `path` VARCHAR(1024) NULL,
  `artista` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nomeMedia`, `artista`),
  UNIQUE INDEX `nomeMedia_UNIQUE` (`nomeMedia` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Utilitario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Utilitario` (
  `emailAdmin` VARCHAR(45) NOT NULL,
  `passAdmin` VARCHAR(45) NOT NULL,
  `pathToMedia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`emailAdmin`),
  UNIQUE INDEX `emailAdmin_UNIQUE` (`emailAdmin` ASC) VISIBLE,
  UNIQUE INDEX `passAdmin_UNIQUE` (`passAdmin` ASC) VISIBLE,
  UNIQUE INDEX `pathToMedia_UNIQUE` (`pathToMedia` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Utilizador_Media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Utilizador_Media` (
  `Utilizador_email` VARCHAR(45) NOT NULL,
  `Media_nomeMedia` VARCHAR(45) NOT NULL,
  `Media_artista` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NULL,
  PRIMARY KEY (`Utilizador_email`, `Media_nomeMedia`, `Media_artista`),
  INDEX `fk_Media_has_Utilizador_Utilizador1_idx` (`Utilizador_email` ASC) VISIBLE,
  INDEX `fk_Media_has_Utilizador_Media1_idx` (`Media_nomeMedia` ASC, `Media_artista` ASC) VISIBLE,
  CONSTRAINT `fk_Media_has_Utilizador_Media1`
    FOREIGN KEY (`Media_nomeMedia` , `Media_artista`)
    REFERENCES `MEDIACENTER`.`Media` (`nomeMedia` , `artista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Media_has_Utilizador_Utilizador1`
    FOREIGN KEY (`Utilizador_email`)
    REFERENCES `MEDIACENTER`.`Utilizador` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEDIACENTER`.`Colecao_Media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEDIACENTER`.`Colecao_Media` (
  `Media_nomeMedia` VARCHAR(45) NOT NULL,
  `Media_artista` VARCHAR(45) NOT NULL,
  `Colecao_codColecao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Media_nomeMedia`, `Media_artista`, `Colecao_codColecao`),
  INDEX `fk_Media_has_Colecao_Colecao1_idx` (`Colecao_codColecao` ASC) VISIBLE,
  INDEX `fk_Media_has_Colecao_Media1_idx` (`Media_nomeMedia` ASC, `Media_artista` ASC) VISIBLE,
  CONSTRAINT `fk_Media_has_Colecao_Media1`
    FOREIGN KEY (`Media_nomeMedia` , `Media_artista`)
    REFERENCES `MEDIACENTER`.`Media` (`nomeMedia` , `artista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Media_has_Colecao_Colecao1`
    FOREIGN KEY (`Colecao_codColecao`)
    REFERENCES `MEDIACENTER`.`Colecao` (`codColecao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
