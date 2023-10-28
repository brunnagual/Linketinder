package Controller

import Model.CandidatoModel
import DAO.CandidatoDAO
import Model.RegexModel

class CandidatoController {
    static CandidatoModel cadastrarCandidato(List<CandidatoModel> candidatos, Scanner scanner) {
        String nome = RegexController.validarEntrada("Nome: ", RegexModel.regexNome, scanner)
        String sobrenome = RegexController.validarEntrada("Sobrenome: ", RegexModel.regexNome, scanner)
        String email = RegexController.validarEntrada("Email: ", RegexModel.regexEmail, scanner)
        long cpf = RegexController.validarEntradaLong("CPF: ", RegexModel.regexCpf, scanner)
        String cep = RegexController.validarEntrada("CEP: ", RegexModel.regexCep, scanner)
        String descricaoPessoal = RegexController.validarEntrada("Descrição Pessoal: ", RegexModel.regexDrescricao, scanner)

        ArrayList<String> competencias = new ArrayList<String>()
        CandidatoModel candidato = new CandidatoModel(nome, sobrenome, email, cep, cpf, descricaoPessoal, competencias)

        CandidatoDAO.cadastrarCandidato(candidato, scanner)

        return candidato
    }

}