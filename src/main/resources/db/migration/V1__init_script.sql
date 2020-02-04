CREATE TABLE planet (
  id_planet                    BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name_planet	                VARCHAR(255) NOT NULL,
  climate               VARCHAR(255) NOT NULL,
  terrain               VARCHAR(255) NOT NULL,
  quantity_films        BIGINT       NOT NULL,
  dat_creation          DATETIME     NOT NULL,
  dat_update            DATETIME     NULL,
  UNIQUE (name_planet)
);