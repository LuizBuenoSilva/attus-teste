# Sistema de Gestão de Processos Judiciais - Attus

## 📋 Descrição

O **Sistema de Gestão de Processos Judiciais** é uma aplicação REST desenvolvida em Spring Boot para gerenciar processos judiciais, suas partes envolvidas e ações processuais. O sistema permite criar, consultar, atualizar e arquivar processos judiciais de forma eficiente e organizada.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.10**
- **Spring Data JPA** - Para persistência de dados
- **PostgreSQL** - Banco de dados principal
- **H2 Database** - Banco de dados em memória para testes
- **SpringDoc OpenAPI 3** - Documentação automática da API
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização (docker-compose.yml)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
src/main/java/com/attusTeste/Attus/Teste/
├── config/          # Configurações (Swagger)
├── controller/      # Controladores REST
├── dto/            # Objetos de Transferência de Dados
├── model/          # Entidades JPA
├── repository/     # Repositórios de dados
└── service/        # Lógica de negócio
```

## 📊 Modelo de Dados

### Entidades Principais

1. **JudicialProcess** (Processo Judicial)
   - `id`: Identificador único
   - `caseNumber`: Número único do processo
   - `openingDate`: Data de abertura
   - `description`: Descrição do processo
   - `status`: Status (ATIVO, SUSPENSO, ARQUIVADO)
   - `parties`: Lista de partes envolvidas
   - `actions`: Lista de ações realizadas

2. **Party** (Parte Processual)
   - `id`: Identificador único
   - `fullName`: Nome completo
   - `cpfOrCnpj`: CPF ou CNPJ
   - `partyType`: Tipo da parte (AUTOR, REU, TESTEMUNHA, ADVOGADO)
   - `contact`: Informações de contato
   - `judicialProcess`: Processo relacionado

3. **Action** (Ação Processual)
   - `id`: Identificador único
   - `actionType`: Tipo da ação (AUDIENCIA, DESPACHO, SENTENCA, RECURSO, PETICAO)
   - `actionDate`: Data da ação
   - `description`: Descrição da ação
   - `judicialProcess`: Processo relacionado

## 🔧 Configuração e Instalação

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- PostgreSQL 12+ (ou Docker)
- Git

### 1. Clone o Repositório

```bash
git clone <url-do-repositorio>
cd Attus-Teste
```

### 2. Configuração do Banco de Dados

#### Opção A: Usando Docker (Recomendado)

```bash
# Na raiz do projeto
docker-compose up -d
```

#### Opção B: PostgreSQL Local

1. Crie um banco de dados chamado `attus_db`
2. Crie um usuário `attus_user` com senha `senha123`
3. Configure as permissões necessárias

### 3. Configuração da Aplicação

O arquivo `application.properties` já está configurado para:

```properties
# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5433/attus_db
spring.datasource.username=attus_user
spring.datasource.password=senha123

# Hibernate
spring.jpa.hibernate.ddl-auto=update

# Pool de conexões
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
```

### 4. Executar a Aplicação

```bash
# Compilar e executar
mvn clean install
mvn spring-boot:run

# Ou usando o wrapper
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

### Swagger UI

Acesse a documentação interativa da API em:
**http://localhost:8080/swagger-ui.html**

### Endpoints Principais

#### Processos Judiciais (`/processes`)

- `POST /processes` - Criar novo processo
- `GET /processes` - Listar todos os processos
- `GET /processes/{id}` - Buscar processo por ID
- `PUT /processes/{id}` - Atualizar processo
- `PATCH /processes/{id}/archive` - Arquivar processo
- `GET /processes/search` - Buscar com filtros

#### Partes Processuais (`/parties`)

- `POST /parties` - Criar nova parte
- `GET /parties/byProcess/{processId}` - Buscar partes por processo
- `GET /parties/byCpfOrCnpj` - Buscar partes por CPF/CNPJ

#### Ações Processuais (`/actions`)

- `POST /actions` - Criar nova ação
- `GET /actions/byProcess/{processId}` - Buscar ações por processo

## 🧪 Testes

### Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com relatório de cobertura
mvn test jacoco:report
```

### Banco de Dados de Teste

Os testes utilizam o banco H2 em memória, configurado automaticamente.

## 📝 Exemplos de Uso

### Criar um Processo Judicial

```bash
curl -X POST http://localhost:8080/processes \
  -H "Content-Type: application/json" \
  -d '{
    "caseNumber": "1234567-89.2023.8.26.0001",
    "openingDate": "2023-01-15",
    "description": "Ação de cobrança",
    "status": "ATIVO"
  }'
```

### Adicionar uma Parte ao Processo

```bash
curl -X POST http://localhost:8080/parties \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "João Silva Santos",
    "cpfOrCnpj": "123.456.789-00",
    "partyType": "AUTOR",
    "contact": "joao.silva@email.com",
    "judicialProcess": {"id": 1}
  }'
```

### Registrar uma Ação

```bash
curl -X POST http://localhost:8080/actions \
  -H "Content-Type: application/json" \
  -d '{
    "actionType": "AUDIENCIA",
    "actionDate": "2023-02-15",
    "description": "Audiência de conciliação",
    "judicialProcess": {"id": 1}
  }'
```

## 🔍 Funcionalidades de Busca

### Buscar Processos com Filtros

```bash
# Por status
GET /processes?status=ATIVO

# Busca avançada
GET /processes/search?status=ATIVO&openingDate=2023-01-15&cpfOrCnpj=123.456.789-00
```

## 🐳 Docker

### Usando Docker Compose

O projeto inclui um `docker-compose.yml` para facilitar o setup:

```bash
# Subir todos os serviços
docker-compose up -d

# Parar os serviços
docker-compose down
```

### Construir Imagem da Aplicação

```bash
# Construir JAR
mvn clean package

# Construir imagem Docker (se houver Dockerfile)
docker build -t attus-teste .
```

## 🚀 Deploy

### Variáveis de Ambiente para Produção

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://prod-db:5432/attus_db
export SPRING_DATASOURCE_USERNAME=prod_user
export SPRING_DATASOURCE_PASSWORD=prod_password
export SPRING_PROFILES_ACTIVE=prod
```

### Profile de Produção

Crie um `application-prod.properties` para configurações específicas de produção.

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👥 Equipe

- **Equipe Attus** - Desenvolvimento e Manutenção
- **Contato**: contato@attus.com.br
- **Website**: https://www.attus.com.br

## 📞 Suporte

Para suporte técnico ou dúvidas sobre o sistema:

- 📧 Email: suporte@attus.com.br
- 📱 Telefone: (11) 9999-9999
- 🌐 Website: https://www.attus.com.br/suporte

---

**Desenvolvido com ❤️ pela equipe Attus**