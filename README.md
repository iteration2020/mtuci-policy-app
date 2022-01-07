# Технологии разработки программного обеспечения
[![Build Status](https://app.travis-ci.com/iteration2020/mtuci-policy-app.svg?branch=main)](https://app.travis-ci.com/iteration2020/mtuci-policy-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=iteration2020_mtuci-policy-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=iteration2020_mtuci-policy-app)

Лабораторные работы по дисциплине Технологии разработки программного обеспечения

Антипов Артем Владиславович, группа МАС 2131


# Лабораторная работа №1: создание микросервиса на Spring Boot с базой данных
Цель лабораторной работы: знакомство с проектированием многослойной архитектуры Web-API

## Описание предметной области сервиса
Веб-сервис Полис предназначен для использования в территориальных фондах обязательного медицинского страхования с целью учета застрахованных лиц и их прикреплений к страховым медицинским и медицинским организациям.
Функции сервиса:
1. Учет застрахованных лиц (person) включая сведения о дате, месте рождения, сведения о документе удостоверяющем личность и контактным данным.
2. Регистрация заявлений от застрахованных лиц о смене страховой принадлежности, о замене медицинской организации  (attachmo)
3. Ведения реестра страховых медицинских и медицинских организаций (organisations)

## Сборка и запуск приложения

1. Клонирование репозитория
* Для клонирования приложения необходимо выполнить следующую команду в терминале.

  `$ git clone https://github.com/iteration2020/mtuci-policy-app.git`

2. Сборка приложения в Maven
* Для сборки приложения через Maven необходимо выполнить следующие действия.
Перейти в папку проекта и выполнить команду
  
  `$ ./mvnw package -DskipTests`

3. Сборка через docker-образ из командной строки
* Для сборки docker-образа необходимо выполнить следующие действия.

  `$ docker build . -t policy-api:latest`
 
4. Запуск docker-контейнера из docker-образа (с указанным маппингом портов)
* Для запуска docker-контейнера необходимо выполнить следующую команду.

  `$ docker run -p 8080:8080 policy-api:latest`
 
5. CURL для обращения к ендпоинтам приложения
* Типы организаций (#Orgtype)
  * Save 
 
  `$ curl -H "Content-Type: application/json" -d "{\"type\": \"type1\"}" localhost:8080/api/v1/orgtype`
  
  * Get all 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/orgtype`
  
  * Get 
  
  (`$ curl -H "Content-Type: application/json" localhost:8080/api/v1/orgtype/1`
  
  * Delete
  
  `$ curl -X DELETE localhost:8080/api/v1/orgtype/1`
  
  * Update 
  
  `$ curl -X PUT -H "Content-Type: application/json" -d "{\"type\": \"updated\"}" localhost:8080/api/v1/orgtype/1`
  
* Типы документов (#Doctype)  
  * Save 
  
  `$ curl -H "Content-Type: application/json" -d "{\"type\": \"type1\"}" localhost:8080/api/v1/doctype`
  
  * Get all 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/doctype`
  
  * Get 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/doctype/1`
  
  * Delete 
  
  `$ curl -X DELETE localhost:8080/api/v1/doctype/1`
  
  * Update 
  
  `$ curl -X PUT -H "Content-Type: application/json" -d "{\"type\": \"updated\"}" localhost:8080/api/v1/doctype/1`
  
* Типы документов (#Organisations)  
  * Save 
  `$ curl -H "Content-Type: application/json" -d "{\"orgtype\": {\"id\": 1}, \"address\": \"a\", \"nameorg\": \"name\", \"headorg\": \"head\"}" localhost:8080/api/v1/organisations`
  * Get all 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/organisations`
  
  * Get 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/organisations/1`
  
  * Delete 
  
  `$ curl -X DELETE localhost:8080/api/v1/organisations/1`
  
  * Update 
  
  `$ curl -X PUT -H "Content-Type: application/json" -d "{\"orgtype\": {\"id\": 1}, \"address\": \"a\", \"nameorg\": \"updated\", \"headorg\": \"head\"}" localhost:8080/api/v1/organisations/1`
  
* Персоны (#Person)  
  * Save 
  
  `$ curl -H "Content-Type: application/json" -d "{\"doc\": {\"id\": 1}, \"lastname\": \"ln\", \"firstname\": \"fn\", \"patronymic\": \"pat\", \"datebirth\": \"10.10.1988\", \"placebirth\": \"moscow\", \"docnumber\": \"A23\", \"phone\": \"984712341\", \"email\": \"em@mail.ru\"}" localhost:8080/api/v1/person`
  
  * Get all 
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/person`
  
  * Get 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/person/1`
  
  * Delete 
  
  `$ curl -X DELETE localhost:8080/api/v1/person/1`
  
  * Update 
  `$ curl -X PUT -H "Content-Type: application/json" -d "{\"doc\": {\"id\": 1}, \"lastname\": \"new\", \"firstname\": \"new2\", \"patronymic\": \"pat\", \"datebirth\": \"10.10.1988\", \"placebirth\": \"moscow\", \"docnumber\": \"A23\", \"phone\": \"984712341\", \"email\": \"em@mail.ru\"}" localhost:8080/api/v1/organisations/1`
  
* Прикрепления (#AttachMO)  
  * Save 

  `$ curl -H "Content-Type: application/json" -d "{\"person\": {\"id\": 1}, \"org\": {\"id\": 1}, \"datestart\": \"10.10.1988\", \"dateend\": \"10.10.2030\"}" localhost:8080/api/v1/attachments`
  
  * Get all 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/attachments`

  * Get 
  
  `$ curl -H "Content-Type: application/json" localhost:8080/api/v1/attachments/1`

  * Delete 

  `$ curl -X DELETE localhost:8080/api/v1/attachments/1`
  
  * Update 
  
  `$ curl -X PUT -H "Content-Type: application/json" -d "{\"person\": {\"id\": 1}, \"org\": {\"id\": 1}, \"datestart\": \"10.10.1988\", \"dateend\": \"10.10.2040\"}" localhost:8080/api/v1/attachments/1`

6. CURL для обращения к ендпоинту hostname
* Для получения HostName выполните следующую компанду.

   `$ curl localhost:8080/api/v1/status`
 
 # Лабораторная работа №2: создание кластера Kubernetes и деплой приложения
 Цель лабораторной работы является знакомство с кластерной архитектурой на примере Kubernetes, а также деплоем приложения в кластер.
 1. Манифесты deployment.yaml и service.yaml
 * deployment.yaml
 
`apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-deployment
spec:
  replicas: 10
  selector:
    matchLabels:
      app: my-app
  strategy:
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
    type: RollingUpdate
  template:
    metadata:
      labels:
        app: my-app
    spec:
      containers:
        - image: policyapi:latest
          # https://medium.com/bb-tutorials-and-thoughts/how-to-use-own-local-doker-images-with-minikube-2c1ed0b0968
          imagePullPolicy: Never
          name: myapi
          ports:
            - containerPort: 8080`
           
 * service.yaml

`apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  type: NodePort
  ports:
    - nodePort: 31317
      port: 8080
      protocol: TCP
      targetPort: 8080
  selector:
    app: my-app`

 2. Скриншты вывода команды консоли с шага 3.3 на фоне рабочего стола.
 3. Скриншоты графического интерфейса с шага 3.5, где видны поды.
 4. 30 секундное видео с обзором созданного кластера и вашими комментариями.

# Лабораторная работа №3: CI/CD и деплой приложения в Heroku
Целью лабораторной работы является знакомство с CI/CD и его реализацией на примере Travis CI и Heroku.

1. Привести ссылку на развернутое приложение на платформе Heroku.
* Ознакомится с приложением можно по ссылке https://mtuci-policy-app.herokuapp.com


2.Прикрепить Travis CI badge на странице репозитория для отображения статуса сборки.
*  [![Build Status](https://app.travis-ci.com/iteration2020/mtuci-policy-app.svg?branch=main)](https://app.travis-ci.com/iteration2020/mtuci-policy-app)
