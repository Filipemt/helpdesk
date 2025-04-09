# DeskhelpApi

## Descrição do Projeto
O **DeskhelpApi** é uma aplicação desenvolvida em **Java** utilizando o framework **Spring Boot** e as melhores práticas do ecossistema Jakarta EE. A aplicação tem como objetivo oferecer uma API RESTful para solucionar problemas relacionados ao gerenciamento de tickets de suporte técnico, incluindo o registro, rastreamento e resolução de problemas.

O uso de ferramentas modernas, como Spring Data JPA, Spring MVC e Lombok, garante maior produtividade, um design robusto e facilidade de manutenção. Este projeto também implementa boas práticas de manipulação de exceções globais, fornecendo respostas claras e informativas para os clientes da API.

---

## Tecnologias Utilizadas

- **Java SDK**: 21
- **Spring Boot**:
    - Spring Data JPA
    - Spring MVC
    - Spring Web
- **Jakarta EE**: Para padrões modernos de desenvolvimento Java.
- **Lombok**: Facilitação do desenvolvimento através da geração automática de código repetitivo.
- **Banco de Dados**: Integração com um banco relacional via JPA (configurável).

---

## Funcionalidades

- **Gestão de Exceções**:
    - Tratamento global de erros com mensagens padronizadas para duplicação de registros e entidades não encontradas.
    - Código HTTP apropriado para cada erro (ex.: `409 Conflict`, `404 Not Found`).

- **API RESTful**:
    - Estruturada e seguindo princípios REST para operações CRUD (`Create`, `Read`, `Update`, `Delete`).

- **Manutenibilidade**:
    - Código modular e extensível, com separação de responsabilidades e padrões de desenvolvimento.

---

## Estrutura do Projeto

A estrutura segue as diretrizes do Spring Boot, organizada da seguinte forma:

````
src/main/java 
    └── br/com/filipecode/DeskhelpApi 
        └── auditoria
            ├── entity/ # Entidades JPA que representam as tabelas do banco de dados.
            ├── controllers/ # Controladores da API (camada de entrada). 
            ├── services/ # Lógica de negócios (camada de serviço). 
            ├── repositories/ # Interfaces para acesso ao banco de dados. 
        └── chamado
            ├── entity/ # Entidades JPA que representam as tabelas do banco de dados.
            ├── controllers/ # Controladores da API (camada de entrada). 
            ├── services/ # Lógica de negócios (camada de serviço). 
            ├── dto
            ├── repositories/ # Interfaces para acesso ao banco de dados.
        └── tecnico
            ├── entity/ # Entidades JPA que representam as tabelas do banco de dados.
            ├── controllers/ # Controladores da API (camada de entrada). 
            ├── services/ # Lógica de negócios (camada de serviço). 
            ├── dto
            ├── repositories/ # Interfaces para acesso ao banco de dados.
        └── usuario
            ├── entity/ # Entidades JPA que representam as tabelas do banco de dados.
            ├── controllers/ # Controladores da API (camada de entrada). 
            ├── services/ # Lógica de negócios (camada de serviço). 
            ├── dto
            ├── repositories/ # Interfaces para acesso ao banco de dados.
        └── shared/ ├── exceptions/ # Classe de tratamento global de exceções. 
        └── DeskhelpApiApplication.java # Entry-point da aplicação.

````

---

## Boas Práticas Utilizadas

1. **Tratamento Global de Exceções**:
    - Implementado na classe `GlobalExceptionHandler` usando anotações do Spring (`@ControllerAdvice` e `@ExceptionHandler`).
    - Padrões de resposta uniformes em casos de erro.

2. **Design Ocidental**:
    - Total aderência ao padrão arquitetural REST.
    - Endpoints intuitivos e facilmente documentáveis.

3. **Configuração e Extensibilidade**:
    - Uso de interface no repositório para desacoplar o código entre o banco e as regras de negócio.

---

## Como Executar o Projeto

1. **Pré-requisitos**:
    - Java 21 ou superior instalado.
    - Maven para construção do projeto.
    - Banco de dados configurado (ex.: PostgreSQL, H2 ou outro que preferir).

2. **Clonar o Repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd DeskhelpApi
   ```

3. **Configurar o Aplicativo**:
    - No arquivo `application.properties` ou `application.yml` (em `src/main/resources`):
        - Configure o banco de dados:
          ```properties
          spring.datasource.url=jdbc:seubanco://host:porta/banco
          spring.datasource.username=usuario
          spring.datasource.password=senha
          ```

4. **Construção e Execução**:
    - Para compilar e iniciar a aplicação:
      ```bash
      ./mvnw spring-boot:run
      ```
    - A aplicação estará disponível em: `http://localhost:8080`.

---

## Exemplo de Uso da API

**Request exemplo**:
```http
POST /usuarios
Content-Type: application/json

{
    "nome": "usuario",
    "email": "usuario@gmail.com",
    "departamento": "departamento",
    "cargo": "cargo"
}
```

**Response em caso de duplicação de registro**:
```http
HTTP/1.1 409 Conflict
Content-Type: application/json

{
  "erro": "O registro já existe no sistema."
}
```

---

## Melhorias Futuras

1. **Autenticação e Autorização**:
    - Implementação de protocolos como JWT para segurança.

2. **Documentação da API**:
    - Integração com Swagger ou Springdoc OpenAPI para gerar documentação interativa da API.

3. **Testes Automatizados**:
    - Cobertura de testes com o uso de ferramentas como JUnit e Mockito.

4. **Logs Avançados**:
    - Uso de ferramentas de observabilidade como ELK Stack ou Logback.

---

## Contribuindo

Contribuições são sempre bem-vindas! Siga os passos abaixo:

1. Faça um fork do projeto.
2. Crie uma branch para a funcionalidade ou correção:
   ```bash
   git checkout -b minha-nova-feature
   ```
3. Commit suas mudanças:
   ```bash
   git commit -m "Descrição da minha nova funcionalidade"
   ```
4. Faça um push:
   ```bash
   git push origin minha-nova-feature
   ```
5. Abra um Pull Request.

---

## Licença

Este projeto é licenciado sob [MIT License](LICENSE).

---

## Contatos

Caso tenha dúvidas ou sugestões, entre em contato:

- **Autor**: Filipe Mota Barbosa
- **E-mail**: [filipeddev@gmail.com](mailto:filipeddev@gmail.com)
- **LinkedIn**: [linkedin.com/in/Filipe Mota](https://www.linkedin.com/in/filipe-mota-b15139231/)