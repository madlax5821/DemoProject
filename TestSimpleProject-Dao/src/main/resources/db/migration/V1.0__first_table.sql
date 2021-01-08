create table student (
    id              BIGSERIAL not null,
    name            varchar(32),
    age             integer,
    personality     varchar(255)
);

alter table student add constraint student_pk primary key(id);

create table course (
    id              BIGSERIAL not null,
    name            varchar (32),
    information     varchar (255)
);

alter table course add constraint course_pk primary key(id);

create table student_course (
    student_id  BIGINT not null,
    course_id   BIGINT not null
);

alter table student_course add constraint student_fk foreign key(student_id) references student(id);
alter table student_course add constraint course_fk foreign key(course_id) references course(id);

create table hobbies (
    id          BIGSERIAL not null,
    name        varchar(255),
    student_id  BIGSERIAL
);

alter table hobbies add constraint hobbies_pk primary key (id);
alter table hobbies add constraint student_fk foreign key (student_id) references student (id);

