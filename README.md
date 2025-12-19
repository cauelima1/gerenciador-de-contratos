# 📑 Gerenciador de Orçamentos

Aplicação desenvolvida em **Java 21** utilizando **Spring Boot 3.5.4** e **Thymeleaf** para gerenciamento de orçamentos.  
Permite cadastrar, filtrar, editar, excluir e imprimir orçamentos em PDF com numeração auto-incrementada.

---

## 🚀 Funcionalidades

- **Cadastro de orçamentos** com informações básicas.  
- **Filtragem** por status:
  - Em aberto  
  - Aprovados  
  - Não aprovados  
- **Operações CRUD**:
  - Alterar  
  - Excluir  
  - Deletar  
- **Impressão em PDF** dos orçamentos com numeração sequencial automática.  
- **Segurança** integrada com Spring Security e JWT.  

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
  - Spring Web  
  - Spring Data JPA  
  - Spring Security  
- **Thymeleaf 3.5.0**
- **Banco de dados**:
  - MySQL (produção)  
  - H2 (testes)  
- **Lombok** para simplificação de código  
- **JWT (Auth0 / JJWT)** para autenticação  
- **Maven** para gerenciamento de dependências  

---

## 📦 Dependências principais (pom.xml)

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
        <version>3.5.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
        <version>3.3.4</version>
    </dependency>

    <!-- Banco de dados -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- JWT -->
    <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>4.4.0</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.3</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.3</version>
        <scope>runtime</scope>
    </dependency>

    <!-- Utilitários -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## ⚙️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gerenciador-de-contratos.git
   ```
2. Entre na pasta do projeto:
   ```bash
   cd gerenciador-de-contratos
   ```
3. Configure o banco de dados MySQL no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/gerenciador
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Compile e execute:
   ```bash
   mvn spring-boot:run
   ```
5. Acesse no navegador:
   ```
   http://localhost:8080
   ```

---

## 📄 Impressão em PDF

- Cada orçamento pode ser exportado em PDF.  
- O sistema gera **numeração automática sequencial** para cada documento.  

---

## 🔒 Segurança

- Autenticação e autorização com **Spring Security**.  
- Tokens JWT para controle de sessão.  

---

## 👨‍💻 Autor

- **Cauê Lima**  
- Projeto acadêmico/profissional para gerenciamento de contratos e orçamentos.  
