import DAO.CandidatoDB
import DAO.EmpresaDB
import DAO.conexao
import DAO.VagasDB
import DAO.competenciasDB

static void main(String[] args) {
    def con = new conexao().getConnection()
    def candidatos = []
    def empresas = []
    def vagas = []
    def scanner = new Scanner(System.in)

    menu: while (true) {
        println("Escolha uma opção:\n")
        println("[1] Listar Candidatos")
        println("[2] Listar Empresas")
        println("[3] Cadastrar Candidato")
        println("[4] Cadastrar Empresas")
        println("[5] Cadastrar Vaga")
        println("[6] Listar Vagas")
        println("[7] Cadastrar competências")
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
            case "5":
                VagasDB.cadastrarVaga(vagas,con, scanner)
                break
            case "6":
                VagasDB.listarVagas(con)
                break
            case "7":
                competenciasDB.cadastrarCompetencia(con, scanner)
                break
            case "0":
                println("Saindo do programa.")
                break menu
            default:
                println("Opção inválida.")
        }
    }
}