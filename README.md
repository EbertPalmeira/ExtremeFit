# 🏋️ API de Academia - Extreme Fit

API REST para gerenciamento de academia com cadastro de alunos, professores, treinos e exercícios.

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Flyway
- Lombok
- Maven

## 📋 Funcionalidades

- ✅ CRUD de Alunos
- ✅ CRUD de Professores
- ✅ CRUD de Treinos
- ✅ CRUD de Exercícios
- ✅ Relacionamentos (OneToMany, ManyToMany)
- ✅ Soft delete (exclusão lógica)
- ✅ Paginação e ordenação
- ✅ Autenticação com JWT (em desenvolvimento)

## 🔗 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/aluno` | Cadastrar aluno |
| GET | `/aluno` | Listar alunos |
| PUT | `/aluno` | Atualizar aluno |
| DELETE | `/aluno/{id}` | Remover aluno |
| POST | `/professor` | Cadastrar professor |
| GET | `/professor` | Listar professores |
| POST | `/treino` | Cadastrar treino |
| GET | `/treino` | Listar treinos |
| POST | `/exercicio` | Cadastrar exercício |
| GET | `/exercicio` | Listar exercícios |
| POST | `/login` | Autenticar usuário |
| POST | `/usuarios` | Cadastrar usuário |

## 🛠️ Como rodar

```bash
# Clonar o projeto
git clone https://github.com/seu-usuario/extreme-fit.git


