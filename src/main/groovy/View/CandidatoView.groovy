package View

import Controller.CandidatoController
import DAO.CandidatoDAO
import DAO.DatabaseUtilDAO

import java.sql.ResultSet
import java.sql.SQLException

class CandidatoView {

    static void exibirInformacoesCandidato(ResultSet conjuntoResultados) {
        try {
            while (conjuntoResultados.next()) {
                String nome = conjuntoResultados.getString("nome")
                String sobrenome = conjuntoResultados.getString("sobrenome")
                String email = conjuntoResultados.getString("email")
                String cep = conjuntoResultados.getString("cep")
                String descricaoPessoal = conjuntoResultados.getString("descricao")

                println("$nome | $sobrenome | $email | $cep | $descricaoPessoal")
            }
        } catch (SQLException e) {
            DatabaseUtilDAO.handleSQLException(e)
        }
    }
}
