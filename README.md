# To-Do List API - Mini Curso Java Rocketseat

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 📖 Sobre o Projeto

Este é o repositório do projeto **To-Do List**, uma API RESTful desenvolvida em **Java com Spring Boot**.  
O projeto foi criado como parte de um mini curso prático da [Rocketseat](https://www.rocketseat.com.br/), com foco em construir uma aplicação back-end para gerenciamento de tarefas.

A aplicação permite que usuários se cadastrem, se autentiquem e gerenciem suas próprias listas de tarefas (Tasks).

## ✨ Funcionalidades Principais

- **👤 Gerenciamento de Usuários**: Criação de novos usuários com `username` e `password`.
- **🔐 Autenticação**: Sistema de autenticação Basic Auth para proteger as rotas de tarefas.
- **📝 Gerenciamento de Tarefas (CRUD)**:
  - Criação de novas tarefas.
  - Listagem de todas as tarefas de um usuário.
  - Atualização de tarefas existentes.
  - Exclusão de tarefas.
- **✔️ Validações de Dados**: Como a verificação de título com no máximo 50 caracteres e a garantia de que um `username` não seja duplicado.
- **🐳 Suporte a Docker**: A aplicação vem com um `Dockerfile` pronto para execução em contêineres.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Maven**
- **Docker**

## 🏁 Como Executar o Projeto

Você pode executar a aplicação de duas maneiras: localmente usando Maven ou via Docker.

### ✅ Pré-requisitos

- [OpenJDK 21](https://openjdk.org/) ou superior
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/products/docker-desktop/) (opcional)

### 🔧 Rodando Localmente

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/todolist.git

# 2. Acesse o diretório do projeto
cd todolist

# 3. Execute a aplicação com Maven
./mvnw spring-boot:run  
``` 

### 🔧 Testando Remotamente



- https://minicurso-java-springboot-rocketseat.onrender.com/user  
    - ex:Post

```json
{
    "name": "meuNome",
    "username": "meuUserName",
    "password": 123456
}
```
- https://minicurso-java-springboot-rocketseat.onrender.com/tasks
    - ex:Post

```json
{   
    "description": "Tarefa todolist com java",
    "title": "aula de java2",
    "startAt": "2025-10-06T12:30:30",
    "endAt": "2025-10-07T12:30:30",
    "priority": "ALTA"    

}
```

- https://minicurso-java-springboot-rocketseat.onrender.com/tasks/{id}
    - ex:Put

```json
{   
    "description": "Tarefa todolist com java",
   
}
```


- https://minicurso-java-springboot-rocketseat.onrender.com/tasks/{id}
    - ex:Delete 
   
    
- https://minicurso-java-springboot-rocketseat.onrender.com/tasks
    - ex:get

