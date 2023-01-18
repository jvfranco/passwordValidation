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

<h5>:mag: Exemplo:</h5>

```json
{
    "password": "AbTp9!fok"
}
```

* Retorno

Status | Parâmetro | Tipo | Descrição 
:----: | :-------: | :--: | :-------:
200 OK | isValid | Boolean | <b>true</b> caso a senha seja valida e <b>false</b> caso inválida  

<h5>:mag: Exemplo:</h5>

```json
{
  "isValid": true
}
```

<h3>:unlock: Etapas do Desenvolvimento</h3>

<h5>:blue_book: Resumo</h5>

Para criação da solução pensei em dois princípios da Orientação a Objetos, a Coesão e o Acoplamento. Criei uma classe para cada uma das validações, buscando evitar
grandes classes com diversos métodos, melhorando a leitura e entendimento do código. Para executar as validações pensei em utilizar expressões regulares, não utilizo comumente
em meu dia a dia, o que necessitou de pesquisas adicionais para implementação no desafio. Para validação de caracteres duplicados utilizei a coleção _set_, que não permite itens duplicados,
verificando o tamanho da senha antes e pós adição na collection. Para organização dos pacotes, pensei em separar as classes relacionadas a entidade, Password, das classes relacionados
a regras de negócio das validações. Também utilizei o _design pattern Chain of Responsibility_.

<h5>:green_book: Detalhes</h5>

* Para Execução das validações utilizei expressões regulares, não estou habituado em utilizar a classe Pattern do Java, o que demandou pesquisa e leitura da documentação.

* Na validação de caracteres duplicados utilizei uma _collection Set_, no caso _HashSet_, faço um _split_ na senha e depois adiciono os caracteres na _collection_, como os
_sets_ não permitem itens duplicados, caso haja caracteres duplicados, um não será inserido na _collection_, o que modificará o tamanho da senha, com isso comparo o tamanho da 
_collection_ com a senha original e verifico se estão ou não com o mesmo tamanho.

* Para a separação dos pacotes, pensei em deixar as classes relacionadas ao modelo da senha em um pacote denominado _domain_, onde tenho acesso a base de dados. Outro pacote denominado 
_configuration_, onde deixei as classes de configuração, no caso somente a configuração do SpringDoc OpenAPI. E um terceiro pacote denominado _application_, onde coloquei as classes
de modelo de request e response, utilizei as classes _Record_ do Java, para manter a imutabilidade dos objetos, controller com um endpoint POST ( /password/validation ) e as classes de validação da senha, agrupando as regras de negócio.

* Resolvi deixar as classes de validação com somente um método em cada, tentando criar classes coesas, com poucos atributos e somente uma função, penso que classes sem coesão tendem a
crescer demasiadamente conforme evolução da aplicação, o que acaba descaracterizando o motivo de sua criação. Busquei atender o primeiro princípio do SOLID, 
o Princípio da Responsabilidade Única.

* No desenvolvimento percebi que as validações poderiam ser realizadas com vários _ifs_, então resolvi pesquisar por um _design pattern_ que se encaixassem no meu desenvolvimento,
e encontrei o _Chain of Responsibility_, no qual crio uma corrente ( _chain_ ) com todas as classes das validações que desejo realizar e conforme as validações se confirmam elas próprias
chamam a próxima validação, caso a senha não seja validada lanço uma _exception_ ( _ValidatorException_ ), a qual encerra a execução das validações e informo através de logs o motivo da não validação, 
nesse caso o retorno da API será _false_ e a senha não será gravada em banco. Quando a senha é validada, é gravada em banco depois de ser criptografada com a classe _BCryptPasswordEncoder_.

* Deixei o retorno da API com código HTTP 200 OK, mesmo que a senha não tenha sido validada, deixei desta forma porque demonstra que ocorreu o processamento e que a senha não passou nos critérios pré definidos.

<h3>:books: Documentação Consultada:</h3>

1. [Class Pattern](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html)
2. [Class Matcher](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Matcher.html#matches())
3. [Class BCryptPasswordEncoder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html)
4. [SpringDoc](https://springdoc.org/v2/#migrating-from-springfox)

    