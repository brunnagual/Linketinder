package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class CompetenciasDB {

    static void cadastrarCompetencia(String nomeCompetencia) {
        Connection con = new Conexao().getConnection()

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
        Conexao.desconectar (con)
    }

}
