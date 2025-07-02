# 📘 Contribuição e Padrões de Organização – Projeto Deskhelp

Este documento define os padrões de organização do repositório Deskhelp, com foco em nomeação de branches, commits e práticas para garantir qualidade, rastreabilidade e colaboração futura.

---

## 🗂️ Padrão de Nomes de Branches

Utilize o seguinte formato para nomear suas branches:
### Tipos mais comuns:

| Tipo       | Finalidade                                      |
|------------|--------------------------------------------------|
| `feature/` | Novas funcionalidades                            |
| `bugfix/`  | Correção de bugs                                 |
| `refactor/`| Refatorações internas sem novas funcionalidades |
| `hotfix/`  | Correções urgentes em produção                  |
| `chore/`   | Tarefas técnicas (documentação, configs, etc)    |

### Exemplos reais:

- `feature/security-refactor-user-role`
- `feature/jwt-authentication`
- `refactor/usuario-service-cleanup`
- `bugfix/fix-null-pointer-chamado`
- `chore/update-readme-docs`

---

## ✅ Convenção de Commits

Use a convenção semântica nos commits para facilitar a leitura e automações futuras:
### Tipos recomendados:

| Tipo        | Uso                                                                 |
|-------------|----------------------------------------------------------------------|
| `feat:`     | Nova funcionalidade ou endpoint                                     |
| `fix:`      | Correção de bug                                                    |
| `refactor:` | Refatoração sem mudança de comportamento externo                   |
| `chore:`    | Atualizações técnicas (configs, deps, docs)                        |
| `docs:`     | Mudanças em documentação                                           |
| `test:`     | Criação ou ajuste de testes                                        |

### Exemplos:

- `feat: adicionar campo Role à entidade Usuario`
- `refactor: mover lógica de criação de técnico para UsuarioService`
- `fix: corrigir erro de autenticação com senha criptografada`
- `docs: atualizar README com nova estrutura de segurança`

---

## 📦 Organização de Sprints e Issues

- Cada Sprint tem suas issues com a label `sprint: N`
- As issues devem conter:
    - Objetivo claro
    - Checklist de etapas
    - Justificativa (quando necessário)

---

## ✅ Pull Requests

- Vincule sempre uma issue ao PR, usando:  
  `closes #12` ou `resolves #5`
- Utilize título padronizado:  
  `feat: implementar login com JWT`
- Faça review com atenção e explique mudanças importantes no corpo do PR.

---

## 🛡️ Boas Práticas Gerais

- Cada commit deve representar uma mudança funcional e testável.
- Prefira pequenas mudanças em várias branches do que grandes PRs difíceis de revisar.
- Sempre documente mudanças estruturais no `README.md` ou nos arquivos específicos do módulo.

---

_Última atualização: Julho/2025_