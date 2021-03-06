--liquibase formatted sql
--changeset renan.monteiro:1.1 context:prod
CREATE TABLE TB_CLIENTE (
  ID bigint GENERATED BY DEFAULT AS IDENTITY primary key,
  NOME VARCHAR2
(255) NULL,
  EMAIL VARCHAR2
(100) NULL,
  TELEFONE VARCHAR2
(15) NULL,
  DT_NASCIMENTO TIMESTAMP NOT NULL
);
--rollback DROP SEQUENCE SQ_CLIENTE;
--rollback DROP TABLE TB_CLIENTE;


--liquibase formatted sql
--changeset renan.monteiro:1.2 context:test
INSERT INTO TB_CLIENTE
  (NOME,EMAIL,TELEFONE,DT_NASCIMENTO)
VALUES('Renan Monteiro', 'renan@mail.com', '61 99999-9999', '1990-02-09');
INSERT INTO TB_CLIENTE
  (NOME,EMAIL,TELEFONE,DT_NASCIMENTO)
VALUES('Beca Moura', 'beca@mail.com', '15 99999-9999', '2000-02-09');
--rollback DELETE FROM TB_CLIENTE WHERE NAME in('Renan Monteiro', 'Beca Moura');
