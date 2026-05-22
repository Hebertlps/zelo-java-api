# Java Advanced - API RESTful

Este módulo contém a API RESTful desenvolvida em Java com Spring Boot, responsável pelo core business do projeto Zelo.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security (JWT)
- H2 Database (para desenvolvimento local)
- Swagger/OpenAPI
- Bean Validation

## Funcionalidades
- CRUD completo de Tutores e Pets.
- Autenticação e Autorização via JWT.
- Paginação e Ordenação de resultados.
- Cache de consultas frequentes.
- Tratamento global de exceções.

## Como Executar
```bash
cd zelo-api
./gradlew bootRun
```
A API estará disponível em `http://localhost:8080`.
A documentação Swagger pode ser acessada em `http://localhost:8080/swagger-ui.html`.
