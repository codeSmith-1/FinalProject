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
  `room_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kid` ;

CREATE TABLE IF NOT EXISTS `kid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `classroom_id` INT NOT NULL,
  `image_url` VARCHAR(1500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_child_classroom1_idx` (`classroom_id` ASC),
  CONSTRAINT `fk_child_classroom1`
    FOREIGN KEY (`classroom_id`)
    REFERENCES `classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_report` ;

CREATE TABLE IF NOT EXISTS `daily_report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time_in` DATETIME NULL,
  `time_out` DATETIME NULL,
  `diaper_low` TINYINT NULL,
  `wipes_low` TINYINT NULL,
  `activities` VARCHAR(700) NULL,
  `notes` TEXT NULL,
  `child_id` INT NOT NULL,
  `report_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_day_child1_idx` (`child_id` ASC),
  CONSTRAINT `fk_day_child1`
    FOREIGN KEY (`child_id`)
    REFERENCES `kid` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food` ;

CREATE TABLE IF NOT EXISTS `food` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lunch` VARCHAR(250) NULL,
  `am_snack` VARCHAR(250) NULL,
  `pm_snack` VARCHAR(250) NULL,
  `other` VARCHAR(250) NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meals_day_idx` (`day_id` ASC),
  CONSTRAINT `fk_meals_day`
    FOREIGN KEY (`day_id`)
    REFERENCES `daily_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nap`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nap` ;

CREATE TABLE IF NOT EXISTS `nap` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time_start` DATETIME NULL,
  `time_finish` DATETIME NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nap_day1_idx` (`day_id` ASC),
  CONSTRAINT `fk_nap_day1`
    FOREIGN KEY (`day_id`)
    REFERENCES `daily_report` (`id`)
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
  `classroom_id` INT NULL,
  `image_url` VARCHAR(1500) NULL,
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
-- Table `bathroom_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bathroom_type` ;

CREATE TABLE IF NOT EXISTS `bathroom_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bathroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bathroom` ;

CREATE TABLE IF NOT EXISTS `bathroom` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(150) NULL,
  `bathroom_time` DATETIME NULL,
  `day_id` INT NOT NULL,
  `staff_id` INT NOT NULL,
  `bathroom_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bathroom_day1_idx` (`day_id` ASC),
  INDEX `fk_bathroom_staff1_idx` (`staff_id` ASC),
  INDEX `fk_bathroom_bathroom_type1_idx` (`bathroom_type_id` ASC),
  CONSTRAINT `fk_bathroom_day1`
    FOREIGN KEY (`day_id`)
    REFERENCES `daily_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bathroom_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bathroom_bathroom_type1`
    FOREIGN KEY (`bathroom_type_id`)
    REFERENCES `bathroom_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(200) NOT NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adult`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adult` ;

CREATE TABLE IF NOT EXISTS `adult` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(12) NOT NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `image_url` VARCHAR(1500) NULL,
  `emergency_contact` TINYINT NULL,
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
-- Table `mood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mood` ;

CREATE TABLE IF NOT EXISTS `mood` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guardian`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guardian` ;

CREATE TABLE IF NOT EXISTS `guardian` (
  `adult_id` INT NOT NULL,
  `kid_id` INT NOT NULL,
  `relationship` VARCHAR(45) NULL,
  PRIMARY KEY (`adult_id`, `kid_id`),
  INDEX `fk_guardian_has_child_child1_idx` (`kid_id` ASC),
  INDEX `fk_guardian_has_child_guardian1_idx` (`adult_id` ASC),
  CONSTRAINT `fk_guardian_has_child_guardian1`
    FOREIGN KEY (`adult_id`)
    REFERENCES `adult` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guardian_has_child_child1`
    FOREIGN KEY (`kid_id`)
    REFERENCES `kid` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mood_entry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mood_entry` ;

CREATE TABLE IF NOT EXISTS `mood_entry` (
  `mood_id` INT NOT NULL,
  `daily_report_id` INT NOT NULL,
  `entered_at` DATETIME NULL,
  PRIMARY KEY (`mood_id`, `daily_report_id`),
  INDEX `fk_mood_has_daily_report_daily_report1_idx` (`daily_report_id` ASC),
  INDEX `fk_mood_has_daily_report_mood1_idx` (`mood_id` ASC),
  CONSTRAINT `fk_mood_has_daily_report_mood1`
    FOREIGN KEY (`mood_id`)
    REFERENCES `mood` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mood_has_daily_report_daily_report1`
    FOREIGN KEY (`daily_report_id`)
    REFERENCES `daily_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `report_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `report_image` ;

CREATE TABLE IF NOT EXISTS `report_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(1500) NULL,
  `daily_report_id` INT NOT NULL,
  `staff_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_report_image_daily_report1_idx` (`daily_report_id` ASC),
  INDEX `fk_report_image_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_report_image_daily_report1`
    FOREIGN KEY (`daily_report_id`)
    REFERENCES `daily_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_report_image_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message_date` DATETIME NULL,
  `content` TEXT NULL,
  `sender_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user1_idx` (`sender_id` ASC),
  INDEX `fk_message_user2_idx` (`recipient_id` ASC),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `user` (`id`)
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
-- Data for table `classroom`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `classroom` (`id`, `room_name`) VALUES (1, 'Fancy Sunshine');
INSERT INTO `classroom` (`id`, `room_name`) VALUES (2, 'Sugar Dash');
INSERT INTO `classroom` (`id`, `room_name`) VALUES (3, 'Midnight Sparkle');
INSERT INTO `classroom` (`id`, `room_name`) VALUES (4, 'Twinkle Wish');

COMMIT;


-- -----------------------------------------------------
-- Data for table `kid`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (1, 'Javier', 'Rodriguez', '2018-07-15', 1, NULL);
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (2, 'Joe', 'Franklin', '2019-03-06', 2, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `daily_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `daily_report` (`id`, `time_in`, `time_out`, `diaper_low`, `wipes_low`, `activities`, `notes`, `child_id`, `report_date`) VALUES (1, '2022-11-20T07:31:21', '2022-11-20T15:28:24', 0, 0, 'Circle Time, Arts & Crafts, Outside Time', 'None', 1, '2022-11-20');
INSERT INTO `daily_report` (`id`, `time_in`, `time_out`, `diaper_low`, `wipes_low`, `activities`, `notes`, `child_id`, `report_date`) VALUES (2, '2022-11-20T07:33:21', '2022-11-20T14:37:21', 0, 1, 'Circle Time, Arts & Crafts, Outside Time', 'N/A', 2, '2022-11-20');
INSERT INTO `daily_report` (`id`, `time_in`, `time_out`, `diaper_low`, `wipes_low`, `activities`, `notes`, `child_id`, `report_date`) VALUES (3, '2022-11-21T07:29:21', '2022-11-21T16:01:21', 1, 0, 'Circle Time, Arts & Crafts, Outside Time', 'Nothing special today', 1, '2022-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `food`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `food` (`id`, `lunch`, `am_snack`, `pm_snack`, `other`, `day_id`) VALUES (1, NULL, 'Cheez Its, Cantelope, Grapes', NULL, NULL, 1);
INSERT INTO `food` (`id`, `lunch`, `am_snack`, `pm_snack`, `other`, `day_id`) VALUES (2, 'Sloppy Joe, Chips, Carrots', NULL, NULL, NULL, 1);
INSERT INTO `food` (`id`, `lunch`, `am_snack`, `pm_snack`, `other`, `day_id`) VALUES (3, NULL, NULL, 'Celery, Peanut Butter, Carrots', NULL, 1);
INSERT INTO `food` (`id`, `lunch`, `am_snack`, `pm_snack`, `other`, `day_id`) VALUES (4, NULL, 'Apple Slices, Wheat Thins', NULL, NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `nap`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `nap` (`id`, `time_start`, `time_finish`, `day_id`) VALUES (1, '2022-11-20T12:01:32', '2022-11-20T12:51:32', 1);
INSERT INTO `nap` (`id`, `time_start`, `time_finish`, `day_id`) VALUES (2, '2022-11-20T12:01:32', '2022-11-20T13:15:32', 2);
INSERT INTO `nap` (`id`, `time_start`, `time_finish`, `day_id`) VALUES (3, '2022-11-21T12:00:15', '2022-11-21T12:45:12', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (1, 'staff', 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 1);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (2, 'staff', 'robros', '$2a$10$4/QJZV7S7wNYeh3CvcHhTOAQ8mIQtf8QlKK4qhZpZBj0Ux3WsL44G', 1);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (3, 'staff', 'anthonyk', '$2a$10$044zw.QPqNMBlzkqOJ4unORvy1DQc2jY/YPCKFMoxWZ/S/5o2cTu6', 1);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (4, 'staff', 'jeremyw', '$2a$10$nmsfDgaAqIkIz2v9T9kau.m9B1yL3uxOUX2URusIGbuOezhCOal/i', 1);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (5, 'user', 'darlenef', '$2a$10$4QG1VKu2HEFKPbMc6Kyb1uZN18A452vJNmyzE8wAbYu306ObgJh.G', 1);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (6, 'user', 'guillermor', '$2a$10$OQyN5W8eDCecBT9PHdJ4wOw1u.kVzZig4VttBjkz0bKs4pVYeh2s.', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `staff`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (1, 'Rob', 'Roselius', 2, 1, NULL);
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (2, 'Anthony', 'King', 3, 2, NULL);
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (3, 'Jeremy', 'Botta', 4, 3, NULL);
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (4, 'William', 'Slaunwhite', 1, 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bathroom_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `bathroom_type` (`id`, `status`) VALUES (1, 'BM');
INSERT INTO `bathroom_type` (`id`, `status`) VALUES (2, 'Wet diaper');
INSERT INTO `bathroom_type` (`id`, `status`) VALUES (3, 'Dry');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bathroom`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `bathroom` (`id`, `description`, `bathroom_time`, `day_id`, `staff_id`, `bathroom_type_id`) VALUES (1, 'N/A', '2022-11-20T09:17:48', 1, 4, 3);
INSERT INTO `bathroom` (`id`, `description`, `bathroom_time`, `day_id`, `staff_id`, `bathroom_type_id`) VALUES (2, 'Blowout', '2022-11-21T14:55:12', 2, 2, 1);
INSERT INTO `bathroom` (`id`, `description`, `bathroom_time`, `day_id`, `staff_id`, `bathroom_type_id`) VALUES (3, 'N/A', '2022-11-21T15:02:55', 2, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, '379 Washington St.', 'Englewood', 'CO', '80110');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (2, '1413 Downing St.', 'Denver', 'CO', '80209');

COMMIT;


-- -----------------------------------------------------
-- Data for table `adult`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (1, 'Darlene', 'Franklin', '303-992-0473', 5, 1, NULL, 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (2, 'Guillermo', 'Rodriguez', '720-552-9462', 6, 2, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mood`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `mood` (`id`, `description`) VALUES (1, 'Happy');
INSERT INTO `mood` (`id`, `description`) VALUES (2, 'Fussy');
INSERT INTO `mood` (`id`, `description`) VALUES (3, 'Tired');
INSERT INTO `mood` (`id`, `description`) VALUES (4, 'Quiet');

COMMIT;


-- -----------------------------------------------------
-- Data for table `guardian`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (1, 2, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (2, 1, 'Father');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mood_entry`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `mood_entry` (`mood_id`, `daily_report_id`, `entered_at`) VALUES (1, 1, '2022-11-20T12:34:20');
INSERT INTO `mood_entry` (`mood_id`, `daily_report_id`, `entered_at`) VALUES (2, 2, '2022-11-20T13:01:11');
INSERT INTO `mood_entry` (`mood_id`, `daily_report_id`, `entered_at`) VALUES (3, 3, '2022-11-21T10:10:10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `report_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `report_image` (`id`, `image_url`, `daily_report_id`, `staff_id`, `created_at`) VALUES (1, 'https://cloudfront-us-east-1.images.arcpublishing.com/coindesk/WMXJCFJ3ERCETA6TJNZ5NQPNKA.webp', 1, 2, '2022-11-20T08:45:12');

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `message` (`id`, `message_date`, `content`, `sender_id`, `recipient_id`) VALUES (1, '2022-11-20', 'Hello', 1, 2);

COMMIT;

