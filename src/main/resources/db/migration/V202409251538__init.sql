
CREATE TABLE houses
(
    house_id                  BIGSERIAL PRIMARY KEY,
    address                   VARCHAR(1000) NOT NULL,
    name                      VARCHAR(255)  NOT NULL,
    building_start_date       DATE,
    planned_building_end_date DATE,
    commissioning_date        DATE
);

CREATE TABLE floors
(
    floor_id     BIGSERIAL PRIMARY KEY,
    house_id     BIGINT   NOT NULL,
    floor_number SMALLINT NOT NULL,
    floor_plan   VARCHAR(1000),
    foreign key (house_id) references houses (house_id)
);

CREATE TABLE sale_statuses
(
    sale_status_id SERIAL PRIMARY KEY NOT NULL,
    name           VARCHAR(255)
);

CREATE TABLE apartments
(
    apartment_id    BIGSERIAL PRIMARY KEY NOT NULL,
    floor_id        BIGINT                NOT NULL,
    sale_status_id  INT                   NOT NULL,
    total_area      INT                   NOT NULL,
    living_area     INT                   NOT NULL,
    amount_of_rooms SMALLINT              NOT NULL,
    entrance_number SMALLINT              NOT NULL,
    apartment_cost  INT                   NOT NULL,
    apartment_plan  VARCHAR(1000),
    foreign key (floor_id) references floors (floor_id),
    foreign key (sale_status_id) references sale_statuses (sale_status_id)
);

CREATE TABLE owners
(
    owner_id     BIGSERIAL PRIMARY KEY NOT NULL,
    name         VARCHAR(255)          NOT NULL,
    surname      VARCHAR(255)          NOT NULL,
    phone_number VARCHAR(20)           NOT NULL,
    email        VARCHAR(100)
);

CREATE TABLE apartments_owners
(
    apartment_id BIGINT NOT NULL,
    owner_id     BIGINT NOT NULL,
    foreign key (apartment_id) references apartments (apartment_id),
    foreign key (owner_id) references owners (owner_id)
);

