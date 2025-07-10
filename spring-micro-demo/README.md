# ApiGatewayExample

Это программа содержит сразу несколько модулей и примеров 
работы с микросервисной архитектурой, включая
api gateway, service discovery, circuit breaker и external configuration.

Для запуска программы нужно запускать модули
в следующем порядке.

Далее переходим уже к этому приложению `spring-micro-demo`.

1. Запустить модуль сервера eureka-server по пути
   
    `eureka-server/src/main/java/eurekaserver/EurekaServerApplication.java`
2. Запустить приложение из другого репозитория,
   которое привязано к api gateway. Там содержится
   своя инструкция:
   https://github.com/Matiika/AstronSpringHomework
3. Возвращаемся к текущему приложению. Запустить модуль клиента eureka-client по пути
   
    `eureka-client/src/main/java/eurikaclient/EurekaClientApplication.java`
4. Запустить ServerConfigApplication. Это также демонстрация
   external configuration так как настройки порта следующи 
модуль Api-Gateway будет брать из внешнего репозитория.

   `config-server/src/main/java/serverconfigapp/ServerConfigApplication.java`
5. Запуск самого Api-Gateway по пути:
   
    `api-gateway/src/main/java/apigateway/ApiGatewayApplication.java`

Если все выполнено верно, то по адресу `http://localhost:8081/` будет 
доступен Spring-Eureka. В его списке Application будет три приложения.
1. API-GATEWAY
2. ECLIENT
3. USER-SERVICE

##  Тест работоспобности

Теперь для теста программы можно использовать следующие команды. 
Все они будут обращаться через http://localhost:8082/, которая стала 
единой точкой входа для нескольких модулей. 

1. Создание пользователя
   
    `curl -X POST http://localhost:8082/api/users \
   -H "Content-Type: application/json" \
   -d '{
   "name": "John",
   "email": "john@example.com",
   "age": 30
   }'`
2. Просмотр пользователей. Их также можно посмотреть просто по адресу http://localhost:8082/api/users

   `curl -X GET http://localhost:8082/api/users`
3. Обновление пользователя
   
    `curl -X PUT http://localhost:8082/api/users/1 \
   -H "Content-Type: application/json" \
   -d '{
   "name": "John Updated",
   "email": "john.updated@example.com",
   "age": 31
   }'`
4. Удаление пользователя

   `curl -X DELETE http://localhost:8082/api/users/1`

5. Для теста circuit breaker можно выключить приложение,
которое ранее было запущено из репозитория
по адресу: https://github.com/Matiika/AstronSpringHomework. Его выключение
приведет к тому, что по адресу http://localhost:8082/api/users начнет появляться
сообщение, что сервис сейчас недоступен. При повторном включении сервиса через
некоторое время он вновь будет доступен.