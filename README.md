# Exchange API 💰

#### ⏳ Desenvolvido durante o módulo Arquitetura de Software Ágil I no curso Back-end Java da [Ada](https://ada.tech/)

## :page_with_curl: Sobre

TODO


## :man_technologist: Conhecimentos aplicados
▪️ Java <br>
▪️ Spring Boot <br>
▪️ PostgreSQL <br>
▪️ Rest API <br>
▪️ Metodologia Scrum <br>
▪️ Trabalho em equipe <br>


## ⚙️ Como Executar
1. Clone o repositório em uma pasta de preferência
  ```
  git@github.com:nataliagiacobo/exchangeAPI.git
  ```
2. Entre na pasta que você acabou de clonar e instale as dependências
  ```
  mvn install
  ```
3. Visualize a interface da API utilizando o Thunder Client, Postman, Insomnia ou outra plataforma de sua preferência

## 📚 Documentação (endpoints)

### :bust_in_silhouette: Customer
<details>
  <summary> Rotas </summary>
  <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza o cadastro do cliente no sistema | `http://localhost:8080/customer`

  > :warning: &nbsp;  _Os atributos "maritalStatus" e "sex" aceitam somente valores pré-estabelecidos_
  
  <details>
    <summary> A estrutura do body da requisição deverá seguir o padrão abaixo: </summary>
    
    {
      "name": String,
      "cpf": String,
      "birthDate": Date,
      "maritalStatus": MaritalStatus, // Deve ser "SINGLE", "MARRIED", "DIVORCED" ou "WIDOWED" 
      "sex": Sex // Deve ser "MALE", "FEMALE" ou "OTHER"
    }
  
  </details>

  <details>
    <summary> A resposta da requisição <code>status 200</code> é: </summary>

    {
      "id": Integer
    }
  </details>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Realiza a consulta do cliente pelo seu cpf | `http://localhost:8080/customer/{cpf}`

  <details>
    <summary> A resposta da requisição com <code>status 200</code> é: </summary>
    
    {
    //TODO - coloquei apenas um exemplo
      "id": Integer,
      "name": String,
      "cpf": String,
      "birthDate": Date
      "maritalStatus": MaritalStatus,
      "sex": Sex
    }

  </details>
</details>


### :currency_exchange: Order
<details>
  <summary> Rotas </summary>
  <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza a ordem de compra da moeda desejada | `http://localhost:8080/order`
  
  > :warning: &nbsp; _O atributo "currency_type" aceita somente valores pré-estabelecidos_

  <details>
    <summary> A estrutura do body da requisição deverá seguir o padrão abaixo: </summary>
      
      {
        "cpf": "43488428095",
        "currency_type": String, // Deve ser "USD" ou "EUR"
        "foreign_currency_amount": BigDecimal,
        "agency_number": String
      }
      
  </details>
  
  <details>
    <summary> A resposta de uma requisição bem sucedida com <code>status 201</code> é: </summary>
    
      {
        "order_id": 1,
        "customer_id": 1,
        "customer_cpf": "43488428095",
        "request_date": "2021-08-27T16:11:23.866",
        "currency_type": "EUR",
        "foreign_currency_amount": 100.0,
        "exchange_rate": 6.5857,
        "total_operation_value": 658.57,
        "agency_number": "7057"
      }
        
  </details>
  
  <details>
    <summary> A requisição irá falhar nos seguintes casos: </summary>
  :black_small_square: O endpoint retorna um erro <code>400</code> <code>{ "currency_type must be USD or EUR" }</code>, caso o cliente tente comprar uma moeda que não seja Dólar (USD) ou Euro (EUR); <br>
  </details>
  <br>
</details>
