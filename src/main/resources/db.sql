use my;

CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `date` date DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `stamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com'),
(6, 'haha', 10, 'fw@r23.cn');


drop table if exists  book_order;

CREATE TABLE `book_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `bid` int(11) NOT NULL COMMENT '书籍id',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订阅日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订阅表';
insert into book_order values
(null,1,1,null),
(null,1,2,null),
(null,1,3,null),
(null,1,4,null),
(null,2,2,null),
(null,2,3,null),
(null,3,4,null);

drop table if exists book;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(11) NOT NULL COMMENT '书名',
  `price` varchar(5) COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍表';
insert into book values
(null,'java内幕','100'),
(null,'高性能mysql','50'),
(null,'spring源码解析','80'),
(null,'python入门','60');