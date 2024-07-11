-- member 테이블 생성
create table member (
	id int auto_increment not null primary key,
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
-- member 테이블 password 컬럼 삭제
alter table member drop column password;
alter table member add update_date date;

-- board 테이블 생성
CREATE TABLE board (
	id int auto_increment not null primary key comment 'PK',
    title varchar(500) not null comment '제목',
    content varchar(3000) not null comment '내용',
    email varchar(30) not null comment '작성자',
    nick_name varchar(30) not null comment '닉네임',
    delete_yn varchar(5) not null comment '삭제 여부',
    create_date datetime not null DEFAULT current_timestamp() comment '생성일시',
	update_date datetime default null comment '최종 수정일시'
) comment '게시판';

insert into board (id, title, content, email, nick_name, delete_yn, create_date, update_date) values (1, 'test', 'content', 'asd@naver.com', 'nick', 'n', sysdate(), null);

-- board 테이블 nick_name 컬럼 삭제. member 테이블에서 회원 email과 작성자 email이 같을 때 nick_name 가져옴
alter table board drop column nick_name;

-- member 테이블 member_info로 테이블명 변경
rename table member to member_info;

-- member_info 테이블
create table member_info (
	id int auto_increment not null primary key,
	email varchar(100) not null,
	name varchar(100),
    provider varchar(50),
    nick_name varchar(200),
    picture varchar(300),
    role varchar(50),
	create_date timestamp default current_timestamp,
    update_date date
);

-- 조회수 추가
alter table board add column view_count int default 0;








-- 테이블 생성 최종
-- board 테이블 생성
CREATE TABLE board (
	id int auto_increment not null primary key comment 'PK',
    title varchar(500) not null comment '제목',
    content varchar(3000) not null comment '내용',
    email varchar(30) not null comment '작성자',
    delete_yn varchar(5) not null comment '삭제 여부',
    create_date datetime not null DEFAULT current_timestamp() comment '생성일시',
	update_date datetime default null comment '최종 수정일시',
	view_count int default 0 comment '조회수'
) comment '게시판';

-- member_info 테이블
create table member_info (
	id int auto_increment not null primary key,
	email varchar(100) not null,
	name varchar(100),
    provider varchar(50),
    nick_name varchar(200),
    picture varchar(300),
    role varchar(50),
	create_date timestamp default current_timestamp,
    update_date date
);
