CREATE TABLE `book` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(100) NOT NULL,
  `lect_name` varchar(100) NOT NULL,
  `major_id` int(11) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `state` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_date` datetime NOT NULL,
  `image_path` varchar(500) DEFAULT NULL,
  `price` int(11) DEFAULT '0',
  PRIMARY KEY (`b_id`),
  KEY `major_id` (`major_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `majorinfo` (`major_id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)

CREATE TABLE `comment` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_num` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_date` datetime NOT NULL,
  `content` varchar(1024) NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) 

CREATE TABLE `lecture` (
  `lect_id` int(11) NOT NULL AUTO_INCREMENT,
  `lect_name` varchar(100) NOT NULL,
  `prof_name` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `major_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `post_date` datetime NOT NULL,
  PRIMARY KEY (`lect_id`),
  KEY `major_id` (`major_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `majorinfo` (`major_id`),
  CONSTRAINT `lecture_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) 

CREATE TABLE `majorinfo` (
  `major_id` int(11) NOT NULL AUTO_INCREMENT,
  `major` varchar(50) NOT NULL,
  PRIMARY KEY (`major_id`)
) 

CREATE TABLE `notice` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_date` datetime NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(2048) NOT NULL,
  PRIMARY KEY (`n_id`)
)

CREATE TABLE `reply` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reply_date` datetime NOT NULL,
  `content` varchar(1024) NOT NULL,
  PRIMARY KEY (`r_id`),
  KEY `c_id` (`c_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `comment` (`c_id`),
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_pw` varchar(64) NOT NULL,
  `user_gender` tinyint(1) NOT NULL,
  `user_grade` int(11) DEFAULT NULL,
  `user_name` varchar(30) NOT NULL,
  `adminChecked` tinyint(1) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_major_id` (`major_id`),
  CONSTRAINT `user_major_id` FOREIGN KEY (`major_id`) REFERENCES `majorinfo` (`major_id`)
) 