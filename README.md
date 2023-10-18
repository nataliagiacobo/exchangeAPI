# Exchange API 💰

#### ⏳ Desenvolvido durante o módulo Arquitetura de Software Ágil I no curso Back-end Java da [Ada](https://ada.tech/)

## :page_with_curl: Proposta

TODO


## 💡 Conhecimentos aplicados
TODO


## ⚙️ Como Executar
Clone o repositório em uma pasta de preferência
```
git@github.com:nataliagiacobo/exchangeAPI.git
```
Entre na pasta que você acabou de clonar e instale as dependências
```
mvn install
```
## 📚 Documentação (endpoints)

### :bust_in_silhouette: User
<details>
  <summary> Rotas </summary>
  <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza o cadastro do usuário no sistema | `http://localhost:8080/user`

  <details>
    <summary> A estrutura do body da requisição deverá seguir o padrão abaixo: </summary>
    
    {
      "nome": "string",
      "cpf": "string",
      "data de nascimento": "string",
      "estado civil": "string",
      "sexo": "string"
    }
  
  </details>

  <details>
    <summary> A resposta da requisição é a seguinte com <code>status 200</code>: </summary>

    {
      "id": Integer
    }
