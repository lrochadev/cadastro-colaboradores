# cadastro-colaboradores

# Instruções:

Fazer o checkout do projeto e na raiz acessar a pasta ColaboradoresApi e executar o comando abaixo:

- mvn clean install

Com o build realizado com sucesso, executar o comando abaixo para criar os containers da API, mysql e do front-end

- docker-compose up --build

A partir de agora você poderá acessar os serviços pelos endpoints.

# Link de acesso para a aplicação: http://localhost:4200

# Endpoints API*

Endpoints necessários para consumir os serviços da API:

<li>Criar colaborador</li>

 - POST - http://localhost:8080/colaboradores
 
 Segue abaixo, exemplo com os campos para realizar a chamada JSON:
 
 { 
 "nome":"",
 "email":"",
 "telefone":"",
 "cpf":"",
 "dataNascimento", ""
 }
 
# Listar Colaboradores

- GET - http://localhost:8080/colaboradores

# Buscar Colaboradorres

- GET - http://localhost:8080/colaboradores/{id}

# Deletar Colaborador

- DELETE - http://localhost:8080/colaboradores/{id}

# Buscar Colaborador por nome

- DELETE - http://localhost:8080/colaboradores?nome=
