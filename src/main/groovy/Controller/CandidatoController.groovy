package Controller

import DAO.ConexaoDAO
import Model.CandidatoModel
import DAO.CandidatoDAO
import Model.RegexModel

import java.sql.Connection

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

        cadastrarCandidato(candidato, scanner)

        return candidato
    }

    static boolean cadastrarCandidato(CandidatoModel candidato, Scanner scanner) {
        Connection con = null

        try {
            con = new ConexaoDAO().getConnection()

            int candidatoId = CandidatoDAO.inserirCandidatoNoBanco(candidato.nome, candidato.sobrenome, candidato.email,
                    candidato.cep, candidato.cpf, candidato.descricaoPessoal)

            if (candidatoId != -1) {
                CandidatoDAO.listarTodasCompetencias(con)
                while (CandidatoDAO.associarCompetencia(con, candidatoId, scanner)) {
                }
                RegexController.mostrarSucesso("Candidato")
            } else {
                println("Falha ao cadastrar o candidato.")
            }
        } finally {
            ConexaoDAO.desconectar(con)
        }
    }

}