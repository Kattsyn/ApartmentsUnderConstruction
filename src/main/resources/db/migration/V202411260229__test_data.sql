--Статусы продажи
INSERT INTO sale_statuses (sale_status_id, name)
VALUES (1, 'ON SALE'),
       (2, 'RESERVED'),
       (3, 'SOLD');

SELECT setval('public.sale_statuses_sale_status_id_seq', (SELECT MAX(sale_status_id) FROM sale_statuses));

--Регионы

INSERT INTO regions (region_id, name)
values (1, 'Воронежская область');

SELECT setval('public.regions_region_id_seq', (SELECT MAX(regions.region_id) FROM regions));

--Дома

INSERT INTO houses
VALUES (1, 1, 'ул. Моисеева, 23', 'Афродита', '2024-08-14', '2024-11-09', '2025-02-02', false);
INSERT INTO houses
VALUES (2, 1, 'ул. Новосибирская, 66', 'Зевс', '2024-03-28', '2024-11-12', '2025-06-21', false);
INSERT INTO houses
VALUES (3, 1, 'ул. Богдана Хмельницкого, 91', 'Орел', '2024-09-21', '2024-11-09', '2024-12-26', true);
INSERT INTO houses
VALUES (4, 1, 'ул. 20-Летия октября, 12', 'Феникс', '2024-05-13', '2024-11-11', '2025-05-06', true);

SELECT setval('public.houses_house_id_seq', (SELECT MAX(house_id) FROM houses));

--Этажи
INSERT INTO floors (floor_id, house_id, floor_number, floor_plan)
VALUES (1, 4, 9,
        'https://rzv.ru/upload/resized_cache_custom/xml_00111_000000394_000005808_Plan1_95ac53cea7eb11ef8682b271e4872331_726x10000.jpg');
INSERT INTO floors (floor_id, house_id, floor_number, floor_plan)
VALUES (2, 1, 5,
        'https://rzv.ru/upload/resized_cache_custom/xml_00165_000000674_000007073_Plan1_b3f37fa671ca11ef867fb3409566c4e4_726x10000.jpg');
INSERT INTO floors (floor_id, house_id, floor_number, floor_plan)
VALUES (3, 2, 3, 'https://rzv.ru/upload/resized_cache_custom/xml_00132_000000444_3_etazh____OBShch_1920x1920.jpg');
INSERT INTO floors (floor_id, house_id, floor_number, floor_plan)
VALUES (4, 3, 18, 'https://rzv.ru/upload/resized_cache_custom/xml_00136_000000479_000005010_Plan1_726x10000.jpg');

SELECT setval('public.floors_floor_id_seq', (SELECT MAX(floor_id) FROM floors));

--Квартиры
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (1, 2, 1, 24, 103.97, 102.12, 3, 4, 12166830,
        'https://rzv.ru/upload/resized_cache_custom/xml_00111_000000394_000005868_Plan_b0e7fc85354411ef867bf505c3deefa9_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (2, 2, 1, 123, 25.64, 24.12, 1, 2, 2668209,
        'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007494_Plan_7344d40f8ad111ef8680d139b61f1e25_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (3, 3, 1, 93, 32.12, 31.40, 1, 4, 3538125,
        'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007459_Plan_c0d6f1ba87b711ef867fb3409566c4e4_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (4, 2, 1, 11, 35.15, 34.10, 1, 3, 3768761,
        'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007472_Plan_3a9b57f88d2111ef8680d139b61f1e25_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (5, 3, 1, 42, 75.76, 74.21, 2, 1, 7882662,
        'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007470_Plan_bfb517e387ba11ef867fb3409566c4e4_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (6, 4, 1, 12, 61.55, 60.45, 2, 3, 6251642,
        'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007502_Plan_d70dc7648ad411ef8680d139b61f1e25_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (7, 4, 1, 85, 54.98, 53.43, 1, 5, 5398966,
        'https://rzv.ru/upload/resized_cache_custom/xml_00124_000000681_000007469_Plan_8e237d6987ba11ef867fb3409566c4e4_726x10000.jpg');
INSERT INTO apartments (apartment_id, floor_id, sale_status_id, apartment_number, total_area, living_area,
                        amount_of_rooms, entrance_number, apartment_cost, apartment_plan)
VALUES (8, 1, 1, 67, 62.28, 60.73, 2, 1, 7859736,
        'https://rzv.ru/upload/resized_cache_custom/xml_00111_000000394_000005808_Plan_3e00ebdaa7e811ef8682b271e4872331_726x10000.jpg');

SELECT setval('public.apartments_apartment_id_seq', (SELECT MAX(apartment_id) FROM apartments));

--Роли

INSERT INTO roles (role_id, name)
VALUES (1, 'ROLE_USER');
INSERT INTO roles (role_id, name)
VALUES (2, 'ROLE_MANAGER');
INSERT INTO roles (role_id, name)
VALUES (3, 'ROLE_ADMIN');

SELECT setval('public.roles_role_id_seq', (SELECT MAX(role_id) from roles));

--Пользователи

INSERT INTO users (user_id, username, password, name, surname, phone_number, email, status)
VALUES (1, 'admin', '$2a$10$A9R5tDQq3SFDKJa.J0OYj.PlLlFqbf/mfzyrcwMjMi0w22mZE5N3i', 'admin', 'admin', '+8999',
        'mail@mail.ru', true);

SELECT setval('public.users_user_id_seq', (SELECT MAX(user_id) from users));

--Пользователи + роли

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 3);

SELECT setval('public.users_roles_users_roles_id_seq', (SELECT MAX(user_id) from users_roles));

--Фотографии

INSERT INTO images (image_id, url)
VALUES (1, 'https://rzv.ru/upload/resize_cache/iblock/0df/730_410_2/d38r5dlgw1ye0yvtsho7jdk8wvwbtbm5.jpg');
INSERT INTO images (image_id, url)
VALUES (2, 'https://rzv.ru/upload/resize_cache/iblock/c45/lcnwo1ns4rrfx9do17p2g2elre2hlfuo/730_410_2/2.jpg');
INSERT INTO images (image_id, url)
VALUES (3, 'https://rzv.ru/upload/resize_cache/iblock/768/ef8lj4paxuotiaf0hu2k8y02gb8x1ogi/730_410_2/2.jpg');
INSERT INTO images (image_id, url)
VALUES (4, 'https://rzv.ru/upload/resize_cache/iblock/589/tuyj4tuk0wbgauycny3wboldr1136cwx/730_410_2/3.jpg');
INSERT INTO images (image_id, url)
VALUES (5, 'https://rzv.ru/upload/resize_cache/iblock/0ed/hpz1vmqw7b6fvcp5xurhovjv16jfteps/392_255_2/1.jpg');
INSERT INTO images (image_id, url)
VALUES (6, 'https://rzv.ru/upload/resize_cache/iblock/935/1320_600_2/zrp8u4pbawyk98s1574y2atwibjbo1y5.jpg');
INSERT INTO images (image_id, url)
VALUES (7, 'https://rzv.ru/upload/resize_cache/iblock/b17/bpi8vutb5ddcx0n140ogwut3k3dekb43/1320_600_2/3.jpg');
INSERT INTO images (image_id, url)
VALUES (8, 'https://rzv.ru/upload/resize_cache/iblock/5b3/i3ymzkt2rvg8jmam07kdf7u1tbqh0snc/1320_600_2/7.jpg');

SELECT setval('public.images_image_id_seq', (SELECT MAX(image_id) from public.images));

--Дома + фотографии

INSERT INTO houses_images (house_id, image_id)
values (1, 1);
INSERT INTO houses_images (house_id, image_id)
values (2, 2);
INSERT INTO houses_images (house_id, image_id)
values (3, 3);
INSERT INTO houses_images (house_id, image_id)
values (4, 4);
INSERT INTO houses_images (house_id, image_id)
values (2, 5);
INSERT INTO houses_images (house_id, image_id)
values (1, 6);
INSERT INTO houses_images (house_id, image_id)
values (3, 7);
INSERT INTO houses_images (house_id, image_id)
values (3, 8);

SELECT setval('public.houses_images_houses_images_id_seq', (SELECT MAX(houses_images_id) from public.houses_images));
