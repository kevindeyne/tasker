START TRANSACTION;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role` VARCHAR(15) NOT NULL DEFAULT 'DEVELOPER',
  PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE user_role
ADD FOREIGN KEY (user_id) REFERENCES `user`(id);

COMMIT;