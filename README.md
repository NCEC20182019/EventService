# EventService [![Build Status](https://travis-ci.org/NCEC20182019/EventService.svg?branch=master)](https://travis-ci.org/NCEC20182019/EventService)
### Подготовка к работе с flyway
1. Создать БД на сервере PostgreSQL (у меня 11 версия, но, возможно, есть смысл 10 юзать)
2. Прописать логин, пароль и тд в application.properties
3. Выполнить `mvn flyway:migrate`
### Swagger
Для доступа к докуметации API запустите сервис и перейдите по: `localhost:port/swagger-ui.html`
### Server Port
8092