[![CircleCI](https://circleci.com/gh/PedroFrancisco/API-SpringBoot-Docker-CircleCi-Codecov.svg?style=svg)](https://circleci.com/gh/PedroFrancisco/API-SpringBoot-Docker-CircleCi-Codecov)
[![codecov](https://codecov.io/gh/PedroFrancisco/starWarsAPI/branch/master/graph/badge.svg)](https://codecov.io/gh/PedroFrancisco/starWarsAPI)

# Descrição

API com dados de planetas da franquia Star Wars. Implementação utilizando docker-compose, springboot, circleCi, maven, codecov e mongodb.

# Pré-requisitos

Java 8

Maven

Eclips

Docker 

MongoDB

JUnit

Mockmvc

# Instruções

Todos os comandos deverão ser executado via terminal.

Fazer o checkout do projeto e na raiz executar o comando abaixo:

<b>mvn clean compile install</b>

Com o build realizado com sucesso, executar o comando abaixo para criar os conteiners Java e MongoDB:

<b>docker-compose up --build</b>

A partir de agora você poderá acessar os serviços pelos endpoints.

# Endpoints

Endpoints necessário para consumir os serviços da aplicação:

Criar planeta

<b>POST - http://localhost:8080/api/v1/planetas</b>

Segue abaixo, exemplo com os campos para realizar a chamada JSON:

<b>{ 

"nome":"",

"clima":"", 

"terreno":""

}</b>

Listar planetas

<b>GET - http://localhost:8080/api/v1/planetas</b>

buscar planeta pelo ID

<b>GET - http://localhost:8080/api/v1/planetas/{id}</b>

buscar planeta pelo nome

<b>GET - http://localhost:8080/api/v1/planetas/{nome}</b>

Deletar planeta

<b>DELETE - http://localhost:8080/api/v1/planetas/{id}</b>
