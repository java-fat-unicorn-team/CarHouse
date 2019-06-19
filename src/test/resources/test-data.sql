INSERT INTO `user_table` (`user_name`, `phone_number`, `login`, `password`)
VALUES ('Oleg', '623-44-21', 'oleg97', '12321');

INSERT INTO `fuel_type` (`fuel_type`)
VALUES ('Bensin');
INSERT INTO `fuel_type` (`fuel_type`)
VALUES ('Diesel');
INSERT INTO `fuel_type` (`fuel_type`)
VALUES ('Gasoline');
INSERT INTO `fuel_type` (`fuel_type`)
VALUES ('Electric');

INSERT INTO `transmission` (`transmission`)
VALUES ('Automatic');
INSERT INTO `transmission` (`transmission`)
VALUES ('Manual');

INSERT INTO `car_make` (`car_make`)
VALUES ('Mercedes');
INSERT INTO `car_make` (`car_make`)
VALUES ('BMW');

INSERT INTO `car_model` (`car_model`, `car_make_id`)
VALUES ('C63_AMG', '1');
INSERT INTO `car_model` (`car_model`, `car_make_id`)
VALUES ('M5', '2');
INSERT INTO `car_model` (`car_model`, `car_make_id`)
VALUES ('M8', '2');

INSERT INTO `car` (`year`, `mileage`, `fuel_type_id`, `transmission_id`, `car_model_id`)
VALUES ('2017', '90300', '1', '1', '1');
INSERT INTO `car` (`year`, `mileage`, `fuel_type_id`, `transmission_id`, `car_model_id`)
VALUES ('2016', '110300', '1', '2', '2');
INSERT INTO `car` (`year`, `mileage`, `fuel_type_id`, `transmission_id`, `car_model_id`)
VALUES ('2015', '130300', '2', '1', '1');
INSERT INTO `car` (`year`, `mileage`, `fuel_type_id`, `transmission_id`, `car_model_id`)
VALUES ('2017', '70300', '1', '1', '3');
INSERT INTO `car` (`year`, `mileage`, `fuel_type_id`, `transmission_id`, `car_model_id`)
VALUES ('2017', '70300', '2', '2', '1');

INSERT INTO `car_feature` (`car_feature`, `car_id`)
VALUES ('Winter tires', '1');
INSERT INTO `car_feature` (`car_feature`, `car_id`)
VALUES ('air conditioning', '1');
INSERT INTO `car_feature` (`car_feature`, `car_id`)
VALUES ('Leather interior', '2');
INSERT INTO `car_feature` (`car_feature`, `car_id`)
VALUES ('Csenon', '1');

INSERT INTO `car_sale` (`price`, `date`, `user_id`, `car_id`)
VALUES ('36800', '2019-06-01 00:00:00', '1', '1');
INSERT INTO `car_sale` (`price`, `date`, `user_id`, `car_id`)
VALUES ('40500', '2019-06-07 00:00:00', '1', '2');
INSERT INTO `car_sale` (`price`, `date`, `user_id`, `car_id`)
VALUES ('26800', '2019-06-11 00:00:00', '1', '3');
INSERT INTO `car_sale` (`price`, `date`, `user_id`, `car_id`)
VALUES ('57900', '2019-06-05 00:00:00', '1', '4');

INSERT INTO `comment` (`user_name`, `comment`, `car_sale_id`)
VALUES ('Pasha', 'Good car', '4');
INSERT INTO `comment` (`user_name`, `comment`, `car_sale_id`)
VALUES ('Alex', 'Cool', '1');
INSERT INTO `comment` (`user_name`, `comment`, `car_sale_id`)
VALUES ('Michael', 'Rubbish', '4');
INSERT INTO `comment` (`user_name`, `comment`, `car_sale_id`)
VALUES ('David', 'Nice car', '2');






