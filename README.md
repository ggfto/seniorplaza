# seniorplaza
Senior Plaza Hotel API

## Documentação
API com funcionalidades de cadastro de pessoas e reservas, checkin e checkout.

## Regras de negócio requeridas
 - custo da diária em dias normais: R$ 120,00
 - custo da diária em fins de semana: R$ 150,00
 - custo diário da vaga de garagem em dias normais: R$ 15,00
 - custo diário da vaga de garagem em fins de semana: R$ 20,00
 - checkout após as 16:30 tem o custo de uma diária extra

## Requisitos
 - padrão REST de comunicação
 - baseado em springboot
 - implementação de testes unitários

## Frameworks utilizados
 - Lombok
 - jUnit
 - H2Database
 - Swagger

## Endpoints
Os endpoints podem ser consultados via API do Swagger, que se encontra em /swagger-ui/index.html

## Docker
Neste repositório há um arquivo Dockerfile, para que a API possa ser distribuída em container.
Instruções de build:
    - executar o comando "mvn clean package", para gerar o arquivo .jar;
    - executar o comando "docker build -t gf2/seniorplaza ."
    - executar o comando "docker run -p 8080:8080 gf2/seniorplaza" para inicializar o container.

## Demo
A API encontra-se publicada no heroku, no endereço: https://seniorplaza.herokuapp.com/
OBS.: Por se tratar de um dyno gratuito, a API entra em hibernação e pode demorar a retornar a resposta inicial.