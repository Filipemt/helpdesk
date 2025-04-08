# 📘 Deskhelp API - Sistema de Helpdesk de Chamados de TI

## 📖 Visão Geral

O sistema de Helpdesk de Chamados de TI é uma API RESTful que permite:

- **Usuários** registrarem problemas técnicos
- **Técnicos** consultarem e resolverem esses chamados
- **Auditoria** completa do histórico de alterações dos chamados

---

## 🧱 Arquitetura

- `Controller`: Camada responsável por lidar com requisições HTTP
- `Service`: Contém a lógica de negócio
- `Repository`: Comunicação com o banco de dados
- `Model`: Contém as entidades, enums e DTOs
- `Exceptions`: Lida com erros e validações personalizadas
- `Validator`: Regras de negócio específicas

---

## 📂 Estrutura das Entidades

### 👤 Usuário

```json
{
  "id": "UUID",
  "nome": "String",
  "email": "String",
  "departamento": "String",
  "cargo": "String"
}
```

### 🔧 Técnico

```json
{
  "id": "UUID",
  "nome": "String",
  "email": "String",
  "especializacao": "String"
}
```

### 📌 Chamado

```json
{
  "id": "UUID",
  "titulo": "String",
  "descricao": "String",
  "status": "Enum: ABERTO | EM_ANDAMENTO | CONCLUIDO",
  "prioridade": "Enum: BAIXA | MEDIA | ALTA",
  "dataCriacao": "DateTime",
  "dataAtualizacao": "DateTime",
  "dataFechamento": "DateTime (opcional)",
  "usuarioId": "UUID",
  "tecnicoId": "UUID (opcional)"
}
```

### 📊 Auditoria

```json
{
  "id": "UUID",
  "chamadoId": "UUID",
  "tituloChamado": "String",
  "descricaoEvento": "String",
  "status": "Enum: ABERTO | EM_ANDAMENTO | CONCLUIDO",
  "dataEvento": "DateTime",
  "usuarioId": "UUID",
  "nomeUsuario": "String",
  "tecnicoId": "UUID (opcional)",
  "nomeTecnico": "String (opcional)"
}
```

---

## 🔗 Endpoints

### 👤 Usuários

#### ✅ Criar usuário
`POST /usuarios`

#### 🔍 Buscar todos
`GET /usuarios`

#### 🔍 Buscar por ID
`GET /usuarios/{id}`

#### 🗑️ Deletar usuário
`DELETE /usuarios/{id}`

---

### 🔧 Técnicos

#### ✅ Criar técnico
`POST /tecnicos`

#### 🔍 Buscar todos / Filtrar por especialização
`GET /tecnicos?especializacao={valor}`

#### 🔍 Buscar por ID
`GET /tecnicos/{id}`

#### ✏️ Atualizar técnico
`PUT /tecnicos/{id}`

#### 🗑️ Deletar técnico
`DELETE /tecnicos/{id}`

---

### 📌 Chamados

#### ✅ Criar chamado
`POST /chamados`

#### 🔍 Buscar todos
`GET /chamados`

#### 🔍 Buscar por ID
`GET /chamados/{id}`

#### ✏️ Atualizar parcialmente (PATCH)
`PATCH /chamados/{id}`

#### 🔁 Atualizar totalmente (PUT)
`PUT /chamados/{id}`

#### 🗑️ Deletar chamado
`DELETE /chamados/{id}`

---

### 📊 Auditoria

#### 🔍 Buscar histórico completo
`GET /auditoria`

**Descrição:** Lista todos os eventos de alteração relacionados aos chamados, com detalhes de quem fez, o que foi alterado, e quando.

**Exemplo de resposta:**

```json
[
  {
    "id": "a1b2c3d4-e5f6-7890-abcd-1234567890ab",
    "chamadoId": "9f2b8c3e-3f27-4217-9cf1-9bde6a07e8a2",
    "tituloChamado": "Computador não liga mais",
    "descricaoEvento": "Status alterado de ABERTO para EM_ANDAMENTO",
    "status": "EM_ANDAMENTO",
    "dataEvento": "2025-04-03T15:42:00",
    "usuarioId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "nomeUsuario": "João Silva",
    "tecnicoId": "c1a22e2d-8e34-4c87-b80c-290a5d74c3f7",
    "nomeTecnico": "Carlos Lima"
  }
]
```

---

## 📦 Retornos Padrão

- `GET`: Retorna JSON com os dados solicitados
- `POST`, `PUT`, `PATCH`, `DELETE`: Retornam apenas status HTTP apropriado (`201`, `204`, `400`, `404`, etc.)

---

## 🧪 Testes com Postman

Você pode utilizar o Postman para testar todos os endpoints da API. Requisições de criação e atualização aceitam `JSON`, enquanto as de consulta retornam informações detalhadas em `JSON` padronizado.

---

## 📌 Observações Finais

- As entidades são identificadas por UUID
- Auditoria é registrada automaticamente ao criar, atualizar ou alterar status de um chamado
- Todas as validações de negócio são feitas via classe `Validator` com exceções personalizadas

---

## 👨‍💻 Autor

Desenvolvido por Filipe (Deskhelp API) — Projeto pessoal com fins de aprendizado e boas práticas em APIs REST com Spring Boot.