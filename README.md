# **Apartments Under Construction**

## **Task**

 “Apartments in houses under construction”. A construction company needs an application that will record houses under construction and apartments in them. House attributes: address, name, construction start date, planned completion date, commissioning date. For an apartment: total area, living area, number of rooms, floor, entrance, price, as well as the state of sale: free, reserved, sold. It is desirable to make it possible to download floor plans of buildings.

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

![Database scheme](https://github.com/user-attachments/assets/576c6ca8-3f67-41fc-9473-412fdcd9ba25)
