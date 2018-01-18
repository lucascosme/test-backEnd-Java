Requisitos:
Java 8

Quick install:

Para rodar o projeto faça um clone do repositório em seu workspace.
Em ambiente Unix rode o comando:
mvn spring-boot:run
No browser colocar a seguinte url: http://localhost:8080/home.

Para acessar o banco utilizar a url: http://localhost:8080/h2-console.
JDBC URL: jdbc:h2:mem:playercontrol
User name: sa
Password:

IDE:

Rodar o método main a partir da classe: TestBackEndJavaApplication.class


Features implementadas:

Cadastro do jogador.
Consumo de informações JSON.
Alguns testes de unidade.
Exibição de relatório dos jogadores cadastrados.

Features faltantes:

Consumo de informações XML.

Pontos á melhorar:

Validação dos dados do jogador.
Criação de mais testes de unidade para garantir 100% de cobertura do código.
Melhorias no design da arquitetura do projeto.
Exportar informações do relatório.

Stack:
Java 8
Maven 3
Spring Boot
Thymeleaf
H2 Memory database
