--liquibase formatted sql
--changeset ricardo.medeiros:1.1 context:prod
CREATE SEQUENCE SQ_USUARIO MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 1 CACHE 20;
CREATE TABLE TB_USUARIO (
  ID NUMBER(19) NOT NULL,
  NOME VARCHAR2(255) NULL
);
--rollback DROP SEQUENCE SQ_USUARIO;
--rollback DROP TABLE TB_USUARIO;


--liquibase formatted sql
--changeset ricardo.medeiros:1.2 context:test
INSERT INTO TB_USUARIO VALUES(SQ_USUARIO.nextval, 'Ricardo Abreu Medeiros');
INSERT INTO TB_USUARIO VALUES(SQ_USUARIO.nextval, 'Walison Marcos');
--rollback DELETE FROM POC WHERE NAME in('Ricardo Abreu Medeiros', 'Walison Marcos');