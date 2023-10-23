# Exchange API 💰

## :paperclips: Sobre

A Exchange API é um projeto desenvolvido como parte do curso Back-end Java da [Ada](https://ada.tech/) no módulo "Arquitetura de Software Ágil I".<br>
A proposta é implementar uma API que permita ao cliente realizar a compra de moedas estrangeiras, como o dólar (USD) e o euro (EUR), de forma simples e eficiente.

## :pushpin: Funcionalidades principais

1. Cadastro de Clientes: Os clientes podem se cadastrar no sistema fornecendo informações essenciais, como nome, CPF, data de nascimento, estado civil e sexo. Após o cadastro bem-sucedido, o sistema gera um ID de cliente único para cada usuário. Além disso, é possível consultar um cliente por meio do seu CPF.

2. Registro de Ordem de Compra: Após o cadastro, os clientes podem registrar ordens de compra de moeda estrangeira. Cada ordem de compra inclui informações como CPF do cliente, tipo de moeda desejada (USD ou EUR), valor em moeda estrangeira e número da agência onde a retirada ocorrerá.

3. Cálculo da Cotação da Moeda: A API calcula o valor total com base na cotação da moeda, multiplicando-a pelo valor desejado de compra. Para isso, faz uma chamada à API externa economia.awesomeapi.com.br com a sigla da moeda desejada (USD ou EUR). Caso o cliente tente comprar outra moeda que não seja USD ou EUR, a API lançará uma exceção.

## :man_technologist: Conhecimentos aplicados
- **Linguagem de Programação:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** PostgreSQL
- **Arquitetura:** O projeto segue os princípios de SOLID e adota o padrão de arquitetura MVC (Model-View-Controller).
- **Metodologia de Desenvolvimento:** Scrum

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
    <summary>  A resposta de uma requisição bem-sucedida com <code>status 200</code> é: </summary>

    {
      "id": 1
    }
    
  </details>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Consulta todos os clientes cadastrados | `http://localhost:8080/customer`
  
  <details>
   <summary>  A resposta de uma requisição bem-sucedida com <code>status 200</code> é: </summary>
   
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
    <br>
  
  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Realiza a consulta do cliente pelo seu cpf | `http://localhost:8080/customer/{cpf}`
  
  <details>
    <summary>  A resposta de uma requisição bem-sucedida com <code>status 200</code> é: </summary>
    
    {
      "id": 1,
      "name": "Ana",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  
  </details>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `PUT` | Atualiza as informações de um cliente existente | `http://localhost:8080/customer/{id}`
  
  <details>
    <summary> A estrutura do body da requisição deve seguir o padrão do exemplo abaixo: </summary>
  
    {
      "name": "Novo Nome da Cliente",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  
  </details>
  
  <details>
    <summary>  A resposta de uma requisição bem-sucedida com <code>status 200</code> é: </summary>
  
    {
      "id": 1,
      "name": "Maria",
      "birthDate": "1990-05-15",
      "maritalStatus": "MARRIED",
      "sex": "FEMALE"
    }
  </details>
  <br>
  
  | Método | Funcionalidade | URL |
  |---|---|---|
  | `DELETE` | Remove um cliente existente | `http://localhost:8080/customer/{id}`
  
  | _Para deletar um cliente, especifique o `id` desejado na URL, conforme mostrado acima. Não é necessário incluir um corpo de requisição, pois a ação de exclusão é baseada no `id` fornecido._
  <br>
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
        "cpf": String,
        "currency_type": String, // Deve ser "USD" ou "EUR"
        "foreign_currency_amount": BigDecimal,
        "agency_number": String
      }
      
  </details>
  
  <details>
    <summary> A resposta de uma requisição bem-sucedida com <code>status 201</code> é: </summary>
    
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
</details>
<br>

| _O desenvolvimento da Exchange API foi um esforço colaborativo realizado por [Dayane](https://github.com/acdayane), [Juliana](https://github.com/julianaando), [Karen](https://github.com/karenCLima), [Natalia](https://github.com/nataliagiacobo), [Raquel](https://github.com/raquelpcarvalho) e [Thaís](https://github.com/tdthais)._
