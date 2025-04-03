# 📌 Sistema de Helpdesk de Chamados de TI

## 📖 Visão Geral
O sistema de Helpdesk de Chamados de TI permite que usuários registrem problemas técnicos e técnicos possam gerenciá-los e resolvê-los. 
O sistema segue uma arquitetura RESTful com as seguintes camadas:
- **Controller**: Responsável pelas requisições HTTP
- **Service**: Contém a lógica de negócio
- **Repository**: Interface para acesso ao banco de dados
- **Model**: Contém entidades, enums e DTOs
- **Exceptions**: Tratamento de erros
- **Validator**: Validação de regras de negócio

---

## 📂 Estrutura das Entidades

### 👤 Usuário (User)
Representa o funcionário que abre um chamado.
- `id` (UUID)
- `nome` (String)
- `email` (String)
- `departamento` (String)
- `cargo` (String)

### 📌 Chamado (Ticket)
Representa um problema reportado.
- `id` (UUID)
- `titulo` (String)
- `descricao` (String)
- `status` (Enum: ABERTO, EM_ANDAMENTO, CONCLUIDO)
- `prioridade` (Enum: BAIXA, MEDIA, ALTA)
- `dataCriacao` (DateTime)
- `dataAtualizacao` (DateTime)
- `dataFechamento` (DateTime, opcional)
- `usuarioId` (UUID)

### 🔧 Técnico (Technician)
Responsável por resolver os chamados.
- `id` (UUID)
- `nome` (String)
- `email` (String)
- `especializacao` (String)

### 📊 Auditoria (Audit)
Registra histórico de alterações dos chamados.
- `id` (UUID)
- `ticketId` (UUID)
- `alteracao` (String)
- `dataAlteracao` (DateTime)

---

## 🔗 Endpoints da API

### 📌 Chamados

#### 🔹 Criar um chamado (`POST /chamados`)
```json
{
    "titulo": "Computador não liga",
    "descricao": "O computador do setor financeiro não liga após uma queda de energia.",
    "prioridade": "ALTA",
    "usuarioId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

#### 🔹 Listar chamados (`GET /chamados`)
Retorna todos os chamados cadastrados.

#### 🔹 Buscar chamado por ID (`GET /chamados/{id}`)
Retorna um chamado específico pelo seu ID.

#### 🔹 Atualizar chamado (`PUT /chamados/{id}`)
Atualiza os dados de um chamado existente.

#### 🔹 Fechar chamado (`PATCH /chamados/{id}/fechar`)
Altera o status do chamado para "CONCLUIDO" e registra a data de fechamento.

---

### 🛠 Técnicos

#### 🔹 Cadastrar técnico (`POST /tecnicos`)
```json
{
    "nome": "Carlos Silva",
    "email": "carlos.silva@empresa.com",
    "especializacao": "Redes"
}
```

#### 🔹 Listar técnicos (`GET /tecnicos`)
Retorna todos os técnicos cadastrados.

---

### 🔍 Auditoria

#### 🔹 Listar alterações (`GET /auditoria`)
Retorna todas as alterações registradas nos chamados.

#### 🔹 Buscar histórico de um chamado (`GET /auditoria/chamado/{chamadoId}`)
Retorna todas as alterações feitas em um chamado específico.

---

## 🚀 Tecnologias Utilizadas
- **Spring Boot** (Framework principal)
- **Spring Data JPA** (Persistência de dados)
- **Spring Web** (Criação da API REST)
- **Banco de Dados** (MySQL, PostgreSQL ou outro de sua escolha)
- **Docker** (Para conteinerização)

---

