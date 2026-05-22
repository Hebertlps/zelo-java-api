# Diagramas de Arquitetura - Projeto Zelo

## 1. Diagrama Entidade-Relacionamento (DER)

```mermaid
erDiagram
    TUTORES ||--o{ PETS : "possui"
    
    TUTORES {
        NUMBER id_tutores PK
        VARCHAR2 nm_tutores
        VARCHAR2 ds_email UK
        VARCHAR2 ds_senha
        VARCHAR2 ds_telefone
    }
    
    PETS {
        NUMBER id_pets PK
        VARCHAR2 nm_pets
        VARCHAR2 ds_especie
        VARCHAR2 ds_raca
        NUMBER nr_idade
        NUMBER nr_peso
        NUMBER id_tutores FK
    }
    
    SYSTEM_LOGS {
        NUMBER id_logs PK
        VARCHAR2 nm_procedure
        VARCHAR2 nm_usuario
        TIMESTAMP dt_ocorrencia
        NUMBER cod_erro
        VARCHAR2 ms_erro
    }
```

## 2. Diagrama de Classes de Entidade

```mermaid
classDiagram
    class Tutor {
        -Long id
        -String nome
        -String email
        -String senha
        -String telefone
        -List~Pet~ pets
        +getId() Long
        +getNome() String
        +getEmail() String
        +getPets() List~Pet~
    }
    
    class Pet {
        -Long id
        -String nome
        -String especie
        -String raca
        -Integer idade
        -Double peso
        -Tutor tutor
        +getId() Long
        +getNome() String
        +getEspecie() String
        +getTutor() Tutor
    }
    
    Tutor "1" *-- "*" Pet : possui
```

## 3. Explicação dos Relacionamentos e Constraints

### Relacionamento Tutor -> Pet (1:N)
- Um **Tutor** pode ter vários **Pets** (relação de um para muitos).
- Um **Pet** pertence a apenas um **Tutor**.
- No banco de dados, isso é representado pela Foreign Key `id_tutores` na tabela `t_ch_pets`, referenciando a Primary Key `id_tutores` da tabela `t_ch_tutores`.
- No JPA, isso é mapeado usando as anotações `@OneToMany` na classe `Tutor` e `@ManyToOne` na classe `Pet`.

### Constraints Principais
- **Primary Keys:** `id_tutores` e `id_pets` são chaves primárias auto-incrementais (Identity).
- **Unique:** O campo `ds_email` na tabela de tutores é único, garantindo que não existam contas duplicadas.
- **Not Null:** Campos essenciais como nome, email, senha (Tutor) e nome, espécie (Pet) são obrigatórios.
