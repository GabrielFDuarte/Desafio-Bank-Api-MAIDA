# Desafio-Bank-Api-MAIDA
API REST bancária para o desafio da Maida;health.

## Sobre a API
API genérica desenvolvida para implementar serviços bancários. A API é construída com Java e Spring Boot e implementa controles de autenticação do Spring Security e Token JWT.

![GitHub release (latest by date)](https://img.shields.io/github/v/release/GabrielFDuarte/Desafio-Bank-Api-MAIDA) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/GabrielFDuarte/Desafio-Bank-Api-MAIDA)

## Endpoints
Essa API provê os seguintes endpoints para as seguintes funcionalidades:

### Users
- Cadastro de usuários: `POST/users`

### Autenticação
- Login de usuário: `POST/auth`

### Accounts
- Cadastro de nova conta: `POST/accounts`
- Transferência entre contas: `POST/accounts/transfer`
- Exibir saldo: `POST/accounts/balance`

## Detalhes
Exemplos de requisições para os diversos endpoints listados anteriormente:

`POST/users`

**Body:**
```json
{
    "email": "default@email.com",
    "password": "123987",
    "name": "John Doe"
}
```
Realiza o cadastro de um usuário.

`POST/auth`

**Body:**
```json
{
    "email": "default@email.com",
    "password": "123987"
}
```
Faz o login do usuário e retorna o token de acesso do usuário para acesso às demais requisições.

`POST/accounts`

**Header:**
```json
{"Authorization": "Bearer TOKEN"}
```
**Body:**
```json
{
    "number": "1234-5",
    "balance": "100.0"
}
```
Cadastro de uma nova conta. Cada usuário pode ter a quantidade de contas que quiser. Nenhuma conta na API pode ter o mesmo número.


`POST/accounts/transfer`

**Header:**
```json
{"Authorization": "Bearer TOKEN"}
```
**Body:**
```json
{
    "source_account_number": "1234-5",
    "destination_account_number": "3145-8",
    "amount": "25.48"
}
```
Realiza a transferência entre contas.

`POST/accounts/balance`

**Header:**
```json
{"Authorization": "Bearer TOKEN"}
```
**Body:**
```json
{
    "account_number": "1234-5"
}
```
Mostra o saldo de uma conta.

## Tecnologias
Projeto desenvolvido com as seguintes tecnologias:
- Java 8
- Spring Boot 2.5.2
- JWT
- Maven

## Package
A API foi desenvolvida para ser executada a partir de um jar. Para que o jar seja gerado, o seguinte comando pode ser executado na pasta do projeto:
```bash
mvn package
```

## Execução
Para executar a API, o jar pode ser executado normalmente ou com o seguinte comando na pasta do projeto:
```bash
mvn spring-boot:run
```
