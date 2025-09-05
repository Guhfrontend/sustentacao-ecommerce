# Sustentação E-commerce

## 📋 Sobre o Projeto

Este é um sistema de sustentação para e-commerce desenvolvido em Spring Boot com Java. O projeto oferece funcionalidades de gestão de usuários e sistema de tickets de suporte, proporcionando uma base sólida para operações de suporte e manutenção de plataformas de comércio eletrônico.

## 🚀 Funcionalidades

- **Gestão de Usuários**: CRUD completo para usuários, incluindo autenticação e login
- **Sistema de Tickets**: Criação e gerenciamento de tickets de suporte
- **Monitoramento**: Integração com Prometheus para coleta de métricas
- **Visualização**: Dashboard Grafana para análise de dados e métricas
- **Banco de Dados**: MongoDB como sistema de persistência

## 🛠️ Tecnologias Utilizadas

- **Backend**: Spring Boot 3.x, Java 17+
- **Banco de Dados**: MongoDB
- **Monitoramento**: Prometheus
- **Visualização**: Grafana
- **Containerização**: Docker & Docker Compose

## 📦 Pré-requisitos

- Docker
- Docker Compose
- Git

## 🚀 Como Executar o Projeto

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

## 🔗 Acesso aos Serviços

### Aplicação Principal
- **URL**: http://localhost:8080
- **Endpoints principais**:
  - `GET /users` - Listar usuários
  - `POST /users` - Criar usuário
  - `POST /users/login` - Login de usuário
  - `GET /api/tickets` - Listar tickets
  - `POST /api/tickets` - Criar ticket

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

## 📊 Configuração do Grafana

1. Acesse http://localhost:3000
2. Faça login com admin/admin
3. Adicione o Prometheus como data source:
   - URL: http://prometheus:9090
4. Importe dashboards ou crie seus próprios

Dashboards recomendados: 4701 (Source do Prometheus)

<img width="1237" height="591" alt="image" src="https://github.com/user-attachments/assets/7044a2dd-cb6a-47f7-9e41-731678a38e91" />


## 🐛 Solução de Problemas

### Container do Prometheus não inicia
- Verifique se o arquivo `config/prometheus.yml` existe
- Confirme se a porta 9090 não está em uso

### Aplicação não conecta ao MongoDB
- Verifique se o container do MongoDB está rodando
- Confirme as credenciais no docker-compose.yml

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

## 📁 Estrutura do Projeto

```
sustentacaoEcommerce/
├── src/main/java/               # Código fonte da aplicação
├── config/prometheus.yml        # Configuração do Prometheus
├── docker-compose.yml           # Orquestração dos containers
├── Dockerfile                   # Imagem da aplicação
├── k8s/                        # Manifests para Kubernetes
└── README.md                   # Esta documentação
```

## 🔧 Desenvolvimento

Para desenvolvimento local sem Docker:

1. Configure o MongoDB local
2. Ajuste as configurações em `src/main/resources/application.yml`
3. Execute: `./gradlew bootRun`

## 📝 API Documentation

### Usuários
- `GET /users` - Lista todos os usuários
- `POST /users` - Cria um novo usuário
- `POST /users/login` - Autentica um usuário

### Tickets de Suporte
- `GET /api/tickets` - Lista todos os tickets
- `GET /api/tickets/{id}` - Busca ticket por ID
- `GET /api/tickets/user/{userId}` - Lista tickets por usuário
- `POST /api/tickets` - Cria um novo ticket
- `PUT /api/tickets/{id}` - Atualiza um ticket
