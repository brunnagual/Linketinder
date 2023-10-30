import Controller.CandidatoController
import Controller.EmpresaController
import Controller.VagasController
import DAO.CandidatoDAO
import DAO.EmpresaDAO
import DAO.VagasDAO
import Model.CandidatoModel
import Model.CompetenciaModel
import Model.EmpresaModel
import Model.VagasModel
import View.CandidatoView

static void main(String[] args) {
    List<CandidatoModel> candidatos = []
    List<EmpresaModel> empresas = []
    List<VagasModel> vagas = []
    Scanner scanner = new Scanner(System.in)

    while (true) {
        mostrarMenuOpcoes()
        String opcao = scanner.nextLine()

        switch (opcao) {
            case "1":
                CandidatoView.exibirInformacoesCandidato()
                break
            case "2":
                EmpresaDAO.listarEmpresas()
                break
            case "3":
                CandidatoView.cadastrarCandidato(scanner)
                break
            case "4":
                EmpresaController.cadastrarEmpresa(empresas, scanner)
                break
            case "5":
                VagasController.cadastrarVaga(vagas, scanner)
                break
            case "6":
                VagasDAO.listarVagas()
                break
            case "7":
                CompetenciaModel.cadastrarCompetencia(scanner)
                break
            case "0":
                println("Saindo do programa.")
                return
            default:
                println("Opção inválida.")
        }
    }
}

static void mostrarMenuOpcoes() {
    println("Escolha uma opção:\n")
    println("[1] Listar Candidatos")
    println("[2] Listar Empresas")
    println("[3] Cadastrar Candidato")
    println("[4] Cadastrar Empresas")
    println("[5] Cadastrar Vaga")
    println("[6] Listar Vagas")
    println("[7] Cadastrar competências")
    println("[0] Sair")
}
