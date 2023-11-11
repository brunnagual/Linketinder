import Model.CompetenciaModel
import View.CandidatoView
import View.EmpresaView
import View.VagasView

static void main(String[] args) {
    Scanner scanner = new Scanner(System.in)

    while (true) {
        mostrarMenuOpcoes()
        String opcao = scanner.nextLine()

        switch (opcao) {
            case "1":
                CandidatoView.exibirInformacoesCandidato()
                break
            case "2":
                EmpresaView.exibirInformacoesEmpresa()
                break
            case "3":
                CandidatoView.cadastrarCandidato(scanner)
                break
            case "4":
                EmpresaView.cadastrarEmpresa(scanner)
                break
            case "5":
                VagasView.cadastrarVaga(scanner)
                break
            case "6":
                VagasView.exibirInformacoesVagas()
                break
            case "7":
                CompetenciaModel.cadastrarCompetencia(scanner)
                break
            case "0":
                println("Saindo do programa.")
                return
            default:
                println("Cadastrado com sucesso")
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
