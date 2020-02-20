
create table books (
book_id int(10)  ,
book_name varchar(100) not null,
book_cat varchar(100) not null,
book_author varchar(100)not null,
book_edition int(10)not null,
book_price int(10),
purchased_date date ,
no_of_bks int(20) not null,
no_of_pgs int(20),
constraint book_id_prk primary key (book_id),
constraint book_edition_ck check(book_edition>=0),
constraint book_id_uk unique(book_id,book_name,book_edition),
constraint no_of_bks_ck check (no_of_bks>0));



create table student(
std_id int(20),
std_name varchar(30) not null,
std_dept varchar(100)not null,
std_dob date,
std_mail_id varchar(50),
std_mob_no bigint(12)not null,
joining_yr int(5)not null,
pass_yr int(5)not null,
constraint std_id_pk primary key (std_id));


create table details(
book_count int(20)not null,
book_id int(20) not null,
std_id int(10) not null,
issue_date date default sysdate,
due_date date default sysdate+15,
returned_date date,
fine_amt int(20),
Status int(1) default 0,
constraint book_id_fk foreign key (book_id) references books(book_id),
constraint book_count_pk primary key(book_count),
constraint std_id_fk foreign key (std_id) references student(std_id));





create table admin (
admin_id int(10),
admin_name varchar(30) not null,
admin_dob date,
admin_mail_id varchar(50)not null,
admin_mob_no bigint(12)not null unique,
admin_password varchar(20)not null,
admin_gender varchar(6) not null,
constraint mail_id_pk primary key(admin_mail_id),
constraint num_check check (length(admin_mob_no)=10));

create sequence id_seq start with 101 increment by 1;
create sequence book_count_sq start with 1 increment by 1;
create sequence std_seq start with 1001 increment by 1;
create sequence admin_id_seq start with 1 increment by 1;
