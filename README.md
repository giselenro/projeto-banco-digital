Projeto Banco Digital
============================

Infra
--------

- Docker

1 - Tenha o Docker instalado

2 - Criar uma pasta chamada "Docker"

3 - Dentro da pasta "Docker", criar o arquivo "docker-compose.yml" com o seguinte conteúdo:
```
version: '3.7'

services: 
    mongo:
        container_name: mongo-container
        image: mongo:latest
        restart: always
        ports: 
            - 27017:27017
        environment: 
            MONGO_INITDB_ROOT_USERNAME: root
            MONGO_INITDB_ROOT_PASSWORD: password
            MONGO_INITDB_ROOT_DATABASE: root-db
        
        volumes: 
            - ./docker-entrypoint-initdb.d/mongo-init.js:/docker-entrypoint-initdb.d/mongo-init.js:ro
```            
4 - No mesmo diretório "Docker", criar a pasta "docker-entrypoint-initdb.d"

5 - Dentro da pasta "docker-entrypoint-initdb.d" criar o arquivo "mongo-init.js" com o seguinte conteúdo:
```
print('Start creating database ##########################')
db = db.getSiblingDB('banco_proposta_db');
db.createUser(
    {
        user: 'api_user',
        pwd:  'api1234',
        roles: [{role: 'readWrite', db: 'banco_proposta_db'}],
    }
);

db = db.getSiblingDB('banco_db');
db.createUser(
    {
        user: 'api_user',
        pwd:  'api1234',
        roles: [{role: 'readWrite', db: 'banco_db'}],
    }
);
print('End creating database ##########################')
```

6 - Rodar o seguinte comando para criar um container do mongodb no Kubernetes
```
docker-compose -f "docker-compose.yml" up -d --build
```
