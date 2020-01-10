CREATE TABLE corporation(
 corp_id SMALLINT comment '主键',
 name VARCHAR(30) comment '姓名',
 CONSTRAINT pk_corporation PRIMARY KEY (corp_id)
);

INSERT INTO corporation(corp_id, name) values(27, 'Acme Paper Corporation');