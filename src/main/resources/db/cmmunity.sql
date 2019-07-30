-- auto-generated definition
create table QUESTION
(
  ID            INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_60193296_BEEA_47E1_853C_1E080F0FAC80) auto_increment,
  TITLE         VARCHAR(50),
  DESCRIPTION   CLOB,
  GMT_CREATE    BIGINT,
  GMT_MODIFIED  BIGINT,
  GREATOR       INTEGER,
  COMMENT_COUNT INTEGER default 0,
  VIEW_COUNT    INTEGER default 0,
  LIKE_COUNT    INTEGER default 0,
  TAGS          VARCHAR(255),
  constraint QUESTION_PK
    primary key (ID)
);
comment on column QUESTION.TITLE is '标题';
comment on column QUESTION.VIEW_COUNT is '阅读数';
comment on column QUESTION.LIKE_COUNT is '点赞数';
comment on column QUESTION.TAGS is '标签';

