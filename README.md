# Linketinder

Adaptação do projeto para gradle.

O tracking do projeto original está na branch atualização.

- Brunna adriane de Jesus Gualberto.

---------- Clean Code e SOLID -----------

Nessa etapa, foram realizadas as seguintes alterações no código:

 - Adicionei a função de cadastrar competências: Implementei uma nova função para lidar com o cadastro de competências, tornando o código mais modular e legível.

 - Renomeei os nomes para nomes mais autoexplicativos: Renomeei variáveis e métodos para que seus nomes refletissem com mais clareza o propósito e a funcionalidade, tornando o código mais compreensível.

 - Removi os comentários: Com as mudanças implementadas, os comentários que antes eram necessários se tornaram dispensáveis, uma vez que o código agora está mais claro e autoexplicativo.

 - Substituí todos os tipos "def" pelos tipos corretos: Adotei uma boa prática de programação, especificando explicitamente os tipos de variáveis em vez de usar o "def," tornando o código mais seguro e fácil de entender.

 - Criei mais classes: Introduzi novas classes para melhorar a organização e a coesão do código, seguindo princípios de design orientado a objetos.

 - Refatorei os métodos para terem apenas uma responsabilidade: Dividi os métodos em funções menores, cada uma com uma única responsabilidade, melhorando a manutenção e a extensibilidade do código.

Essas alterações visaram aprimorar a qualidade e a legibilidade do código, tornando-o mais limpo e de fácil compreensão.

- Ajustei as funções para Princípio da inversão de dependência.

---------- Padrões de Projeto -----------

Foi refatorado todo código para o o padrão MVC onde tenho a minha main que chama minha View que acessa os contrutores que estão na minha Model..
A partir dá minha view é chamado as funções na controller.
E a partir dá minha controller é chamado minhas funções na DAO.

- Foi utilizado o design Pattern Singleton.
- Com isso foi possível fazer o teste unitario para minhas Candidatos, Empresas e competências.
- Todos esses ajustes permitiu um código muito mais legivel com possibilidade de teste e de ajustes.
- Foi necessário deletar coluna id_competencias na tabela de vagas.
- Passei a coluna id_empresa para receber dados nulos.
- Foi feito os ajustes necessário para inversão de dependências das classes.   
