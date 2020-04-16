DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

 CREATE TABLE IF NOT EXISTS `user` (
   user_id INT PRIMARY KEY,
   user_name VARCHAR(50),
   password VARCHAR(50),
   user_role VARCHAR(100)
  );

 CREATE TABLE IF NOT EXISTS `role` (
   role_id INT  PRIMARY KEY,
   role_type VARCHAR(50),
   role_description VARCHAR(150)
  );