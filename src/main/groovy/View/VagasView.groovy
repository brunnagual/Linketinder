package View


import Controller.RegexController
import Controller.VagasController
import DAO.VagasDAO
import Model.RegexModel
import Model.VagasModel


class VagasView {

    static void exibirInformacoesVagas() {

        List<VagasModel> vagas = VagasController.ListarVagas()

        for (VagasModel vaga: vagas){
            println("$vaga.nome | $vaga.descricao | $vaga.salario | $vaga.idEmpresa| $vaga.nomeEmpresa")
        }
    }

    static void cadastrarVaga(Scanner scanner) {

        String nome = RegexController.validarEntrada( "Nome da vaga: ",RegexModel.regexNome, scanner)
        String descricao = RegexController.validarEntrada( "Descrição da vaga: ",RegexModel.regexDrescricao, scanner)
        double salario = RegexController.validarEntrada( "Salário: ",RegexModel.regexSalario, scanner) as double
        EmpresaView.exibirInformacoesEmpresa()
        Integer idEmpresa = RegexController.validarEntrada( "ID dá Empresa: ",RegexModel.regexNumero, scanner) as Integer

        ArrayList<String> competencias = new ArrayList<String>()
        VagasModel vaga = new VagasModel(nome, descricao, salario, idEmpresa, null, competencias)

        Integer vagaId = VagasController.cadastrarVaga(vaga, scanner)
        associandocompetencias(scanner, vagaId)
    }

    static void associandocompetencias(Scanner scanner, Integer vagaId){
        if (vagaId != -1) {
            VagasDAO.listarTodasCompetencias()

            boolean continuar = true

            while (continuar) {

                int idCompetencia = RegexController.capturarEntradaInt("Digite o número da competência " +
                        "que deseja associar: ", scanner)
                VagasController.associarCompetencias(vagaId, idCompetencia)
                println("Deseja associar outra competência? (S/N): ")
                String resposta = scanner.next().toUpperCase()

                continuar = resposta.equals("S")
            }
        }
    }
}
