# Teste Técnico Viasoft - Email Service

## Descrição
Aplicação REST desenvolvida para o teste técnico da Viasoft. Recebe um objeto com informações de e-mail, adapta para AWS ou OCI com base na configuração do `application.properties`, valida os dados, e simula o envio do e-mail (serialização JSON e impressão no console).

## Tecnologias
- Java 17
- Spring Boot 3.3.4
- Maven
- Jackson (serialização JSON)
- JUnit 5 (testes unitários)

## Configuração
1. Configure o `application.properties` com `mail.integracao=AWS` ou `mail.integracao=OCI`.
2. Execute o projeto com `mvn spring-boot:run`.

## Endpoints
- **POST /api/email**
  - **Body**: `EmailRequestDTO` (recipient, recipientName, sender, subject, content)
  - **Resposta**: 
    - 204 (sucesso)
    - 400 (validação inválida ou configuração incorreta)
    - 500 (erro interno)

## Arquitetura
A aplicação segue os princípios da **Clean Architecture**:
- **Entities/DTOs**: Representam os dados (`EmailRequestDTO`, `EmailAwsDTO`, `EmailOciDTO`).
- **Use Cases**: `EmailSenderUseCase` define o contrato para o serviço de envio de e-mails.
- **Services**: `EmailService` implementa o caso de uso, orquestrando a adaptação e o envio.
- **Adapters**: `EmailAdapter` e suas implementações (`AwsEmailAdapter`, `OciEmailAdapter`) tratam a conversão de dados.
- **Gateways**: `EmailSenderGateway` e suas implementações (`AwsEmailSenderGateway`, `OciEmailSenderGateway`) simulam o envio de e-mails.
- **Controllers**: `EmailController` gerencia as requisições REST, usando `EmailSenderUseCase`.

### Design Patterns
- **Adapter Pattern**: Usado para adaptar `EmailRequestDTO` para `EmailAwsDTO` ou `EmailOciDTO`.
- **Factory Pattern**: `EmailProviderFactory` seleciona o provedor com base em `mail.integracao`.
- **Dependency Injection**: Injeção de dependências via Spring, incluindo `EmailSenderUseCase`.

## Validação de Dados
- O `EmailRequestDTO` valida os campos obrigatórios e formatos de e-mail.
- Os adapters (`AwsEmailAdapter`, `OciEmailAdapter`) validam os limites específicos de cada provedor usando o `Validator` do Spring.
- Entradas inválidas resultam em erros 400 com mensagens detalhadas.

## Tratamento de Erros
A aplicação usa um `GlobalExceptionHandler` para centralizar o tratamento de exceções, garantindo respostas consistentes:
- **400 Bad Request**: Para validações inválidas ou configuração incorreta.
- **500 Internal Server Error**: Para erros de serialização ou exceções inesperadas.

## Testes
O projeto inclui uma suíte de testes unitários que cobrem os principais componentes da aplicação, garantindo a qualidade e a robustez do código. Os testes foram implementados usando JUnit 5 e Mockito.

### Classes de Teste
- **`EmailServiceTest`**: Testa a lógica de orquestração do serviço de envio de e-mails, incluindo a seleção do provedor e a delegação para adaptadores e gateways.
- **`AwsEmailAdapterTest` e `OciEmailAdapterTest`**: Validam a conversão do `EmailRequestDTO` para os formatos específicos de cada provedor (AWS e OCI), incluindo verificações de limites de tamanho.
- **`AwsEmailSenderGatewayTest` e `OciEmailSenderGatewayTest`**: Testam a serialização dos DTOs em JSON e a simulação do envio de e-mails.

### Executando os Testes
Para executar todos os testes unitários, use o comando:
```bash
mvn test
```

Para gerar o relatório de cobertura de testes com JaCoCo, execute:
```bash
mvn clean test jacoco:report
```
O relatório de cobertura estará disponível em `target/site/jacoco/index.html`. O projeto foi desenvolvido com foco em alta cobertura de testes para os componentes críticos, como o serviço, adaptadores e gateways.

## Como Testar
1. Use o Insomnia, ou alguma outra ferramenta similar, para enviar uma requisição POST para `http://localhost:8080/api/email` com o body:
```json
{
    "recipient": "recipient@example.com",
    "recipientName": "Recipient Name",
    "sender": "sender@example.com",
    "subject": "Test Subject",
    "content": "Test Content"
}
```
2. Verifique o console para a saída JSON.
3. Teste casos de erro (ex.: e-mail com mais de 40 caracteres) para validar respostas 400.

## Melhorias Futuras
- Adicionar integração real com AWS SES ou OCI Email Service.
- Implementar mais testes de integração.
- Adicionar suporte a múltiplos idiomas.
- Incluir logging para erros e métricas (ex.: Micrometer).