# Como criar uma API com Spring Boot

## Introdução

Este projeto demonstra como criar uma API utilizando Spring Boot, configurar RabbitMQ e MongoDB via Docker, consumir uma fila do RabbitMQ, comunicar com o banco de dados MongoDB, mapear uma collection do MongoDB dentro do Spring, fazer aggregations no MongoDB com Spring e efetuar logs com o SLF4J.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data MongoDB
- RabbitMQ
- Docker
- SLF4J

## Passos para Criação

### 1. Como criar um microserviço com Spring Boot

1. Crie um novo projeto Spring Boot.
2. Adicione as dependências necessárias no `pom.xml` (Spring Boot Starter Web, Spring Boot Starter Data MongoDB, Spring Boot Starter AMQP, etc.).
3. Crie as classes de configuração, controladores, serviços e repositórios.

### 2. Como configurar o RabbitMQ e MongoDB via Docker

Crie um arquivo `docker-compose.yml` com o seguinte conteúdo para configurar RabbitMQ e MongoDB:

```yaml
version: '3.8'

services:
  mongodb:
    image: mongo
    ports:
      - 27017:27017
    environment:
      - MONGO_INITDB_ROOT_USERNAME=admin
      - MONGO_INITDB_ROOT_PASSWORD=123

  rabbitmq:
    image: rabbitmq:3.13-management
    ports:
      - 15672:15672
      - 5672:5672
