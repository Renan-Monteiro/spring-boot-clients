# Builders Cliente - Spring Boot

**Sumário**

1. [Sobre o projeto](#1-sobre-o-projeto)
2. [Build e execução local](#2-build-e-execução-local)
   - 2.1. [Com arquivo jar](#21-com-arquivo-jar)
   - 2.2. [Com Docker](#22-com-docker)
3. [Acessar o projeto](#3-acessar-o-projeto)
4. [Deploy no kubernetes](#4-deploy-no-kubernetes)

:smiley:
## 1 Sobre o projeto

Exemplo de aplicação Java com Spring boot para cadastro de clientes.

## 2 Build e execução local (#2-build-e-execução-local)

### 2.1 Com arquivo .jar
Para gerar a versão executável do projeto com a extensão .jar é necessário executar o comando abaixo no diretório raiz:

```bash
mvn clean package
```

Execute o comando abaixo para iniciar o projeto

```bash
java -jar ~\target\transformacao-digital.jar
```

### 2.2 Com Docker
Para gerar a versão executável do projeto com Docker é necessário executar o comando abaixo no diretório raiz:
```bash
mvn clean package docker:build
```

Executar o comando abaixo para iniciar o container Docker no diretório dos arquivos Dockerfile e docker-compose:
```bash
docker build -t builderscliente:1 .
docker run builderscliente:1
```

## 3 Acessar o projeto

Para acessar o projeto digite no navegador o endereço: http://localhost:8080/swagger-ui.html


## 4 Deploy no kubernetes

### Upload da image no registry

```bash
docker build -t <my-registry-url>/builderscliente:1 .
docker push <my-registry-url>/builderscliente:1
```

### Deploy no kubernetes
Para efetuar o deploy no kubernetes utilizar o manifesto de exemplo "deployment.yaml" e ter a imagem publicada em algum repositório

```bash
kubectl apply -f deployment.yaml
```
