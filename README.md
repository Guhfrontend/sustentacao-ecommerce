# Sustentação E-commerce

## Sobre o Projeto

Este é um sistema de sustentação para e-commerce desenvolvido em Spring Boot com Java. O projeto oferece funcionalidades de gestão de usuários, sistema de tickets de suporte e integração com SAP Hybris, proporcionando uma base sólida para operações de suporte e manutenção de plataformas de comércio eletrônico.

## Funcionalidades

- **Gestão de Usuários**: CRUD completo para usuários, incluindo autenticação e login via JWT
- **Sistema de Tickets**: Criação e gerenciamento de tickets de suporte
- **Integração SAP Hybris**: Cliente para comunicação com sistema SAP Hybris para consulta de produtos
- **Mock de APIs**: Simulação de endpoints SAP Hybris para desenvolvimento e testes
- **Monitoramento**: Integração com Prometheus para coleta de métricas
- **Visualização**: Dashboard Grafana para análise de dados e métricas
- **Banco de Dados**: MongoDB como sistema de persistência
- **Documentação**: Swagger/OpenAPI para documentação da API

## Tecnologias Utilizadas

- **Backend**: Spring Boot 3.x, Java 17+
- **Segurança**: Spring Security com JWT
- **Banco de Dados**: MongoDB
- **Integração**: RestTemplate para comunicação com SAP Hybris
- **Monitoramento**: Prometheus
- **Visualização**: Grafana
- **Documentação**: SpringDoc OpenAPI 3 (Swagger)
- **Containerização**: Docker & Docker Compose

## Pré-requisitos

- Docker
- Docker Compose
- Git

## Como Executar o Projeto

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd sustentacaoEcommerce
```

### 2. Execute com Docker Compose
```bash
docker-compose up -d
```

Este comando irá:
- Construir a aplicação Spring Boot
- Subir o MongoDB
- Iniciar o Prometheus
- Configurar o Grafana

### 3. Aguarde a inicialização
Os serviços podem levar alguns minutos para inicializar completamente.

## Acesso aos Serviços

### Aplicação Principal
- **URL**: http://localhost:8080
- **Documentação da API**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs

#### Endpoints principais:

**Usuários**
- `GET /users` - Listar usuários (requer autenticação)
- `GET /users/{id}` - Buscar usuário por ID (requer autenticação)
- `POST /users/cadastrar` - Cadastrar novo usuário (público)
- `POST /users/logar` - Login de usuário (público)
- `PUT /users/{id}` - Atualizar usuário completo (requer autenticação)
- `PATCH /users/{id}` - Atualizar usuário parcial (requer autenticação)
- `DELETE /users/{id}` - Deletar usuário (requer autenticação)

**Tickets de Suporte**
- `GET /api/tickets` - Listar todos os tickets (requer autenticação)
- `GET /api/tickets/{id}` - Buscar ticket por ID (requer autenticação)
- `GET /api/tickets/user/{userId}` - Listar tickets por usuário (requer autenticação)
- `POST /api/tickets` - Criar novo ticket (requer autenticação)
- `PUT /api/tickets/{id}` - Atualizar ticket (requer autenticação)

**SAP Hybris (Mock)**
- `GET /mock/sap-hybris/products/{sku}` - Buscar produto por SKU (público)

**Monitoramento**
- `GET /actuator/health` - Status da aplicação
- `GET /actuator/metrics` - Métricas da aplicação
- `GET /actuator/prometheus` - Métricas para Prometheus

### Prometheus
- **URL**: http://localhost:9090
- **Descrição**: Interface para consultas de métricas e monitoramento da aplicação

### Grafana
- **URL**: http://localhost:3000
- **Credenciais**:
  - **Usuário**: admin
  - **Senha**: admin
- **Descrição**: Dashboard para visualização de métricas e criação de relatórios

### MongoDB
- **Porta**: 27017
- **Credenciais**:
  - **Usuário**: root
  - **Senha**: root

## Configuração do Grafana

1. Acesse http://localhost:3000
2. Faça login com admin/admin
3. Adicione o Prometheus como data source:
   - URL: http://prometheus:9090
4. Importe dashboards ou crie seus próprios

Dashboards recomendados: 4701 (Source do Prometheus)

<img width="1237" height="591" alt="image" src="https://github.com/user-attachments/assets/7044a2dd-cb6a-47f7-9e41-731678a38e91" />


### Verificar logs dos containers
```bash
# Ver logs de todos os serviços
docker-compose logs

# Ver logs de um serviço específico
docker-compose logs prometheus
docker-compose logs ecomerce
docker-compose logs mongo
docker-compose logs grafana
```

### Parar os serviços
```bash
docker-compose down
```

### Reconstruir e reiniciar
```bash
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```
## Desenvolvimento

Para desenvolvimento local sem Docker:

1. Configure o MongoDB local
2. Ajuste as configurações em `src/main/resources/application.yml`
3. Execute: `./gradlew bootRun`

## Autenticação e Segurança

O sistema utiliza JWT (JSON Web Tokens) para autenticação:

### Endpoints Públicos
- `/users/cadastrar` - Cadastro de usuários
- `/users/logar` - Login
- `/mock/**` - APIs mock do SAP Hybris
- `/actuator/**` - Endpoints de monitoramento
- `/swagger-ui/**` - Documentação da API
- `/v3/api-docs/**` - Especificação OpenAPI

### Como Obter Token
1. Cadastre um usuário via `POST /users/cadastrar`
2. Faça login via `POST /users/logar`
3. Use o token retornado nas próximas requisições

## API Documentation

### Usuários
- `GET /users` - Lista todos os usuários
- `GET /users/{id}` - Busca usuário por ID
- `POST /users/cadastrar` - Cadastra um novo usuário
- `POST /users/logar` - Autentica um usuário e retorna JWT
- `PUT /users/{id}` - Atualiza usuário completo
- `PATCH /users/{id}` - Atualização parcial de usuário
- `DELETE /users/{id}` - Remove usuário

### Tickets de Suporte
- `GET /api/tickets` - Lista todos os tickets
- `GET /api/tickets/{id}` - Busca ticket por ID
- `GET /api/tickets/user/{userId}` - Lista tickets por usuário
- `POST /api/tickets` - Cria um novo ticket
- `PUT /api/tickets/{id}` - Atualiza um ticket

## Solução de Problemas

### Container do Prometheus não inicia
- Verifique se o arquivo `config/prometheus.yml` existe
- Confirme se a porta 9090 não está em uso

### Aplicação não conecta ao MongoDB
- Verifique se o container do MongoDB está rodando
- Confirme as credenciais no docker-compose.yml