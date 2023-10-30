package View

import Controller.CandidatoController
import Controller.RegexController
import Model.CandidatoModel
import Model.RegexModel

class CandidatoView {

    static void exibirInformacoesCandidato() {

        List<CandidatoModel> candidatos = CandidatoController.listarCandidatos()

        for (CandidatoModel candidato: candidatos){
            println("$candidato.nome | $candidato.sobrenome | $candidato.email | $candidato.cep | $candidato.descricaoPessoal")
        }
    }

    static void cadastrarCandidato(Scanner scanner) {

        String nome = RegexController.validarEntrada("Nome: ", RegexModel.regexNome, scanner)
        String sobrenome = RegexController.validarEntrada("Sobrenome: ", RegexModel.regexNome, scanner)
        String email = RegexController.validarEntrada("Email: ", RegexModel.regexEmail, scanner)
        long cpf = RegexController.validarEntradaLong("CPF: ", RegexModel.regexCpf, scanner)
        String cep = RegexController.validarEntrada("CEP: ", RegexModel.regexCep, scanner)
        String descricaoPessoal = RegexController.validarEntrada("Descrição Pessoal: ", RegexModel.regexDrescricao, scanner)

        ArrayList<String> competencias = new ArrayList<String>()
        CandidatoModel candidato = new CandidatoModel(nome, sobrenome, email, cep, cpf, descricaoPessoal, competencias)

        if (CandidatoController.cadastrarCandidato(candidato, scanner)){
            println("Okay")
        }else
            println("Not okay")

    }
}
