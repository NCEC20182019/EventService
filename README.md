# EventService
### Подготовка к работе с flyway
1. Создать БД на сервере postgres (у меня 11 версия, но, возможно, есть смысл 10 юзать)
2. Прописать логин, пароль и тд в application.properties
3. Выполнить `mvn flyway:migrate`
### Swagger
Для доступа к докуметации API запустите сервис и перейдите по: `localhost:port/swagger-ui.html`
