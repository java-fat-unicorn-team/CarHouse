DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `car_sale`;
DROP TABLE IF EXISTS `user_table`;
DROP TABLE IF EXISTS `car_has_car_feature`;
DROP TABLE IF EXISTS `car_feature`;
DROP TABLE IF EXISTS `car`;
DROP TABLE IF EXISTS `car_model`;
DROP TABLE IF EXISTS `car_make`;
DROP TABLE IF EXISTS `transmission`;

DROP TABLE IF EXISTS `fuel_type`;

CREATE TABLE `user_table`
(
    `user_id`      int(11)     NOT NULL AUTO_INCREMENT,
    `user_name`    varchar(45) NOT NULL,
    `phone_number` varchar(45) NOT NULL,
    `login`        varchar(45) NOT NULL,
    `password`     varchar(45) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `loggin_UNIQUE` (`login`)
);

CREATE TABLE `fuel_type`
(
    `fuel_type_id` int(11)     NOT NULL AUTO_INCREMENT,
    `fuel_type`    varchar(45) NOT NULL,
    PRIMARY KEY (`fuel_type_id`)
);

CREATE TABLE `transmission`
(
    `transmission_id` int(11)     NOT NULL AUTO_INCREMENT,
    `transmission`    varchar(45) NOT NULL,
    PRIMARY KEY (`transmission_id`)
);

CREATE TABLE `car_make`
(
    `car_make_id` int(11)     NOT NULL AUTO_INCREMENT,
    `car_make`    varchar(45) NOT NULL,
    PRIMARY KEY (`car_make_id`)
);

CREATE TABLE `car_model`
(
    `car_model_id` int(11)     NOT NULL AUTO_INCREMENT,
    `car_model`    varchar(45) NOT NULL,
    `car_make_id`  int(11)     NOT NULL,
    PRIMARY KEY (`car_model_id`),
    CONSTRAINT `fk_car_make` FOREIGN KEY (`car_make_id`) REFERENCES `car_make` (`car_make_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `car`
(
    `car_id`          int(11) NOT NULL AUTO_INCREMENT,
    `year`            date    NOT NULL,
    `mileage`         int(11) DEFAULT NULL,
    `fuel_type_id`    int(11) NOT NULL,
    `transmission_id` int(11) NOT NULL,
    `car_model_id`    int(11) NOT NULL,
    PRIMARY KEY (`car_id`),
    CONSTRAINT `car_model` FOREIGN KEY (`car_model_id`) REFERENCES `car_model` (`car_model_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_fuel_type` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_type` (`fuel_type_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_transmission` FOREIGN KEY (`transmission_id`) REFERENCES `transmission` (`transmission_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `car_feature`
(
    `car_feature_id` int(11)     NOT NULL AUTO_INCREMENT,
    `car_feature`    varchar(45) NOT NULL,
    PRIMARY KEY (`car_feature_id`)
);

CREATE TABLE `car_has_car_feature`
(
    `car_id`         INT(11) NOT NULL,
    `car_feature_id` INT(11) NOT NULL,
    PRIMARY KEY (`car_id`, `car_feature_id`),
    CONSTRAINT `fk_car_has_car_feature_car1` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `fk_car_has_car_feature_car_feature1` FOREIGN KEY (`car_feature_id`)
        REFERENCES `car_feature` (`car_feature_id`) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE `car_sale`
(
    `car_sale_id` int(11)     NOT NULL AUTO_INCREMENT,
    `price`       varchar(45) NOT NULL,
    `date`        datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id`     int(11)     NOT NULL,
    `car_id`      int(11)     NOT NULL,
    `image_name`  varchar(45) NOT NULL DEFAULT 'default',
    PRIMARY KEY (`car_sale_id`),
    CONSTRAINT `fk_car_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`)
        ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE `comment`
(
    `comment_id`  int(11)      NOT NULL AUTO_INCREMENT,
    `user_name`   varchar(45)  NOT NULL,
    `comment`     varchar(225) NOT NULL,
    `car_sale_id` int(11)      NOT NULL,
    PRIMARY KEY (`comment_id`),
    CONSTRAINT `fk_car_sale_id` FOREIGN KEY (`car_sale_id`) REFERENCES `car_sale` (`car_sale_id`)
        ON DELETE CASCADE ON UPDATE NO ACTION
);
