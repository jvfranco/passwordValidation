<h1>API para validação de senha informada por usuários</h1>

<h3>:key: API em Java e Spring Boot que visa validar uma senha informada pelo usuário. A senha deve conter alguns requisitos para que seja considerada válida:</h3>

1. Possuir nove ou mais caracteres sem espaços em branco
2. Possuir pelo menos um digíto
3. Possuir pelo menos uma letra minúscula
4. Possuir pelo menos uma letra maiúscula
5. Possuir pelo menos um caractere especial, sendo considerados como especiais os caracteres: !@#$%^&*()-+
6. Não possuir caracteres repetidos

<h3>:pushpin: Pré-requisitos</h3>

<p>Existem alguns pré-requisitos antes da execução desta API. Você deverá possuir os itens abaixo:</p>

* [OpenJDK17](https://openjdk.org/projects/jdk/17/)
* [Git](https://git-scm.com/)
* A IDE de sua preferência - [Eclipse](https://www.eclipse.org/) / [Intellij IDEA](https://www.jetbrains.com/pt-br/idea/) / [Spring Tool Suite](https://spring.io/tools)

<h3>:rocket: Execução</h3>

```bash
#Clone o repositório
git clone https://github.com/jvfranco/passwordValidation.git

#Inicie a aplicação na IDE escolhida

#Para acesso via Swagger, utilize o endereço em seu navegador de preferência
http://localhost:8080/swagger-ui/index.html#/

#Caso queira realizar os testes através do Postman, criar uma requisição POST para o endereço:
http://localhost:8080/password/validation

#E enviar o json com a senha no body da requisição:
{
    "password": "AbTp9!fok"
}
```

<h3>:bangbang: Detalhes técnicos</h3>

* Java 11
* Spring Boot 3.0.1
* Banco H2
* OpenAPI
* Lombok
* Testes Unitários com JUnit 5 e Mockito
* Criptografia de senha com BCryptPasswordEncoder
* Regex
* Design Patterns: Builder e Chain of Responsibility

<h3>:scroll: Documentação da API</h3>

* Requisição:
  POST /password/validation

* Body da requisição:

Parâmetro | Tipo | Descrição
:-------: | :--: | :-------:
password | String | Senha que será validada

* Retorno

<h3>Documentação Consultada:</h3>

1. [Class Pattern](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html)
2. [Class Matcher](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Matcher.html#matches())
3. 

    