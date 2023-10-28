package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class CompetenciasDAO {

    static void cadastrarCompetencia(String nomeCompetencia) {
        Connection con = new ConexaoDAO().getConnection()

        String sqlInserirCompetencia = "INSERT INTO competencias (nome) VALUES (?);"

        try {
            PreparedStatement stmtCompetencia = con.prepareStatement(sqlInserirCompetencia)
            stmtCompetencia.setString(1, nomeCompetencia)

            int rowsAffected = stmtCompetencia.executeUpdate()
            if (rowsAffected > 0) {
                println("Competência cadastrada com sucesso.")
            } else {
                println("Falha ao cadastrar a competência.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
        ConexaoDAO.desconectar (con)
    }

}
