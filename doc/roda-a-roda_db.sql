-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema roda-a-roda
-- -----------------------------------------------------
-- Implementação do jogo roda a roda jequeti do Silvio Santos
DROP SCHEMA IF EXISTS `roda-a-roda` ;

-- -----------------------------------------------------
-- Schema roda-a-roda
--
-- Implementação do jogo roda a roda jequeti do Silvio Santos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `roda-a-roda` DEFAULT CHARACTER SET utf8 ;
USE `roda-a-roda` ;

-- -----------------------------------------------------
-- Table `roda-a-roda`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roda-a-roda`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_usuario` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `data_cadastro` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roda-a-roda`.`palavra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roda-a-roda`.`palavra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(145) NOT NULL,
  `dica` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `titulo_UNIQUE` (`titulo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roda-a-roda`.`jogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roda-a-roda`.`jogo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pontuacao` DECIMAL NOT NULL,
  `data_inicio` TIMESTAMP NOT NULL,
  `data_fim` TIMESTAMP NULL,
  `estado` TINYINT(1) NOT NULL DEFAULT 1,
  `tempo_jogado` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `palavra_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_jogo_usuario_idx` (`usuario_id` ASC),
  INDEX `fk_jogo_palavra_idx` (`palavra_id` ASC),
  CONSTRAINT `fk_jogo_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `roda-a-roda`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_jogo_palavra`
    FOREIGN KEY (`palavra_id`)
    REFERENCES `roda-a-roda`.`palavra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roda-a-roda`.`jogo_salvo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roda-a-roda`.`jogo_salvo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estado_palavra` VARCHAR(145) NOT NULL,
  `letras_escolhidas` VARCHAR(245) NOT NULL,
  `index_roda` SMALLINT NOT NULL,
  `pontuacao_npc` DECIMAL NOT NULL,
  `jogo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_salvo_jogo_idx` (`jogo_id` ASC),
  CONSTRAINT `fk_salvo_jogo`
    FOREIGN KEY (`jogo_id`)
    REFERENCES `roda-a-roda`.`jogo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
