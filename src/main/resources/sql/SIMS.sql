CREATE TABLE Course
(
  course_id     VARCHAR(20) NOT NULL
    PRIMARY KEY,
  course_name   VARCHAR(20) NULL,
  course_credit FLOAT       NULL
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE class
(
  class_id    VARCHAR(20) NOT NULL
    PRIMARY KEY,
  class_name  VARCHAR(20) NULL,
  advise_name VARCHAR(20) NULL,
  grade       YEAR        NULL,
  college_id  VARCHAR(20) NULL
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX class_college_college_id_fk
  ON class (college_id);

CREATE TABLE class_title
(
  title_id VARCHAR(20) NOT NULL
    PRIMARY KEY,
  class_id VARCHAR(20) NULL,
  CONSTRAINT FK_Reference_12
  FOREIGN KEY (class_id) REFERENCES class (class_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_12
  ON class_title (class_id);

CREATE TABLE college
(
  college_id   VARCHAR(20) NOT NULL
    PRIMARY KEY,
  college_name VARCHAR(20) NULL
)
  ENGINE = InnoDB
  CHARSET = utf8;

ALTER TABLE class
  ADD CONSTRAINT class_college_college_id_fk
FOREIGN KEY (college_id) REFERENCES college (college_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;

CREATE TABLE home_phone_number
(
  student_id  CHAR(12)    NOT NULL,
  home_number VARCHAR(15) NOT NULL,
  PRIMARY KEY (student_id, home_number)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE personal_phone_number
(
  student_id            CHAR(12)    NOT NULL,
  personal_phone_number VARCHAR(15) NOT NULL,
  PRIMARY KEY (student_id, personal_phone_number)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE section
(
  section_id       VARCHAR(20)                                       NOT NULL,
  section_semester ENUM ('Spring Semester', 'Summer Term', 'Autumn') NOT NULL,
  section_year     YEAR                                              NOT NULL,
  course_id        VARCHAR(20)                                       NOT NULL,
  PRIMARY KEY (section_id, section_semester, section_year, course_id),
  CONSTRAINT FK_Reference_8
  FOREIGN KEY (course_id) REFERENCES Course (course_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_8
  ON section (course_id);

CREATE TABLE student
(
  student_id      CHAR(12)     NOT NULL
    PRIMARY KEY,
  student_birth   DATE         NULL,
  student_name    VARCHAR(20)  NULL,
  student_sex     CHAR(2)      NULL,
  class_id        VARCHAR(20)  NULL,
  student_city    VARCHAR(20)  NULL,
  student_photo   VARCHAR(50)  NULL,
  student_profile VARCHAR(100) NULL,
  CONSTRAINT FK_Reference_1
  FOREIGN KEY (class_id) REFERENCES class (class_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_1
  ON student (class_id);

ALTER TABLE home_phone_number
  ADD CONSTRAINT FK_Reference_3
FOREIGN KEY (student_id) REFERENCES student (student_id);

ALTER TABLE personal_phone_number
  ADD CONSTRAINT FK_Reference_2
FOREIGN KEY (student_id) REFERENCES student (student_id);

CREATE TABLE student_title
(
  title_id   VARCHAR(20) NOT NULL
    PRIMARY KEY,
  student_id CHAR(12)    NULL,
  CONSTRAINT FK_Reference_11
  FOREIGN KEY (student_id) REFERENCES student (student_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_11
  ON student_title (student_id);

CREATE TABLE take
(
  section_id       VARCHAR(20)                                                NOT NULL
    PRIMARY KEY,
  section_semester ENUM ('Spring Semester', 'Summer Term', 'Autumn Semester') NULL,
  secton_year      YEAR                                                       NULL,
  course_id        VARCHAR(20)                                                NULL,
  student_id       CHAR(12)                                                   NULL,
  grade            INT                                                        NULL,
  CONSTRAINT FK_Reference_17
  FOREIGN KEY (section_id, section_semester, secton_year, course_id) REFERENCES section (section_id, section_semester, section_year, course_id),
  CONSTRAINT FK_Reference_10
  FOREIGN KEY (student_id) REFERENCES student (student_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_17
  ON take (section_id, section_semester, secton_year, course_id);

CREATE INDEX FK_Reference_10
  ON take (student_id);

CREATE TABLE teach
(
  section_id       VARCHAR(20)                                                NOT NULL
    PRIMARY KEY,
  section_semester ENUM ('Spring Semester', 'Summer Term', 'Autumn Semester') NULL,
  secton_year      YEAR                                                       NULL,
  course_id        VARCHAR(20)                                                NULL,
  teacher_id       CHAR(12)                                                   NULL,
  class_id         VARCHAR(20)                                                NULL,
  CONSTRAINT FK_Reference_18
  FOREIGN KEY (section_id, section_semester, secton_year, course_id) REFERENCES section (section_id, section_semester, section_year, course_id),
  CONSTRAINT FK_Reference_16
  FOREIGN KEY (class_id) REFERENCES class (class_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_18
  ON teach (section_id, section_semester, secton_year, course_id);

CREATE INDEX FK_Reference_15
  ON teach (teacher_id);

CREATE INDEX FK_Reference_16
  ON teach (class_id);

CREATE TABLE teacher
(
  teacher_id       CHAR(12)                                                           NOT NULL
    PRIMARY KEY,
  teacher_name     VARCHAR(20)                                                        NULL,
  positional_title ENUM ('assistant', 'lecturer', 'assistant professor', 'professor') NULL,
  teacher_office   VARCHAR(10)                                                        NULL,
  is_adviser       TINYINT(1)                                                         NULL,
  teacher_phone    VARCHAR(15)                                                        NULL
)
  ENGINE = InnoDB
  CHARSET = utf8;

ALTER TABLE teach
  ADD CONSTRAINT FK_Reference_15
FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id);

CREATE TABLE teacher_college
(
  college_id VARCHAR(20) NOT NULL
    PRIMARY KEY,
  teacher_id CHAR(12)    NULL,
  CONSTRAINT FK_Reference_7
  FOREIGN KEY (college_id) REFERENCES college (college_id),
  CONSTRAINT FK_Reference_6
  FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE INDEX FK_Reference_6
  ON teacher_college (teacher_id);

CREATE TABLE title
(
  tile_id    VARCHAR(20) NOT NULL
    PRIMARY KEY,
  title_name VARCHAR(20) NULL
)
  ENGINE = InnoDB
  CHARSET = utf8;

ALTER TABLE class_title
  ADD CONSTRAINT FK_Reference_5
FOREIGN KEY (title_id) REFERENCES title (tile_id);

ALTER TABLE student_title
  ADD CONSTRAINT FK_Reference_4
FOREIGN KEY (title_id) REFERENCES title (tile_id);

CREATE TABLE user_information
(
  user_name     VARCHAR(20) NOT NULL
    PRIMARY KEY,
  user_password VARCHAR(20) NULL,
  is_admin      TINYINT(1)  NULL
)
  ENGINE = InnoDB
  CHARSET = utf8;


