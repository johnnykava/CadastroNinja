# Sistema de Cadastro Ninja

Bem-vindo ao **Sistema de Cadastro de Ninjas**!  
Este projeto é uma aplicação de arquitetura em camadas desenvolvida com **Spring Boot**, projetada para cadastrar ninjas e suas respectivas missões.

O projeto utiliza:
- **H2** como banco de dados em memória
- **Flyway** para migrações
- **Git** para controle de versão
- Hospedagem do repositório no **GitHub**

## Visão Geral do Projeto
Este sistema foi desenvolvido para gerenciar ninjas e suas missões.

**Funcionalidades:**
- Cadastro de ninjas com nome, idade, email, rank e imagem.
- Atribuição de uma missão para um ninja.
- Gerenciamento de missões e dos ninjas associados.

## Tecnologias Utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot): Para criação da aplicação web e gerenciamento de dependências.
- [H2 Database](https://www.h2database.com/): Banco de dados em memória para desenvolvimento e testes.
- [Flyway](https://flywaydb.org/): Para gerenciamento de migrações do banco de dados.
- [JPA](https://jakarta.ee/specifications/persistence/): Para mapeamento objeto-relacional (ORM).
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): Para interação com o banco de dados.
- [Maven](https://maven.apache.org/): Para build e gerenciamento de dependências do projeto.
- [Thymeleaf](https://www.thymeleaf.org/): Para criação de um frontend básico.
- [Lombok](https://projectlombok.org/): Para reduzir código repetitivo.
- [OpenAPI 3](https://www.openapis.org/) - Padrão para documentação de APIs REST.
- [Swagger UI](https://swagger.io/tools/swagger-ui/) - Interface para explorar e testar a API.
- SQL: Manipulação do banco de dados
- Git + GitHub: Controle de versão para gerenciamento de mudanças no código.

## Pré-Requisitos
Antes de começar, você precisa ter instalado em sua máquina:
- [Java 21](https://www.oracle.com/br/java/technologies/downloads/#java21)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)


## Configuração

```bash
# Clone o repositório
git clone https://github.com/johnnykava/CadastroNinja.git

# Acesse o diretório
cd CadastroNinja

# Construa o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run