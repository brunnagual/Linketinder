import BD.CandidatoDB
import BD.EmpresaDB
import BD.conexao

static void main(String[] args) {
    def con = new conexao().getConnection()
    def candidatos = []
    def empresas = []
    def scanner = new Scanner(System.in)

    menu: while (true) {
        println("Escolha uma opção:\n")
        println("[1] Listar Candidatos")
        println("[2] Listar Empresas")
        println("[3] Cadastrar Candidato")
        println("[4] Cadastrar Empresas")
        println("[0] Sair")
        def opcao = scanner.nextLine()

        switch (opcao) {
            case "1":
                CandidatoDB.listarCandidatos(con)
                break
            case "2":
                EmpresaDB.listarEmpresas(con)
                break
            case "3":
                CandidatoDB.cadastrarCandidato(candidatos,con, scanner)
                break
            case "4":
                EmpresaDB.cadastrarEmpresa(empresas,con, scanner)
                break
            case "0":
                println("Saindo do programa.")
                con.close()
                break menu
            default:
                println("Opção inválida.")
        }
    }
}