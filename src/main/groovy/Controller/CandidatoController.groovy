package Controller

import DAO.ConexaoDAO
import Model.CandidatoModel
import DAO.CandidatoDAO
import Model.RegexModel

import java.sql.Connection

class CandidatoController {

    static boolean cadastrarCandidato(CandidatoModel candidato, Scanner scanner) {
        Connection con = ConexaoDAO.getInstance().getConnection()

            int candidatoId = CandidatoDAO.inserirCandidatoNoBanco(candidato.nome, candidato.sobrenome, candidato.email,
                    candidato.cep, candidato.cpf, candidato.descricaoPessoal)

            if (candidatoId != -1) {
                CandidatoDAO.listarTodasCompetencias(con)

                boolean continuar = true

                while (continuar) {

                    int idCompetencia = RegexController.capturarEntradaInt("Digite o número da competência " +
                            "que deseja associar: ", scanner)
                    CandidatoDAO.associarCompetencia(candidatoId, idCompetencia)
                    println("Deseja associar outra competência? (S/N): ")
                    String resposta = scanner.next().toUpperCase()

                    continuar = resposta.equals("S")
                }
                    return true
            } else {
                return false
            }
        }
    static List<CandidatoModel> listarCandidatos(){
        return CandidatoDAO.listarCandidatos()
    }
}
