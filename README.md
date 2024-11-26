# API Spring Boot - Sistema de Pedidos e Gerenciamento de Restaurantes

## Visão Geral
Este projeto é composto por duas aplicações Spring Boot:
1. **Orders**: Responsável pelo gerenciamento de pedidos.
2. **Restaurant Manager**: Gerencia os restaurantes cadastrados.

Ambas as aplicações utilizam uma infraestrutura que inclui banco de dados e Apache Kafka para comunicação assíncrona.

---

## Requisitos
- Docker e Docker Compose instalados.
- Maven instalado.
- Java 17 ou superior.

---

## Como executar

### 1. Subir a infraestrutura
Execute o comando abaixo para iniciar os serviços do banco de dados e do Kafka:
```bash
docker-compose up
```

### 2. Subir o microserviço de pedidos (*Orders*)
Entre na pasta `orders`, instale as dependências e inicie a aplicação:
```bash
cd orders
mvn install
mvn spring-boot:run
```

A aplicação ficará acessível em:
- Swagger UI: [http://localhost:8081/swagger-ui/index.html#/Pedidos/createOrder](http://localhost:8081/swagger-ui/index.html#/Pedidos/createOrder)

### 3. Subir o microserviço de gerenciamento de restaurantes (*Restaurant Manager*)
Entre na pasta `restaurant-manager`, instale as dependências e inicie a aplicação:
```bash
cd restaurant-manager
mvn install
mvn spring-boot:run
```

A aplicação ficará acessível em:
- Swagger UI: [http://localhost:8082/swagger-ui/index.html#/](http://localhost:8082/swagger-ui/index.html#/)

---

## Tecnologias Utilizadas
- **Spring Boot**
- **Docker e Docker Compose**
- **Apache Kafka**
- **Swagger/OpenAPI**
- **PostgreSQL**

---

## Estrutura do Projeto
```plaintext
├── docker-compose.yml  # Configuração para banco de dados e Kafka
├── orders              # Microserviço de pedidos
├── restaurant-manager  # Microserviço de gerenciamento de restaurantes
```

---

## Rotas Principais
### Orders API
- **Criar Pedido:** `POST /orders`
  - Documentação: [Swagger Orders](http://localhost:8081/swagger-ui/index.html#/Pedidos/createOrder)

### Restaurant Manager API
- **Gerenciar Restaurantes:** `POST /restaurants`
  - Documentação: [Swagger Restaurant Manager](http://localhost:8082/swagger-ui/index.html#/)

---


