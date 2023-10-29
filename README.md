# Exchange API 💰

## :paperclips: Sobre

A Exchange API é um projeto desenvolvido como parte do curso Back-end Java da [Ada](https://ada.tech/) no módulo "Arquitetura de Software Ágil I".<br>
A proposta é implementar uma API que permita ao cliente realizar a compra de moedas estrangeiras, como o dólar (USD) e o euro (EUR), de forma simples e eficiente.

## :pushpin: Funcionalidades principais

- Cadastro de Clientes: Os clientes podem se cadastrar no sistema fornecendo informações essenciais, como nome, CPF, data de nascimento, estado civil e sexo. Após o cadastro bem-sucedido, o sistema gera um ID de cliente único para cada usuário. Além disso, é possível consultar um cliente por meio do seu CPF.

- Registro de Ordem de Compra: Após o cadastro, os clientes podem registrar ordens de compra de moeda estrangeira. Cada ordem de compra inclui informações como CPF do cliente, tipo de moeda desejada (USD ou EUR), valor em moeda estrangeira e número da agência onde a retirada ocorrerá.

- Cálculo da Cotação da Moeda: A API calcula o valor total com base na cotação da moeda, multiplicando-a pelo valor desejado de compra. Para isso, faz uma chamada à API externa economia.awesomeapi.com.br com a sigla da moeda desejada (USD ou EUR). Caso o cliente tente comprar outra moeda que não seja USD ou EUR, a API lançará uma exceção.

## :man_technologist: Conhecimentos aplicados
- **Linguagem de Programação:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** PostgreSQL
- **Arquitetura:** O projeto segue os princípios de SOLID e adota o padrão de arquitetura MVC (Model-View-Controller).
- **Metodologia de Desenvolvimento:** Scrum
- **Gerenciamento do Projeto:** [Trello](https://trello.com/invite/b/jqnSG2xM/ATTI843a6837490ea45d4e0395f3378b0bef3604D359/exchange-api-projeto-modulo-4-btgfaztech)

## ⚙️ Como Executar
1. Clone o repositório em uma pasta de preferência
  ```
  git@github.com:nataliagiacobo/exchangeAPI.git
  ```
2. Entre na pasta que você acabou de clonar e instale as dependências
  ```
  mvn install
  ```
3. Visualize a interface da API utilizando o Thunder Client, Postman, Insomnia ou outra plataforma de sua preferência <br>
> **Acesso ao Swagger UI (Opcional)** <br>
   Se preferir, você pode explorar e testar os endpoints da API acessando o Swagger UI. <br>
   Certifique-se de que o projeto esteja em execução e acesse a URL abaixo pelo seu navegador: <br>
   http://localhost:8080/swagger-ui/index.html#/

## 📚 Documentação (endpoints)

### :bust_in_silhouette: Customer
<details>
  <summary> Cadastro (POST) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza o cadastro do cliente no sistema | `http://localhost:8080/customer`

  > :warning: &nbsp;  _Os atributos "maritalStatus" e "sex" aceitam somente valores pré-estabelecidos_
  
  <details>
    <summary> A estrutura do body da requisição deverá seguir o padrão abaixo: </summary>
    
    {
      "name": "String",
      "cpf": "String", // 11 dígitos do CPF sem os separadores '12345678901'
      "birthDate": "Date", // Seguir o padrão 'YYYY-MM-DD'
      "maritalStatus": "MaritalStatus", // Valores possíveis: "SINGLE", "MARRIED", "DIVORCED", "WIDOWED"
      "sex": "Sex" // Valores possíveis: "MALE", "FEMALE", "OTHER"
    }
  
  </details>

  <details>
    <summary>  Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>

    {
      "id": 1
    }
    
  </details>

  :x:&nbsp;&nbsp;A requisição irá falhar se algum dos atributos não for preenchido corretamente ou esteja ausente.<br>
  O endpoint retornará um erro <code>400</code> com a mensagem: <code>{ "All fields must be filled out correctly" }</code><br>
</details>

<details>
  <summary> Consultas (GET) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Consulta todos os clientes cadastrados | `http://localhost:8080/customer`
  
  <details>
   <summary>  Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>
   
       [
         {
           "id": 1,
          "name": "Ana",
          "birthDate": "1990-05-15",
          "maritalStatus": "MARRIED",
          "sex": "FEMALE"
         },
         // Outros clientes...
       ]
       
  </details>
  :x:&nbsp;&nbsp;A requisição irá falhar se não houver, pelo menos, um cliente cadastrado.<br>
  O endpoint retornará um erro <code>400</code> com a mensagem: <code>{ "You must save a new customer first" }</code>
  <br><br>
  
  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Realiza a consulta do cliente pelo seu cpf | `http://localhost:8080/customer/cpf/{cpf}`
  
  <details>
    <summary>  Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>
    
    {
      "id": 1,
      "name": "Ana",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  
  </details>
  
  :x:&nbsp;&nbsp;A requisição irá falhar se o CPF utilizado para consulta não estiver associado a nenhum cliente cadastrado.<br>
  O endpoint retornará um erro <code>404</code> com a mensagem: <code>{ "CPF not found" }</code>
  <br>
</details>

  <details>
  <summary> Atualização (PUT) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `PUT` | Atualiza as informações de um cliente existente | `http://localhost:8080/customer/{id}`
  
  <details>
    
  > :warning: &nbsp; _Qualquer atributo pode ser atualizado, porém todos devem ser escritos, mesmo quando não houver alteração_

  <summary> A estrutura do body da requisição deve seguir o padrão do exemplo abaixo: </summary>
  
    {
      "name": "Novo Nome da Cliente",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  
  </details>
  
  <details>
    <summary>  Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>
  
    {
      "id": 1,
      "name": "Maria",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  </details>

  :x:&nbsp;&nbsp; A requisição irá falhar se algum dos atributos não for preenchido corretamente ou esteja ausente.<br> 
  O endpoint retornará um erro <code>400</code> com a mensagem: <code>{ "All fields must be filled out correctly" }</code>
  <br>
</details>

<details>
  <summary> Exclusão (DELETE) </summary>
    <br>
  
  | Método | Funcionalidade | URL |
  |---|---|---|
  | `DELETE` | Remove um cliente existente | `http://localhost:8080/customer/{id}`
  
  -&nbsp;&nbsp;&nbsp;Para deletar um cliente, especifique o `id` desejado na URL, conforme mostrado acima. Não é necessário incluir um corpo de requisição, pois a ação de exclusão é baseada no `id` fornecido.
  
 :x:&nbsp;&nbsp;A requisição irá falhar se o ID não estiver associado a nenhum cliente cadastrado.<br> 
 O endpoint retornará um erro <code>404</code> com a mensagem: <code>{ "ID not found" }</code>
 </details>

### :currency_exchange: Order
<details>
  <summary> Cadastro (POST) </summary>
  <br>
  
  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza a ordem de compra da moeda desejada | `http://localhost:8080/order`
  
  > :warning: &nbsp; _O atributo "currency" aceita somente valores pré-estabelecidos_

  <details>
    <summary> A estrutura do body da requisição deverá seguir o padrão abaixo: </summary>
      
      {
        "cpf": "String",
        "currency": "String", // Valores possíveis: "USD" ou "EUR"
        "exchangeAmount": "BigDecimal",
        "bankBranch": "String"
      }
      
  </details>
  
  <details>
    <summary> Um exemplo de resposta bem-sucedida com <code>status 201</code> é: </summary>
    
      {
        "orderId": 1,
        "customerId": 1,
        "cpf": "43488428095",
        "currency": "EUR",
        "exchangeAmount": 100.0,
        "quotation": 6.5857,
        "operationCost": 658.57,
        "bankBranch": "7057"
        "orderTimestamp": "2021-08-27T16:11:23.866",
      }
        
  </details>
  
 :x:&nbsp;&nbsp;A requisição irá falhar se o campo <code>"currency"</code> não for um dos valores válidos ("USD" ou "EUR").<br>
 O endpoint retornará um erro <code>400</code> com a mensagem: <code>{ "Currency must be USD or EUR" }</code>
 <br>
</details>

<details>
  <summary>Consulta (GET)</summary>
  <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Realiza a consulta das ordens de compra pelo cpf do cliente | `http://localhost:8080/order/cpf/{cpf}`
  
  <details>
    <summary>  Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>
    
    {
      "id": 1,
      "name": "Ana",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  
  </details>

 :x:&nbsp;&nbsp;A requisição irá falhar se o CPF utilizado para consulta não estiver associado a nenhum cliente cadastrado.<br>
 O endpoint retornará um erro <code>404</code> com a mensagem: <code>{ "CPF not found" }</code>
 <br>
</details>
  <br>

> _O desenvolvimento da Exchange API foi um esforço colaborativo realizado por [Dayane](https://github.com/acdayane), [Juliana](https://github.com/julianaando), [Karen](https://github.com/karenCLima), [Natalia](https://github.com/nataliagiacobo), [Raquel](https://github.com/raquelpcarvalho) e [Thaís](https://github.com/tdthais)._
