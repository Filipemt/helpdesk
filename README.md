# 🛠️ Deskhelp API

**Deskhelp** é uma API RESTful desenvolvida em Java com Spring Boot, com o objetivo de simular um sistema de helpdesk para abertura, acompanhamento e resolução de chamados de suporte técnico.  
O projeto tem fins educacionais e demonstra boas práticas de arquitetura, segurança, versionamento e organização de código.

---

## 🚀 Tecnologias e Ferramentas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Security (em andamento)
- Swagger / OpenAPI
- Git + GitHub Projects
- Maven

---

## 📁 Estrutura Modular do Projeto

Organizado por **módulos funcionais**, cada um com suas camadas internas:

```text
src/main/java/br/com/filipecode/DeskhelpApi/
│
├── chamado/       # Módulo de chamados (entidade, controller, service, etc)
├── usuario/       # Usuários e técnicos unificados com controle de papéis (Role)
├── auditoria/     # Histórico de ações nos chamados
├── shared/        # Exceções, utilitários e padrões globais
└── DeskhelpApiApplication.java  # Entry point da aplicação
```

## 🔐 Segurança

### 🔄 Status atual
- 🔄 Refatoração para unificação de `Usuario` e `Tecnico`
- 🔄Criptografia de senha com `BCryptPasswordEncoder`
- 🔄 Em desenvolvimento: autenticação via Spring Security com JWT

### 🔜 Planejado
- Autenticação JWT com token Bearer
- Controle de acesso baseado em `Role` (`USUARIO`, `TECNICO`, `ADMIN`)
- Proteção de rotas sensíveis
- Autorização com `@PreAuthorize`

---

## 📌 Sprints de Evolução

Este projeto é organizado em sprints, seguindo um roadmap técnico de implementação.  
As tarefas são gerenciadas no **GitHub Projects (Kanban)** e divididas em:

| Sprint | Objetivo principal                            | Status      |
|--------|-----------------------------------------------|-------------|
| Sprint 1 | Refatoração de Usuário e papel (Role)         | ✅ Em andamento |
| Sprint 2 | Autenticação com Spring Security (sem JWT)    | 🔜 Planejada |
| Sprint 3 | JWT Token e autenticação stateless            | 🔜 Planejada |
| Sprint 4 | Autorização baseada em papel (role-based)     | 🔜 Planejada |
| Sprint 5 | Melhorias avançadas de segurança              | 🔜 Opcional  |

---

## 🧑‍💻 Como rodar o projeto localmente

```bash
# Clonar o projeto
git clone https://github.com/Filipemt/helpdesk.git

# Entrar na pasta do projeto
cd helpdesk

# Rodar com sua IDE ou usar:
./mvnw spring-boot:run

Certifique-se de configurar corretamente o banco de dados PostgreSQL e as variáveis de ambiente.
🧪 Testes de Requisição

A aplicação pode ser testada via:
	•	✅ Postman
	•	✅ Swagger (acessível em /swagger-ui.html após iniciar a aplicação)

Exemplos de endpoints:
GET    /usuarios
POST   /usuarios
POST   /login
GET    /chamados
POST   /chamados
...
```
## 🧾 Padrões de Contribuição

O projeto segue padrões de organização profissional:

	•	Nomenclatura de branches: feature/security-refactor-user-role
	•	Commits semânticos: feat:, refactor:, fix:
	•	Issues com labels e checklist
	•	Sprint organizada por GitHub Projects

Veja mais detalhes no arquivo CONTRIBUTING.md

⸻

📚 Aprendizados e Propósito

Esse projeto foi construído com o objetivo de aprender de forma prática como aplicar:

	•	Arquitetura modular por domínio (por feature)
	•	Camadas bem definidas (controller, service, repository, DTOs)
	•	Validações e tratamento de exceções personalizados
	•	Implementação progressiva de segurança com Spring Security
	•	Uso de Kanban, issues e boas práticas de versionamento
👨‍💻 Autor

Filipe – Desenvolvedor Java em formação e entusiasta de arquitetura limpa.

LinkedIn: https://www.linkedin.com/in/filipe-mota-b15139231/

📄 Licença

Este projeto está licenciado sob a MIT License.