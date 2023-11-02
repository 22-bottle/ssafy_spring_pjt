use enjoytrip;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`user` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(100) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `salt` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_id`));
insert into `enjoytrip`.`user` (user_id, user_name, user_password, email_id, email_domain, join_date)
values 	('ssafy', '김싸피', '1234', 'ssafy', 'ssafy.com', now()), 
		('admin', '관리자', '1234', 'admin', 'google.com', now());
        
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `comment_cnt` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_user_user_id_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`));
    
CREATE TABLE IF NOT EXISTS `enjoytrip`.`comment` (
	`comment_no` INT NOT NULL AUTO_INCREMENT,
    `article_no` INT NOT NULL,
	`user_id` VARCHAR(16) NOT NULL,
	`comment_content` VARCHAR(2000) NULL DEFAULT NULL,
    `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`comment_no`));


select * from board;
select * from comment;
select * from user where user_id="trip";	

select user_password, salt from user where user_id = '12';
 
delete from user where user_id='ssafy';

