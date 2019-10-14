
# Estrutura de Diretórios

* api: arquivos OpenAPI (Swagger) com a documentação da API.
* backend: código da aplicação em Spring Boot.
* frontend: código da aplicação Angular.io.
* nginx: arquivo de configuração para subir um container Nginx.
* terraform: códigos de IaC (Infrastructure as Code) para subir a aplicação na AWS.

# Backend

## Build

Para construir a aplicação Spring Boot do backend, execute:
```
$ ./backend/gradlew build
```

## Run

Para executar a aplicação Spring Boot do backend, execute:
```
$ ./backend/gradlew bootRun
```

# Docker

Em backend, existe um Dockerfile para construir um container para aplicação.

É possível subir a aplicação e suas dependências (MongoDB e Nginx) através do Docker Composer. Para isso é necessário instalar o Docker Composer e depois executar:

```
$ docker-compose up
```

# Terraform
Implantação na AWS

## Instalação dos utilitários

1. Instalar o Terraform (https://www.terraform.io).
1. Instalar o AWS CLI (https://aws.amazon.com).
1. Configurar o AWS CLI com as credenciais de conta AWS (arquivo **~/.aws/credentials**) com uma profile chama ecommerce.

## Implantação

1. No diretório *terraform*, executar:

```
terraform init
```

1. Em seguida, executar:

```
terraform apply
```
