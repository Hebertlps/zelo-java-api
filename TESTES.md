# Documentação de Testes - API Zelo

## 1. Visão Geral
Este documento comprova a realização dos testes dos endpoints da API RESTful do projeto Zelo, garantindo a persistência e recuperação dos dados no banco de dados Oracle.

## 2. Ferramentas Utilizadas
- **Postman:** Para execução das requisições HTTP.
- **Oracle Database:** Para validação da persistência dos dados.

## 3. Testes Realizados

### 3.1. Autenticação (Login)
- **Endpoint:** `POST /api/auth/login`
- **Objetivo:** Validar a geração do token JWT.
- **Resultado:** Token gerado com sucesso (Status 200 OK).

### 3.2. Criação de Tutor
- **Endpoint:** `POST /api/tutores`
- **Objetivo:** Inserir um novo tutor no banco de dados.
- **Payload:**
```json
{
    "nome": "Novo Tutor",
    "email": "novo@email.com",
    "senha": "senha123",
    "telefone": "11999999999"
}
```
- **Resultado:** Tutor criado com sucesso (Status 201 Created). Dados persistidos na tabela `t_ch_tutores`.

### 3.3. Listagem de Tutores (com Paginação)
- **Endpoint:** `GET /api/tutores?page=0&size=10&sort=nome,asc`
- **Objetivo:** Recuperar a lista de tutores paginada e ordenada.
- **Resultado:** Lista retornada com sucesso (Status 200 OK).

### 3.4. Criação de Pet
- **Endpoint:** `POST /api/pets`
- **Objetivo:** Inserir um novo pet associado a um tutor existente.
- **Payload:**
```json
{
    "nome": "Novo Pet",
    "especie": "Cão",
    "raca": "Vira-lata",
    "idade": 2,
    "peso": 15.5,
    "tutor": {
        "id": 1
    }
}
```
- **Resultado:** Pet criado com sucesso (Status 201 Created). Dados persistidos na tabela `t_ch_pets`.

### 3.5. Listagem de Pets
- **Endpoint:** `GET /api/pets`
- **Objetivo:** Recuperar a lista de pets.
- **Resultado:** Lista retornada com sucesso (Status 200 OK).

## 4. Validação de Persistência
Após a execução dos testes via Postman, foi realizada uma consulta direta no banco de dados Oracle para confirmar a persistência:

```sql
SELECT * FROM t_ch_tutores WHERE ds_email = 'novo@email.com';
SELECT * FROM t_ch_pets WHERE nm_pets = 'Novo Pet';
```
**Resultado:** Os registros foram encontrados no banco de dados, confirmando a perfeita persistência e recuperação dos dados.

## 5. Coleção Postman
A coleção completa com todas as requisições configuradas está disponível no arquivo `Zelo-API.postman_collection.json` na raiz do projeto.
