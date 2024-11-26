# **Apartments Under Construction**

## **Task**

 ENG: “Apartments in houses under construction”. A construction company needs an application that will record houses under construction and apartments in them. House attributes: address, name, construction start date, planned completion date, commissioning date. For an apartment: total area, living area, number of rooms, floor, entrance, price, as well as the state of sale: free, reserved, sold. It is desirable to make it possible to download floor plans of buildings.

RU: "Квартиры в строящихся домах". Строительной компании необходимо приложение в котором будут учитываться строящиеся дома и квартиры в них. Атрибуты дома: адрес, название, дата начала строительства, планируемая дата завершения строительства, дата ввода в эксплуатацию. Для квартиры: общая площадь, жилая площадь, кол-во комнат, этаж, подъезд, цена, а также состояние продажи: свободна, зарезервирована, продана. Желательно сделать возможность загрузки поэтажных планов домов.

## **Docker** 
docker-compose file contains only Postgres DB container.

```
version: '3.1'

services:
  postgres:
    image: postgres:latest
    container_name: apartments
    environment:
      POSTGRES_USER: kattsyn
      POSTGRES_PASSWORD: katt
      POSTGRES_DB: apartments_db
    ports:
      - "5432:5432"
```

## **Database scheme**

![Database scheme](https://github.com/user-attachments/assets/33d0da98-dddb-4fe0-939f-749e98edf1dc)
