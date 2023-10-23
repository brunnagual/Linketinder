import DAO.CandidatoDB
import DAO.EmpresaDB
import DAO.VagasDB
import DAO.competenciasDB
import Model.Candidato
import Model.Competencia
import Model.Empresa

static void main(String[] args) {
    List<Candidato> candidatos = []
    List<Empresa> empresas = []
    List<VagasDB> vagas = []
    Scanner scanner = new Scanner(System.in)

    menuOpcoes: while (true) {
        println("Escolha uma opção:\n")
        println("[1] Listar Candidatos")
        println("[2] Listar Empresas")
        println("[3] Cadastrar Candidato")
        println("[4] Cadastrar Empresas")
        println("[5] Cadastrar Vaga")
        println("[6] Listar Vagas")
        println("[7] Cadastrar competências")
        println("[0] Sair")
        String opcao = scanner.nextLine()

        switch (opcao) {
            case "1":
                CandidatoDB.listarCandidatos()
                break
            case "2":
                EmpresaDB.listarEmpresas()
                break
            case "3":
                Candidato.cadastrarCandidato(candidatos, scanner)
                break
            case "4":
                Empresa.cadastrarEmpresa(empresas, scanner)
                break
            case "5":
                VagasDB.cadastrarVaga(vagas, scanner)
                break
            case "6":
                VagasDB.listarVagas()
                break
            case "7":
                Competencia.cadastrarCompetencia(scanner)
                break
            case "0":
                println("Saindo do programa.")
                break menuOpcoes
            default:
                println("Opção inválida.")
        }
    }
}