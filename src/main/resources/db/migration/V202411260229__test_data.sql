
--Статусы продажи
INSERT INTO sale_statuses (sale_status_id, name)
VALUES (1, 'ON SALE'),
       (2, 'RESERVED'),
       (3, 'SOLD');

SELECT setval('public.sale_statuses_sale_status_id_seq', (SELECT MAX(sale_status_id) FROM sale_statuses));

--Дома

INSERT INTO houses VALUES (1, 'г. Воронеж, ул. Моисеева, 23', 'Афродита', '2024-08-14', '2024-11-09', '2025-02-02');
INSERT INTO houses VALUES (2, 'г. Воронеж, ул. Новосибирская, 66', 'Зевс', '2024-03-28', '2024-11-12', '2025-06-21');
INSERT INTO houses VALUES (3, 'г. Воронеж, ул. Богдана Хмельницкого, 91', 'Орел', '2024-09-21', '2024-11-09', '2024-12-26');
INSERT INTO houses VALUES (4, 'г. Воронеж, ул. 20-Летия октября, 12', 'Феникс', '2024-05-13', '2024-11-11', '2025-05-06');

SELECT setval('public.houses_house_id_seq', (SELECT MAX(house_id) FROM houses));

--Этажи

INSERT INTO floors (floor_id, house_id, floor_number, floor_plan) VALUES (2, 1, 5, 'https://img2.sibdom.ru/images/photo_594_446/houses/photo_plan_floor/81/8162/816292c8a5548e5edd4b281edcb3f31e.jpg');
INSERT INTO floors (floor_id, house_id, floor_number, floor_plan) VALUES (3, 2, 3, 'https://rzv.ru/upload/resized_cache_custom/xml_00132_000000444_3_etazh____OBShch_1920x1920.jpg');
INSERT INTO floors (floor_id, house_id, floor_number, floor_plan) VALUES (4, 3, 18, 'https://rzv.ru/upload/resized_cache_custom/xml_00007_000000073_000000159_Plan_726x10000.jpg');

SELECT setval('public.floors_floor_id_seq', (SELECT MAX(floor_id) FROM floors));

--Квартиры
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (1, 2, 1, 24, 103.97, 102.12, 3, 4, 12166830, 'https://rzv.ru/upload/resized_cache_custom/xml_00111_000000394_000005868_Plan_b0e7fc85354411ef867bf505c3deefa9_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (2, 2, 1, 123, 25.64, 24.12, 1, 2, 2668209, 'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007494_Plan_7344d40f8ad111ef8680d139b61f1e25_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (3, 3, 1, 93, 32.12, 31.40, 1, 4, 3538125, 'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007459_Plan_c0d6f1ba87b711ef867fb3409566c4e4_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (4, 2, 1, 11, 35.15, 34.10, 1, 3, 3768761, 'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007472_Plan_3a9b57f88d2111ef8680d139b61f1e25_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (5, 3, 1, 42, 75.76, 74.21, 2, 1, 7882662, 'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007470_Plan_bfb517e387ba11ef867fb3409566c4e4_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (6, 4, 1, 12, 61.55, 60.45, 2, 3, 6251642, 'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007502_Plan_d70dc7648ad411ef8680d139b61f1e25_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area, amount_of_rooms, entrance_number, apartment_cost, apartment_plan) VALUES (7, 4, 1, 85, 54.98, 53.43, 1, 5, 5398966, 'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007469_Plan_8e237d6987ba11ef867fb3409566c4e4_726x10000.jpg');

SELECT setval('public.apartments_apartment_id_seq', (SELECT MAX(apartment_id) FROM apartments));

--Роли

INSERT INTO roles (role_id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (2, 'ROLE_MANAGER');
INSERT INTO roles (role_id, name) VALUES (3, 'ROLE_ADMIN');

SELECT setval('public.roles_role_id_seq', (SELECT MAX(role_id) from roles));

--Пользователи

INSERT INTO users (user_id, username, password, name, surname, phone_number, email) VALUES (1, 'admin', '$2a$10$A9R5tDQq3SFDKJa.J0OYj.PlLlFqbf/mfzyrcwMjMi0w22mZE5N3i', 'admin', 'admin', '+8999', 'mail@mail.ru');

SELECT setval('public.users_user_id_seq', (SELECT MAX(user_id) from users));

--Пользователи + роли

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);

SELECT setval('public.users_roles_users_roles_id_seq', (SELECT MAX(user_id) from users_roles));