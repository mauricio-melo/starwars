CREATE TABLE planet (
  id                    BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name	                VARCHAR(255) NOT NULL,
  climate               VARCHAR(255) NOT NULL,
  terrain               VARCHAR(255) NOT NULL,
  flg_enabled           BIT          NOT NULL,
  dat_creation          DATETIME     NOT NULL,
  dat_update            DATETIME     NULL
);