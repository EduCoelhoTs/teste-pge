Documentação da API Spring Boot

Introdução

Este projeto consiste em uma API Spring Boot composta por dois módulos principais: orders e restaurant-manager. A API utiliza Kafka para comunicação assíncrona e um banco de dados para persistência. Os passos abaixo descrevem como configurar e executar o projeto.

Requisitos

Docker e Docker Compose instalados

Java 11 ou superior

Maven

Como executar o projeto

1. Subir o ambiente Docker

Execute o seguinte comando na raiz do projeto para inicializar o banco de dados e o Kafka:

docker-compose up

2. Executar o módulo orders

Navegue até o diretório do módulo:

cd orders

Instale as dependências do projeto:

mvn install

Inicie a aplicação:

mvn spring-boot:run

3. Executar o módulo restaurant-manager

Navegue até o diretório do módulo:

cd restaurant-manager

Instale as dependências do projeto:

mvn install

Inicie a aplicação:

mvn spring-boot:run

Acessar a documentação Swagger

Após iniciar os dois módulos, a documentação Swagger estará disponível nos seguintes endereços:

Orders: http://localhost:8081/swagger-ui/index.html#/Pedidos/createOrder

Restaurant Manager: http://localhost:8082/swagger-ui/index.html#/

Observações

Certifique-se de que as portas 8081 e 8082 estejam livres antes de iniciar as aplicações.

Caso o Kafka ou o banco de dados não estejam funcionando corretamente, verifique os logs do Docker para identificar possíveis problemas:

docker-compose logs

