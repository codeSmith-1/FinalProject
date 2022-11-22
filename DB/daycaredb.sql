-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema daycaredb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `daycaredb` ;

-- -----------------------------------------------------
-- Schema daycaredb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `daycaredb` DEFAULT CHARACTER SET utf8 ;
USE `daycaredb` ;

-- -----------------------------------------------------
-- Table `classroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `classroom` ;

CREATE TABLE IF NOT EXISTS `classroom` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `child`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `child` ;

CREATE TABLE IF NOT EXISTS `child` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `classroom_id` INT NOT NULL,
  `child_guarding_many_to_many_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_child_classroom1_idx` (`classroom_id` ASC),
  CONSTRAINT `fk_child_classroom1`
    FOREIGN KEY (`classroom_id`)
    REFERENCES `classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `day`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `day` ;

CREATE TABLE IF NOT EXISTS `day` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time_in` DATETIME(1) NULL,
  `time_out` DATETIME(1) NULL,
  `diaper_low` TINYINT(1) NULL,
  `wipes_low` TINYINT(1) NULL,
  `activities` VARCHAR(700) NULL,
  `notes` VARCHAR(1500) NULL,
  `child_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_day_child1_idx` (`child_id` ASC),
  CONSTRAINT `fk_day_child1`
    FOREIGN KEY (`child_id`)
    REFERENCES `child` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meals` ;

CREATE TABLE IF NOT EXISTS `meals` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `meal` VARCHAR(250) NULL,
  `am_snack` VARCHAR(250) NULL,
  `pm_snack` VARCHAR(250) NULL,
  `other` VARCHAR(250) NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meals_day_idx` (`day_id` ASC),
  CONSTRAINT `fk_meals_day`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nap`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nap` ;

CREATE TABLE IF NOT EXISTS `nap` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time_start` DATETIME(1) NULL,
  `time_finish` DATETIME(1) NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nap_day1_idx` (`day_id` ASC),
  CONSTRAINT `fk_nap_day1`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `staff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `staff` ;

CREATE TABLE IF NOT EXISTS `staff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `classroom_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_staff_user1_idx` (`user_id` ASC),
  INDEX `fk_staff_classroom1_idx` (`classroom_id` ASC),
  CONSTRAINT `fk_staff_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_staff_classroom1`
    FOREIGN KEY (`classroom_id`)
    REFERENCES `classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `staff_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_address_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guardian`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guardian` ;

CREATE TABLE IF NOT EXISTS `guardian` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `relationship` VARCHAR(45) NOT NULL,
  `phone_number` INT(9) NOT NULL,
  `user_id` INT NOT NULL,
  `child_guarding_many_to_many_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_guardian_user1_idx` (`user_id` ASC),
  INDEX `fk_guardian_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_guardian_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guardian_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Payment` ;

CREATE TABLE IF NOT EXISTS `Payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `current` TINYINT(1) NULL,
  `guardian_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Payment_guardian1_idx` (`guardian_id` ASC),
  CONSTRAINT `fk_Payment_guardian1`
    FOREIGN KEY (`guardian_id`)
    REFERENCES `guardian` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bathroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bathroom` ;

CREATE TABLE IF NOT EXISTS `bathroom` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `wet` VARCHAR(45) NULL,
  `dry` VARCHAR(45) NULL,
  `bm` VARCHAR(45) NULL,
  `time` DATETIME(1) NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bathroom_day1_idx` (`day_id` ASC),
  CONSTRAINT `fk_bathroom_day1`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mood` ;

CREATE TABLE IF NOT EXISTS `mood` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `happy` TINYINT(1) NULL,
  `fussy` TINYINT(1) NULL,
  `quiet` TINYINT(1) NULL,
  `sad` TINYINT(1) NULL,
  `sleepy` TINYINT(1) NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mood_day1_idx` (`day_id` ASC),
  CONSTRAINT `fk_mood_day1`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guardian_has_child`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guardian_has_child` ;

CREATE TABLE IF NOT EXISTS `guardian_has_child` (
  `guardian_id` INT NOT NULL,
  `child_id` INT NOT NULL,
  PRIMARY KEY (`guardian_id`, `child_id`),
  INDEX `fk_guardian_has_child_child1_idx` (`child_id` ASC),
  INDEX `fk_guardian_has_child_guardian1_idx` (`guardian_id` ASC),
  CONSTRAINT `fk_guardian_has_child_guardian1`
    FOREIGN KEY (`guardian_id`)
    REFERENCES `guardian` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guardian_has_child_child1`
    FOREIGN KEY (`child_id`)
    REFERENCES `child` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `picture` ;

CREATE TABLE IF NOT EXISTS `picture` (
  `id` INT NOT NULL,
  `picture_url` VARCHAR(400) NULL,
  `child_id` INT NOT NULL,
  `guardian_id` INT NOT NULL,
  `staff_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_picture_child1_idx` (`child_id` ASC),
  INDEX `fk_picture_guardian1_idx` (`guardian_id` ASC),
  INDEX `fk_picture_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_picture_child1`
    FOREIGN KEY (`child_id`)
    REFERENCES `child` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_picture_guardian1`
    FOREIGN KEY (`guardian_id`)
    REFERENCES `guardian` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_picture_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_day`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_day` ;

CREATE TABLE IF NOT EXISTS `user_has_day` (
  `user_id` INT NOT NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `day_id`),
  INDEX `fk_user_has_day_day1_idx` (`day_id` ASC),
  INDEX `fk_user_has_day_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_day_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_day_day1`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS daycareuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'daycareuser'@'localhost' IDENTIFIED BY 'daycareuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'daycareuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (1, 'null', 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 1);

COMMIT;

