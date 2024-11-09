# **Apartments Under Construction**

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
