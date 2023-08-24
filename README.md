# Linketinder

A ideia é combinar a funcionalidade de Match do Tinder, com o campo individual de competências visto no Linkedin e na relação estabelecida entre empresa recrutadora e candidato. 
Realizando a implementação desse sistema.

-  Imports Iniciais:
  
Importamos as classes Candidato e Empresa de seus respectivos pacotes para usar em nosso programa.

- Criação das Listas:
  
Criamos listas vazias candidatos e empresas para armazenar os objetos de candidatos e empresas.

- Menu Interativo:
  
Implementamos um menu interativo usando um loop while que exibe opções e permite ao usuário escolher uma ação.

- Listar Candidatos e Empresas:
  
Criamos funções listarCandidatos e listarEmpresas que percorrem as listas de candidatos e empresas, respectivamente, exibindo seus detalhes.

- Cadastrar Candidato:
  
Criamos a função cadastrarCandidato que aceita parâmetros como candidatos e scanner. Solicita informações do usuário (nome, email, CPF, idade, estado, CEP, descrição e competências) para criar um objeto Candidato e adicioná-lo à lista de candidatos.

- Cadastrar Empresa:
  
Mantivemos a função cadastrarEmpresa que aceita a lista de empresas e o scanner para capturar informações sobre uma empresa do usuário e adicioná-la à lista de empresas.

- Pré-Cadastragem:
  
Pré-cadastramos 5 candidatos usando instâncias da classe Candidato com informações fictícias.

- Loop do Menu:
  
O programa entra em um loop que exibe o menu e permite ao usuário escolher ação. Dependendo da opção escolhida, a função correspondente é chamada.

- Encerramento do Programa:
  
Se o usuário selecionar a opção "0", o programa exibirá uma mensagem de encerramento e sairá do loop, finalizando a execução.

Ao executar o programa, você terá um menu interativo que permite listar candidatos e empresas, cadastrar novos candidatos e empresas, e também pré-cadastrou 5 candidatos.

Isso cria uma experiência interativa e funcional para gerenciar informações de candidatos e empresas.
