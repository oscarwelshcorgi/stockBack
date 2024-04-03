-- member 테이블 생성
create table member (
	id int auto_increment primary key,
	email varchar(100) not null,
	password varchar(100) not null,
	name varchar(100),
    provider varchar(50),
    nick_name varchar(200),
    picture varchar(300),
    role varchar(50),
	create_date timestamp default current_timestamp
);

-- member 테이블 수정
alter table member drop column password;
alter table member add update_date date;

-- board 테이블 생성
CREATE TABLE `board` (
    `id`            bigint(20)    NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `title`         varchar(100)  NOT NULL COMMENT '제목',
    `content`       varchar(3000) NOT NULL COMMENT '내용',
    `email`        varchar(30)   NOT NULL COMMENT '작성자',
    `nick_name`		varchar(30) NOT NULL COMMENT '닉네임',
    `delete_yn`     varchar(5)    NOT NULL COMMENT '삭제 여부',
    `create_date`  datetime      NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
    `update_date` datetime               DEFAULT NULL COMMENT '최종 수정일시',
    PRIMARY KEY (`id`)
) COMMENT '게시판';

insert into board (id, title, content, email, nick_name, delete_yn, create_date, update_date) values (1, 'test', 'content', 'asd@naver.com', 'nick', 'n', sysdate(), null);