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
  `classroom_id` INT NULL,
  `image_url` VARCHAR(2500) NULL,
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
  `kid_id` INT NOT NULL,
  `report_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_day_child1_idx` (`kid_id` ASC),
  CONSTRAINT `fk_day_child1`
    FOREIGN KEY (`kid_id`)
    REFERENCES `kid` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food_type` ;

CREATE TABLE IF NOT EXISTS `food_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food` ;

CREATE TABLE IF NOT EXISTS `food` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NULL,
  `day_id` INT NOT NULL,
  `food_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meals_day_idx` (`day_id` ASC),
  INDEX `fk_food_food_type1_idx` (`food_type_id` ASC),
  CONSTRAINT `fk_meals_day`
    FOREIGN KEY (`day_id`)
    REFERENCES `daily_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_food_type1`
    FOREIGN KEY (`food_type_id`)
    REFERENCES `food_type` (`id`)
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
  `image_url` VARCHAR(2500) NULL,
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
  `image_url` VARCHAR(2500) NULL,
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
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (1, 'Javier', 'Rodriguez', '2018-07-15', 1, 'https://images.unsplash.com/photo-1630304565761-d6f8d5a0facd?ixlib=rb-4.0.3&ixid=MnwxM[…]DB8MHxzZWFyY2h8NXx8Y3V0ZSUyMGJhYnl8ZW58MHx8MHx8&w=1000&q=80');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (2, 'Joe', 'Franklin', '2019-03-06', 2, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Summer%202016-17/MS_pre-boy-2.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (3, 'Liam', 'Murray', '2019-04-02', 3, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Spring2023/Lifetouch-Preschool-image-spring-kinder-Follow%20Your%20Dreams-4.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (4, 'Noah', 'Ellis', '2020-11-01', 4, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Summer%202016-17/MS_Pre-boy-(3-pose).jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (5, 'Olivia', 'Manning', '2019-06-08', 1, 'https://media.istockphoto.com/id/1007786322/photo/is-it-delicious.jpg?b=1&s=170667a&w=0&k=20&c=hk1MyT4dNX1ophb--pVKvIHtO5tv2BYOnHd1N0Dfeco=');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (6, 'Emma', 'Gaines', '2018-02-04', 2, 'https://media.istockphoto.com/id/1429905621/photo/adorable-and-happy-asian-baby.jpg?b=1[…]7a&w=0&k=20&c=QoQoavGAjyJIII9VfVwiZJ_WW6ayfQhraO2uP-cRGcE=');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (7, 'Charlotte', 'Kirby', '2017-08-25', 3, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Summer%202016-17/MS_Toddler-girl.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (8, 'Ameilia', 'Arak', '2018-04-17', 4, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Summer%202016-17/MS_Infant--non-sit-.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (9, 'Ava', 'Curtis', '2019-12-20', 1, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Fall2022/Teal_3.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (10, 'Elija', 'Welch', '2017-01-01', 2, 'https://images.unsplash.com/photo-1543467214-b247439848dc?ixlib=rb-4.0.3&ixid=MnwxMjA3[…]jBiYWJ5fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (11, 'Sophia', 'Davidson', '2018-03-30', 3, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Spring2023/Lifetouch-Preschool-image-spring-kinder-cream%20studio%20color-1.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (12, 'James', 'McPherson', '2016-11-19', 4, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Fall2022/Shiplap_3.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (13, 'William', 'Parks', '2017-10-21', 1, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Fall2022/Teal_2.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (14, 'Lucas', 'Wattles', '2018-02-18', 2, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Holiday2022/Holiday%20Teal_5.jpg');
INSERT INTO `kid` (`id`, `first_name`, `last_name`, `birthday`, `classroom_id`, `image_url`) VALUES (15, 'Mia', 'Weinberg', '2016-03-27', 3, 'https://preschoolportraits.lifetouch.com/cmsimages/2/PortraitGalleries/Spring2023/Lifetouch-Preschool-image-spring-kinder-Follow%20Your%20Dreams-2.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `daily_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `daily_report` (`id`, `time_in`, `time_out`, `diaper_low`, `wipes_low`, `activities`, `notes`, `kid_id`, `report_date`) VALUES (1, '2022-11-20T07:31:21', '2022-11-20T15:28:24', 0, 0, 'Circle Time, Arts & Crafts, Outside Time', 'None', 1, '2022-11-20');
INSERT INTO `daily_report` (`id`, `time_in`, `time_out`, `diaper_low`, `wipes_low`, `activities`, `notes`, `kid_id`, `report_date`) VALUES (2, '2022-11-20T07:33:21', '2022-11-20T14:37:21', 0, 1, 'Circle Time, Arts & Crafts, Outside Time', 'N/A', 2, '2022-11-20');
INSERT INTO `daily_report` (`id`, `time_in`, `time_out`, `diaper_low`, `wipes_low`, `activities`, `notes`, `kid_id`, `report_date`) VALUES (3, '2022-11-21T07:29:21', '2022-11-21T16:01:21', 1, 0, 'Circle Time, Arts & Crafts, Outside Time', 'Nothing special today', 1, '2022-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `food_type` (`id`, `name`) VALUES (1, 'Morning Snack');
INSERT INTO `food_type` (`id`, `name`) VALUES (2, 'Lunch');
INSERT INTO `food_type` (`id`, `name`) VALUES (3, 'Afternoon Snack');
INSERT INTO `food_type` (`id`, `name`) VALUES (4, 'Other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `food`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (1, 'Cheez Its, Cantelope, Grapes', 1, 1);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (2, 'Sloppy Joe, Chips, Carrots', 1, 2);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (3, 'Celery, Peanut Butter, Carrots', 1, 3);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (4, 'Cheez Its, Cantelope, Grapes', 2, 1);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (5, 'Sloppy Joe, Chips, Carrots', 2, 2);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (6, 'Celery, Peanut Butter, Carrots', 2, 3);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (7, 'Cheez Its, Cantelope, Grapes', 3, 1);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (8, 'Sloppy Joe, Chips, Carrots', 3, 2);
INSERT INTO `food` (`id`, `description`, `day_id`, `food_type_id`) VALUES (9, 'Celery, Peanut Butter, Carrots', 3, 3);

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
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (7, 'user', 'katem', '$2a$10$FUEuT849M22aENGPk5oCEOkterA3s2BMxP8eRNGj6WBWvFkwyBjmi', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (8, 'user', 'adame', 'broken', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (9, 'user', 'dannym', '$2a$10$FCvB1Qyt9VSnGW56SWndiuA/61gZ5Az6kUaCa0H2thk3HdKq.Mlne', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (10, 'user', 'candiceg', '$2a$10$njluYXPSx./PZ8fEhuvUMeaKjn8WjQu0hpXkYZKXzngWCh064V/ae', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (11, 'user', 'matthewk', '$2a$10$TG3Rj5s3S1kiVPWLbs6fg.e/UTQZfpYcNXBtNwciFLGDQ4lqAAgGu', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (12, 'user', 'jacoba', '$2a$10$7NG50omMR2bF6FG71FuDHuOxVCBTft.50.J6rx3elsBUThZo65M3a', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (13, 'user', 'michaelac', '$2a$10$N/ZHFsXqw8/9k1t/b2stVeEjMVCNhI66bo8iVyFyCpeQLwzpsJNna', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (14, 'user', 'jackiew', '$2a$10$MhDpthRGnyFJ7eo4xoIto.QkBWmZcEEQqxg0wTLwgFS3jE8LdS2x2', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (15, 'user', 'jessicad', '$2a$10$n2P3phT7Ov2Sf4RCKPfnruEq8yTN7bYhioQTTurvmhkal0t/SDqly', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (16, 'user', 'jenw', '$2a$10$KwSOd/H227poI7kuyLBqO.zT0aH174PQTgyh411WpffFTUuSG3x2C', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (17, 'user', 'alexw', '$2a$10$ppuNJrrk8Hd09ffZknzNBuOE4yTycxT/.WCPTHaNYFkvWFtHMvG9C', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (18, 'user', 'sandym', '$2a$10$fQXUiS6JIgyt/FfyxPwZ5O7/WKCWg.YdHEW0iRsqHV2YGnOaqF.7a', NULL);
INSERT INTO `user` (`id`, `role`, `username`, `password`, `enabled`) VALUES (19, 'user', 'carrolp', '$2a$10$77rT/DI//H0C6oVyLRYeleqEYNSzJLQLB0nl4Cjef4ZPDzZXvZWJK', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `staff`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (1, 'Rob', 'Roselius', 2, 1, 'https://media-exp1.licdn.com/dms/image/C4E03AQHFQsJazXpfSg/profile-displayphoto-shrink_[…]96000&v=beta&t=Bzf3fCNlZ_0tGEuN3PbbCWe-NERIb19WnHgPIVGYUy0');
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (2, 'Anthony', 'King', 3, 2, 'https://media-exp1.licdn.com/dms/image/C4E03AQEvtAjjQLPslA/profile-displayphoto-shrink_[…]96000&v=beta&t=SqlJTn25RraKALDNO5q3Geh8bVG4pHL1kCW-9VgzanA');
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (3, 'Jeremy', 'Botta', 4, 3, 'https://media-exp1.licdn.com/dms/image/C4E03AQGjwpf3Xsprbw/profile-displayphoto-shrink_200_200/0/1532485247024?e=1675296000&v=beta&t=3eScXSdWN-nu7_sWq9uvS8YFZf5HROSNyLem3IUkwJY');
INSERT INTO `staff` (`id`, `first_name`, `last_name`, `user_id`, `classroom_id`, `image_url`) VALUES (4, 'William', 'Slaunwhite', 1, 4, 'https://media-exp1.licdn.com/dms/image/C5603AQEBJwFVonEv7A/profile-displayphoto-shrink_[…]96000&v=beta&t=qPAekFu6pKLV_7k0huNdaqKqTecM-K7AIvuBMis8xOA');

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
INSERT INTO `bathroom` (`id`, `description`, `bathroom_time`, `day_id`, `staff_id`, `bathroom_type_id`) VALUES (4, 'N/A', '2022-11-22T09:02:55', 3, 3, 3);
INSERT INTO `bathroom` (`id`, `description`, `bathroom_time`, `day_id`, `staff_id`, `bathroom_type_id`) VALUES (5, 'N/A', '2022-11-22T15:02:55', 3, 4, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, '379 Washington St.', 'Englewood', 'CO', '80110');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (2, '1413 Downing St.', 'Denver', 'CO', '80209');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (3, '43 S Ogden St', 'Denver', 'CO', '80209');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (4, '1061 S Downing St', 'Denver', 'CO', '80209');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (5, '3901 Elati St', 'Denver', 'CO', '80216');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (6, '2413 Coronado Pkwy', 'Denver', 'CO', '80229');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (7, '1300 S Parker Rd', 'Denver', 'CO', '80321');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (8, '1640 S Quebec Way', 'Denver', 'CO', '80231');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (9, '4725 S Monaco St', 'Denver', 'CO', '80237');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (10, '4550 Kittredge St', 'Denver', 'CO', '80239');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (11, '9100 E Florida Ave', 'Denver', 'CO', '80247');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (12, '1888 N Sherman St', 'Denver', 'CO', '80203');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (13, '1633 Fillmore St', 'Denver', 'CO', '80206');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (14, '9888 E Vassar Dr', 'Denver', 'CO', '80231');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (15, '2825 S Zuni St', 'Denver', 'CO', '80236');

COMMIT;


-- -----------------------------------------------------
-- Data for table `adult`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (1, 'Darlene', 'Franklin', '303-992-0473', 5, 1, 'https://media.istockphoto.com/id/1180927418/photo/successful-mature-woman-looking-at-camera.jpg?s=612x612&w=0&k=20&c=h1iJZZqrxe_egI8oWxJJxVNQNsIKGHumyFe5uAcQ1ZI=', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (2, 'Guillermo', 'Rodriguez', '720-552-9462', 6, 2, 'https://media.istockphoto.com/id/1287598154/photo/passport-photo-of-latin-american-man-with-beard.jpg?s=612x612&w=0&k=20&c=P6pxgoITvaBKtsdTvj-ZFwktOZclZ9uJPIN67ynG4Xk=', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (3, 'Kate', 'Murray', '720-542-9282', 7, 3, 'https://cdn.shopify.com/s/files/1/0253/8956/3978/files/passport_photo_Kristina_Pimenova.jpg?v=1592285068', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (4, 'Adam', 'Ellis', '720-737-1623', 8, 4, 'https://atlanticurologyclinics.com/wp-content/uploads/2019/08/Circ-Adult-Men-1024x607.jpg', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (5, 'Danny', 'Manning', '970-554-7980', 9, 5, 'https://media.istockphoto.com/id/470701814/photo/passport-picture-of-a-guy-in-a-checked-shirt.jpg?s=612x612&w=0&k=20&c=n1XK4OstrSq5zXB042d_07U5oISYuGAeDv8vL6MKCGQ=', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (6, 'Candice', 'Gaines', '970-513-0745', 10, 6, 'https://t4.ftcdn.net/jpg/00/79/74/41/360_F_79744147_CwGIjIoViiIAhlVKgXNjqWtrigLqchie.jpg', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (7, 'Matthew', 'Kirby', '720-663-8138', 11, 7, 'https://upload.wikimedia.org/wikipedia/commons/7/76/Russian_passport_photo.JPG', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (8, 'Jacob', 'Arak', '719-628-0641', 12, 8, 'https://us.123rf.com/450wm/kadettmann/kadettmann2011/kadettmann201100074/kadettmann201100074.jpg?ver=6', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (9, 'Michaela', 'Curtis', '303-722-8895', 13, 9, 'https://t4.ftcdn.net/jpg/01/24/75/07/360_F_124750718_HutULxCzG3XOdWjSubNcdqzkebAYBd1J.jpg', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (10, 'Jackie', 'Welch', '303-729-2741', 14, 10, 'https://media.istockphoto.com/id/916523912/photo/portrait-of-young-african-woman-against-white-background.jpg?s=612x612&w=0&k=20&c=2tcLFR73wfWniMzOld2KNHVDIT3CEOFaxblTU-rs7q4=', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (11, 'Jessica', 'Davidson', '303-858-7851', 15, 11, 'https://cdn.shopify.com/s/files/1/0253/8956/3978/files/Gabbie_Hanna_Instagram_Photo.jpg?v=1592284887', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (12, 'Jen', 'Wattles', '303-254-0638', 16, 12, 'https://static.wikia.nocookie.net/fanon/images/f/f1/HanYeSul-min.jpg/revision/latest?cb=20210924153152', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (13, 'Alex', 'Weinberg', '970-514-4997', 17, 13, 'https://us.123rf.com/450wm/olesiabilkei/olesiabilkei1901/olesiabilkei190100003/olesiabilkei190100003.jpg?ver=6', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (14, 'Sandy', 'McPherson', '720-244-8953', 19, 14, 'https://visafoto.com/img/bangladesh-passport-photo-45x55-mm-example.jpg', 1);
INSERT INTO `adult` (`id`, `first_name`, `last_name`, `phone_number`, `user_id`, `address_id`, `image_url`, `emergency_contact`) VALUES (15, 'Carrol', 'Parks', '303-201-5645', 18, 15, 'https://media.istockphoto.com/id/615279718/photo/businesswoman-portrait-on-white.jpg?s=612x612&w=0&k=20&c=Aa2Vy4faAPe9fAE68Z01jej9YqPqy-RbAteIlF3wcjk=', 1);

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
INSERT INTO `mood` (`id`, `description`) VALUES (5, 'Calm');

COMMIT;


-- -----------------------------------------------------
-- Data for table `guardian`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (1, 2, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (2, 1, 'Father');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (3, 3, 'Aunt');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (4, 4, 'Father');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (5, 5, 'Father');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (6, 6, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (7, 7, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (8, 8, 'Father');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (9, 9, 'Grandma');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (10, 10, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (11, 11, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (12, 14, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (13, 15, 'Uncle');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (14, 12, 'Mother');
INSERT INTO `guardian` (`adult_id`, `kid_id`, `relationship`) VALUES (15, 13, 'Mother');

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
INSERT INTO `report_image` (`id`, `image_url`, `daily_report_id`, `staff_id`, `created_at`) VALUES (2, 'https://winnie.imgix.net/2149cc20-c845-4efc-bc3c-858e9c740821?w=224&h=168&dpr=3&fit=crop&auto=compress', 1, 4, '2022-11-20T10:45:12');
INSERT INTO `report_image` (`id`, `image_url`, `daily_report_id`, `staff_id`, `created_at`) VALUES (3, 'https://health.clevelandclinic.org/wp-content/uploads/sites/3/2014/09/daycare-901208614-770x553-650x428.jpg', 2, 3, '2022-11-20T14:45:12');
INSERT INTO `report_image` (`id`, `image_url`, `daily_report_id`, `staff_id`, `created_at`) VALUES (4, 'https://www.mymdnow.com/wp-content/uploads/2015/03/mdn-blog_daycare-vaccines.jpg', 3, 1, '2022-12-20T07:45:12');

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `daycaredb`;
INSERT INTO `message` (`id`, `message_date`, `content`, `sender_id`, `recipient_id`) VALUES (1, '2022-11-20', 'Hello', 1, 2);

COMMIT;

