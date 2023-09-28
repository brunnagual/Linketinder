import BD.conexao
import Users.Candidato
import Users.Empresa

static void main(String[] args) {

    def conexao = new conexao()

    def candidatos = []
    def empresas = []

//    candidatos.add(new Candidato("Candidato 1", "candidato1@example.com", 25, "Estado 1", 12345, 12345678901, "Descrição 1", ["Java", "Python"]))
//    candidatos.add(new Candidato("Candidato 2", "candidato2@example.com", 30, "Estado 2", 67890, 23456789012, "Descrição 2", ["C++", "JavaScript"]))
//    candidatos.add(new Candidato("Candidato 3", "candidato3@example.com", 28, "Estado 3", 54321, 34567890123, "Descrição 3", ["Ruby", "Swift"]))
//    candidatos.add(new Candidato("Candidato 4", "candidato4@example.com", 35, "Estado 4", 98765, 45678901234, "Descrição 4", ["PHP", "HTML"]))
//    candidatos.add(new Candidato("Candidato 5", "candidato5@example.com", 22, "Estado 5", 13579, 56789012345, "Descrição 5", ["CSS", "SQL"]))
//
//    empresas.add(new Empresa("Empresa 1", "email1@example.com", 25, "Estado 1", 12345, "12345678901234", ["Java", "Angular"]))
//    empresas.add(new Empresa("Empresa 2", "email2@example.com", 30, "Estado 2", 67890, "56789012345678", ["Python", "SQL"]))
//    empresas.add(new Empresa("Empresa 3", "email3@example.com", 28, "Estado 3", 54321, "98765432101234", ["Spring Framework", "Java Script"]))
//    empresas.add(new Empresa("Empresa 4", "email4@example.com", 35, "Estado 4", 98765, "34567890123456", ["Angular ", "Type Script"]))
//    empresas.add(new Empresa("Empresa 5", "email5@example.com", 22, "Estado 5", 13579, "76543210987654", ["ReactJS", "C++"]))

    def scanner = new Scanner(System.in)

    while (true) {
        println("Escolha uma opção:\n")
        println("[1] Listar Candidatos")
        println("[2] Listar Empresas")
        println("[3] Cadastrar Candidato")
        println("[4] Cadastrar Empresas")
        println("[0] Sair")
        def opcao = scanner.nextLine()

        switch (opcao) {
            case "1":
                listarCandidatos(candidatos)
                break
            case "2":
                listarEmpresas(empresas)
                break
            case "3":
                cadastrarCandidato(candidatos, scanner)
                break
            case "4":
                cadastrarEmpresa(empresas, scanner)
                break
            case "0":
                println("Saindo do programa.")
                return
            default:
                println("Opção inválida.")
        }
    }
}

static void listarCandidatos(List<Candidato> candidatos) {
    candidatos.each { candidato ->
        println("Nome: ${candidato.nome}, Email: ${candidato.email}, Descrição Pessoa: ${candidato.descricaoPessoal}, Competências: ${candidato.competencias}")
    }
}

static void listarEmpresas(List<Empresa> empresas) {
    empresas.each { empresa ->
        println("Nome: ${empresa.nome}, Email: ${empresa.email}, Estado: ${empresa.estado}, Expectativas de competências: ${empresa.expectativasCompetencias}")
    }
}

static void cadastrarCandidato(List<Candidato> candidatos, Scanner scanner) {
    def nome = capturarEntrada("Nome: ", scanner)
    def email = capturarEntrada("Email: ", scanner)
    def cpf = capturarEntrada("CPF: ", scanner) as long
    def idade = capturarEntrada("Idade: ", scanner) as int
    def estado = capturarEntrada("Estado: ", scanner)
    def cep = capturarEntrada("CEP: ", scanner) as int
    def descricaoPessoal = capturarEntrada("Descrição Pessoal: ", scanner)

    def competencias = []
    while (true) {
        def competencia = capturarEntrada("Competência (ou 'fim' para sair): ", scanner)
        if (competencia == "fim") {
            break
        }
        competencias.add(competencia)
    }

    candidatos.add(new Candidato(nome, email, idade, estado, cep, cpf, descricaoPessoal, competencias))
    println("Candidato cadastrado com sucesso.")
}

static void cadastrarEmpresa(List<Empresa> empresas, Scanner scanner) {
    def nome = capturarEntrada("Nome: ", scanner)
    def email = capturarEntrada("E-mail: ", scanner)
    def idade = capturarEntrada("Idade: ", scanner) as int
    def estado = capturarEntrada("Estado: ", scanner)
    def cep = capturarEntrada("CEP: ", scanner) as int
    def cnpj = capturarEntrada("CNPJ: ", scanner)

    def expectativasCompetencias = []
    while (true) {
        def competencia = capturarEntrada("Expectativa de Competência (ou 'fim' para sair): ", scanner)
        if (competencia == 'fim') {
            break
        }
        expectativasCompetencias.add(competencia)
    }

    empresas.add(new Empresa(nome, email, idade, estado, cep, cnpj, expectativasCompetencias))
    println("Empresa cadastrada com sucesso.")
}

static String capturarEntrada(String mensagem, Scanner scanner) {
    print(mensagem)
    return scanner.nextLine()
}