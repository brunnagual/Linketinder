package Controller

import DAO.VagasDAO
import Model.RegexModel
import Model.VagasModel

class VagasController {
    static void cadastrarVaga(List<VagasModel> Vagas, Scanner scanner) {

        String nome = RegexController.validarEntrada( "Nome da vaga: ",RegexModel.regexNome, scanner)
        String descricao = RegexController.validarEntrada( "Descrição da vaga: ",RegexModel.regexDrescricao, scanner)
        double salario = RegexController.validarEntrada( "Salário: ",RegexModel.regexSalario, scanner) as double
        String nomeEmpresa = RegexController.validarEntrada( "Empresa: ",RegexModel.regexNome, scanner)

        VagasModel vaga = new VagasModel(nome, descricao, salario, nomeEmpresa)

        VagasDAO.cadastrarVaga(vaga, scanner)
    }
}
