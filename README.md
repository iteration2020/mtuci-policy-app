# Технологии разработки программного обеспечения
[![Build Status](https://app.travis-ci.com/iteration2020/mtuci-policy-app.svg?branch=main)](https://app.travis-ci.com/iteration2020/mtuci-policy-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=iteration2020_mtuci-policy-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=iteration2020_mtuci-policy-app)

Лабораторные работы по дисциплине Технологии разработки программного обеспечения

Антипов Артем Владиславович, группа МАС 2131

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

  $ git clone https://github.com/iteration2020/mtuci-policy-app.git

2. Сборка приложения в Maven
* Для сборки приложения через Maven необходимо выполнить следующие действия.
Перейти в папку проекта и выполнить команду
  $ ./mvnw package -DskipTests
После выполнения

3. Сборка через docker-образ из командной строки
* Для сборки docker-образа необходимо выполнить следующие действия.

  $ docker build . -t policy-api:latest
 
4. Запуск docker-контейнера из docker-образа (с указанным маппингом портов)
* Для запуска docker-контейнера необходимо выполнить следующую команду.

  $ docker run -p 8080:8080 policy-api:latest
 
5. CURL для обращения к ендпоинтам приложения
* Типы организаций (#Orgtype)
  * Save ($ curl -H "Content-Type: application/json" -d "{\"type\": \"type1\"}" localhost:8080/api/v1/orgtype)
  * Get all ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/orgtype)
  * Get ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/orgtype/1)
  * Delete ($ curl -X DELETE localhost:8080/api/v1/orgtype/1)
  * Update ($ curl -X PUT -H "Content-Type: application/json" -d "{\"type\": \"updated\"}" localhost:8080/api/v1/orgtype/1)
* Типы документов (#Doctype)  
  * Save ($ curl -H "Content-Type: application/json" -d "{\"type\": \"type1\"}" localhost:8080/api/v1/doctype)
  * Get all ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/doctype)
  * Get ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/doctype/1)
  * Delete ($ curl -X DELETE localhost:8080/api/v1/doctype/1)
  * Update ($ curl -X PUT -H "Content-Type: application/json" -d "{\"type\": \"updated\"}" localhost:8080/api/v1/doctype/1)
* Типы документов (#Organisations)  
  * Save ($ curl -H "Content-Type: application/json" -d "{\"orgtype\": {\"id\": 1}, \"address\": \"a\", \"nameorg\": \"name\", \"headorg\": \"head\"}" localhost:8080/api/v1/organisations)
  * Get all ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/organisations)
  * Get ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/organisations/1)
  * Delete ($ curl -X DELETE localhost:8080/api/v1/organisations/1)
  * Update ($ curl -X PUT -H "Content-Type: application/json" -d "{\"orgtype\": {\"id\": 1}, \"address\": \"a\", \"nameorg\": \"updated\", \"headorg\": \"head\"}" localhost:8080/api/v1/organisations/1)
* Персоны (#Person)  
  * Save ($ curl -H "Content-Type: application/json" -d "{\"doc\": {\"id\": 1}, \"lastname\": \"ln\", \"firstname\": \"fn\", \"patronymic\": \"pat\", \"datebirth\": \"10.10.1988\", \"placebirth\": \"moscow\", \"docnumber\": \"A23\", \"phone\": \"984712341\", \"email\": \"em@mail.ru\"}" localhost:8080/api/v1/person)
  * Get all ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/person)
  * Get ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/person/1)
  * Delete ($ curl -X DELETE localhost:8080/api/v1/person/1)
  * Update ($ curl -X PUT -H "Content-Type: application/json" -d "{\"doc\": {\"id\": 1}, \"lastname\": \"new\", \"firstname\": \"new2\", \"patronymic\": \"pat\", \"datebirth\": \"10.10.1988\", \"placebirth\": \"moscow\", \"docnumber\": \"A23\", \"phone\": \"984712341\", \"email\": \"em@mail.ru\"}" localhost:8080/api/v1/organisations/1)
* Прикрепления (#AttachMO)  
  * Save ($ curl -H "Content-Type: application/json" -d "{\"person\": {\"id\": 1}, \"org\": {\"id\": 1}, \"datestart\": \"10.10.1988\", \"dateend\": \"10.10.2030\"}" localhost:8080/api/v1/attachments)
  * Get all ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/attachments)
  * Get ($ curl -H "Content-Type: application/json" localhost:8080/api/v1/attachments/1)
  * Delete ($ curl -X DELETE localhost:8080/api/v1/attachments/1)
  * Update ($ curl -X PUT -H "Content-Type: application/json" -d "{\"person\": {\"id\": 1}, \"org\": {\"id\": 1}, \"datestart\": \"10.10.1988\", \"dateend\": \"10.10.2040\"}" localhost:8080/api/v1/attachments/1)

6. CURL для обращения к ендпоинту hostname
* Для получения HostName выполните следующую компанду.

   $ curl localhost:8080/api/v1/status
 
